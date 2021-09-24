package com.taek.daangn.platform.service;

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

    public String insert(VoteSaveRequestDto voteSaveRequestDto) {

        voteItemRepository.save(voteSaveRequestDto.getVoteItem());

        return voteRepository.save(voteSaveRequestDto.toEntity()).getVoteId();

    }
}
