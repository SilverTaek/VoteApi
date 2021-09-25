package com.taek.daangn.platform.service;

import com.taek.daangn.platform.domain.vote.*;
import com.taek.daangn.platform.web.dto.VoteResponseDto;
import com.taek.daangn.platform.web.dto.VoteSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final VoteItemRepository voteItemRepository;
    private final VoteStatusRepository voteStatusRepository;

    public String insert(VoteSaveRequestDto voteSaveRequestDto, String userId, String voteId) {

        LocalDate voteDealline = LocalDate.now().plusDays(1);

        Vote vote = new Vote();
        if (voteSaveRequestDto.getVoteDeadline() != null) {
            voteDealline = voteSaveRequestDto.getVoteDeadline().plusDays(1);
        }

        vote.insert(voteId, userId, voteSaveRequestDto.getPostId(), voteDealline, voteSaveRequestDto.getVoteTitle(), voteSaveRequestDto.getVoteContent());

        voteRepository.save(vote).getVoteId();

        for (int i = 0; i < voteSaveRequestDto.getVoteItem().size(); i++) {
            VoteItem voteItem = new VoteItem();
            voteItem.insert(voteId, voteSaveRequestDto.getVoteItem().get(i).getVoteItemName(), 0L);
            voteItemRepository.save(voteItem);
        }

        return vote.getVoteId();

    }

    public VoteResponseDto findVoteById(String userId, Long postId, String voteId) {
        Vote entity = voteRepository.findById(voteId).orElseThrow(() -> new IllegalArgumentException("해당 투표가 존재하지 않습니다."));
        List<VoteItem> voteItems = voteItemRepository.findByVoteId(entity.getVoteId());
        boolean flag = false;
        List<VoteStatus> voteStatuses = voteStatusRepository.findAllByVoteStatusId_UserId(userId);
        for (VoteStatus voteStatus : voteStatuses) {
            if (voteId.equals(voteStatus.getVoteId())) {
                flag = true;
            }
        }
        return new VoteResponseDto(entity, voteItems, flag);
    }
}
