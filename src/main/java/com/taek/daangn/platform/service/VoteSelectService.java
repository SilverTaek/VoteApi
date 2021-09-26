package com.taek.daangn.platform.service;

import com.taek.daangn.platform.domain.vote.*;
import com.taek.daangn.platform.web.dto.VoteSelectRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VoteSelectService {

    private final VoteStatusRepository voteStatusRepository;
    private final VoteItemRepository voteItemRepository;
    private final VoteRepository voteRepository;

    public void select(String userId, VoteSelectRequestDto voteSelectRequestDto) {
        VoteStatus voteStatus = new VoteStatus();
        VoteStatusId voteStatusId = new VoteStatusId(userId, voteSelectRequestDto.getVoteId());


        voteStatus.insert(voteStatusId, voteSelectRequestDto.getVoteId());

        voteStatusRepository.save(voteStatus);

        VoteItem voteItem = voteItemRepository.findByVoteItemId(voteSelectRequestDto.getVoteItemId());
        Long count = voteItem.getVoteItemCount() + 1L;
        voteItem.count(count);

        voteItemRepository.save(voteItem);
    }

    public boolean checkDeadline(VoteSelectRequestDto voteSelectRequestDto) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        Vote vote = voteRepository.findByVoteId(voteSelectRequestDto.getVoteId());

        return currentDateTime.isAfter(vote.getVoteDeadline());

    }

    public boolean isCheckedVoteItem(VoteSelectRequestDto voteSelectRequestDto) {
        Vote vote = voteRepository.findByVoteId(voteSelectRequestDto.getVoteId());
        VoteItem voteItem = voteItemRepository.findByVoteItemId(voteSelectRequestDto.getVoteItemId());

        if (!vote.getVoteId().equals(voteItem.getVoteId())) {
            return false;
        }

        return true;
    }

    public boolean isCheckedUser(String userId, VoteSelectRequestDto voteSelectRequestDto) {

        VoteStatusId voteStatusId = new VoteStatusId(userId, voteSelectRequestDto.getVoteId());
        VoteStatus voteStatus = voteStatusRepository.findByVoteStatusId(voteStatusId);
        if (voteStatus != null) {
            return false;
        }

        return true;
    }
}
