package com.taek.daangn.platform.service;

import com.taek.daangn.platform.domain.vote.Vote;
import com.taek.daangn.platform.domain.vote.VoteItem;
import com.taek.daangn.platform.domain.vote.VoteItemRepository;
import com.taek.daangn.platform.domain.vote.VoteRepository;
import com.taek.daangn.platform.web.dto.VoteSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final VoteItemRepository voteItemRepository;

    public String insert(VoteSaveRequestDto voteSaveRequestDto, String userId, String voteId) {

        LocalDate voteDealline = LocalDate.now().plusDays(1);

        Vote vote = new Vote();
        if(voteSaveRequestDto.getVoteDeadline() != null){
            voteDealline = voteSaveRequestDto.getVoteDeadline().plusDays(1);
        }

        vote.insert(voteId, userId, voteSaveRequestDto.getPostId(), voteDealline, voteSaveRequestDto.getVoteTitle(), voteSaveRequestDto.getVoteContent());

        voteRepository.save(vote).getVoteId();

        for (int i = 0; i<voteSaveRequestDto.getVoteItem().size(); i++){
            VoteItem voteItem = new VoteItem();
            voteItem.insert(voteId, voteSaveRequestDto.getVoteItem().get(i).getVoteItemName(),0L);
            voteItemRepository.save(voteItem);
        }

        return vote.getVoteId();

    }
}
