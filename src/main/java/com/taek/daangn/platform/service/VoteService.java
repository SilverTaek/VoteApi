package com.taek.daangn.platform.service;

import com.taek.daangn.platform.domain.vote.Vote;
import com.taek.daangn.platform.domain.vote.VoteItem;
import com.taek.daangn.platform.domain.vote.VoteItemRepository;
import com.taek.daangn.platform.domain.vote.VoteRepository;
import com.taek.daangn.platform.web.dto.VoteSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final VoteItemRepository voteItemRepository;

    public String insert(VoteSaveRequestDto voteSaveRequestDto, String userId, String voteId) {

        Vote vote = new Vote();
        vote.insert(voteId, userId, voteSaveRequestDto.getPostId(), voteSaveRequestDto.getVoteDeadline(), voteSaveRequestDto.getVoteTitle(), voteSaveRequestDto.getVoteContent());

        voteRepository.save(vote).getVoteId();

        for (int i = 0; i<voteSaveRequestDto.getVoteItem().size(); i++){
            VoteItem voteItem = new VoteItem();
            voteItem.insert(voteId, voteSaveRequestDto.getVoteItem().get(i).getVoteItemName());
            voteItemRepository.save(voteItem);
        }

        return vote.getVoteId();

    }
}
