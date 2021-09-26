package com.taek.daangn.platform.service;

import com.taek.daangn.platform.common.Validation;
import com.taek.daangn.platform.common.exception.VoteValidationException;
import com.taek.daangn.platform.domain.vote.*;
import com.taek.daangn.platform.web.dto.VoteResponseDto;
import com.taek.daangn.platform.web.dto.VoteSaveRequestDto;
import com.taek.daangn.platform.web.dto.VoteSelectRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final VoteItemRepository voteItemRepository;
    private final VoteStatusRepository voteStatusRepository;
    static Validation validation = new Validation();

    @Transactional
    public String insert(VoteSaveRequestDto voteSaveRequestDto, String userId, String voteId) {

        validation.userIdValidation(userId);
        validation.voteTitleValidation(voteSaveRequestDto.getVoteTitle().length());
        validation.voteContentValidation(voteSaveRequestDto.getVoteContent().length());
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

    public VoteResponseDto findVoteById(String userId, Long postId, String voteId) {
        // 1. 만약 투표가 진행중이면 count를 안보이게 조회

        Vote entity = voteRepository.findById(voteId).orElseThrow(() -> new IllegalArgumentException("해당 투표가 존재하지 않습니다."));

        List<VoteItem> voteItems = voteItemRepository.findByVoteId(entity.getVoteId());
        VoteStatusId voteStatusId = new VoteStatusId(userId, voteId);
        boolean flag = false;
        VoteStatus voteStatus = voteStatusRepository.findByVoteStatusId(voteStatusId);
        if(voteStatus != null) {
            flag = true;
        }

        return new VoteResponseDto(entity, voteItems, flag);
    }

    public List<VoteItem> findVoteResult(VoteSelectRequestDto voteSelectRequestDto) {
        List<VoteItem> voteItems = voteItemRepository.findByVoteIdOrderByVoteItemCountDesc(voteSelectRequestDto.getVoteId());
        return voteItems;
    }

    public List<Vote> findSaveVote(String userId) {

        List<Vote> votes = voteRepository.findByUserId(userId);

        return votes;
    }
}
