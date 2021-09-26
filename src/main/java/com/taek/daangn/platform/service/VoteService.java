package com.taek.daangn.platform.service;

import com.taek.daangn.platform.common.Validation;
import com.taek.daangn.platform.common.exception.VoteValidationException;
import com.taek.daangn.platform.domain.vote.*;
import com.taek.daangn.platform.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final VoteItemRepository voteItemRepository;
    private final VoteStatusRepository voteStatusRepository;

    static boolean flag = false;
    static Validation validation = new Validation();

    @Transactional
    public String insert(VoteSaveRequestDto voteSaveRequestDto, String userId, String voteId) {

        validation.userIdValidation(userId);
        validation.voteItemValidation(voteSaveRequestDto.getVoteItem().size());

        Vote isUsedVoteId = voteRepository.findByVoteId(voteId);
        validation.isUsedVoteId(isUsedVoteId);

        Vote vote = voteSaveRequestDto.toEntity();
        vote.setVoteIdAndUserId(voteId, userId);

        voteRepository.save(vote);

        for (int i = 0; i < voteSaveRequestDto.getVoteItem().size(); i++) {
            VoteItem voteItem = new VoteItem(voteId, voteSaveRequestDto.getVoteItem().get(i).getVoteItemName());
            voteItemRepository.save(voteItem);
        }

        return vote.getVoteId();
    }

    @Transactional
    public VoteResponseDto findVoteById(String userId, Long postId, String voteId) {

        validation.postIdValidation(postId);

        Vote entity = voteRepository.findById(voteId).orElseThrow(() -> new IllegalArgumentException("해당 투표가 존재하지 않습니다."));

        List<VoteItem> voteItems = voteItemRepository.findByVoteId(entity.getVoteId());

        VoteStatusId voteStatusId = new VoteStatusId(userId, voteId);

        VoteStatus voteStatus = voteStatusRepository.findByVoteStatusId(voteStatusId);

        if (voteStatus != null) {
            flag = true;
        }

        return new VoteResponseDto(entity, voteItems, flag);
    }

}
