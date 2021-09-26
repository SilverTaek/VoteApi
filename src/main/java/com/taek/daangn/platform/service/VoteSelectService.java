package com.taek.daangn.platform.service;

import com.taek.daangn.platform.common.Validation;
import com.taek.daangn.platform.common.exception.VoteValidationException;
import com.taek.daangn.platform.domain.vote.*;
import com.taek.daangn.platform.web.dto.VoteSelectRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    static Validation validation = new Validation();

    @Transactional
    public void select(String userId, VoteSelectRequestDto voteSelectRequestDto) {

        validation.userIdValidation(userId);

        if (!isCheckedVoteItem(voteSelectRequestDto)) throw new VoteValidationException("투표와 선택한 항목이 일치하지 않습니다.");

        VoteStatusId voteStatusId = new VoteStatusId(userId, voteSelectRequestDto.getVoteId());
        VoteStatus voteStatus = new VoteStatus(voteStatusId, voteSelectRequestDto.getVoteItemId());

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

    @Transactional
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
