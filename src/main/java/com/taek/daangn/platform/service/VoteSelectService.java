package com.taek.daangn.platform.service;

import com.taek.daangn.platform.domain.vote.*;
import com.taek.daangn.platform.web.dto.VoteSelectRequestDto;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        VoteStatusId voteStatusId = new VoteStatusId();
        voteStatusId.insert(userId, voteSelectRequestDto.getVoteItemId());


        voteStatus.insert(voteStatusId);

        voteStatusRepository.save(voteStatus);

        VoteItem voteItem = voteItemRepository.findByVoteItemId(voteSelectRequestDto.getVoteItemId());
        Long count = voteItem.getVoteItemCount() + 1L;
        voteItem.count(count);

        voteItemRepository.save(voteItem);
    }

    public boolean checkDeadline(VoteSelectRequestDto voteSelectRequestDto){
        LocalDate currentDate = LocalDate.now();

        Vote vote = voteRepository.findByVoteId(voteSelectRequestDto.getVoteId());

       return currentDate.isAfter(vote.getVoteDeadline());
    }

    public boolean isCheckedVoteItem(VoteSelectRequestDto voteSelectRequestDto){
        Vote vote = voteRepository.findByVoteId(voteSelectRequestDto.getVoteId());
        VoteItem voteItem = voteItemRepository.findByVoteItemId(voteSelectRequestDto.getVoteItemId());

        if(!vote.getVoteId().equals(voteItem.getVoteId())){
            return false;
        }

        return true;
    }

    public boolean isCheckedUser(String userId, VoteSelectRequestDto voteSelectRequestDto) {
        System.out.println("어디서 실패?");
        VoteStatusId voteStatusId = new VoteStatusId(userId, voteSelectRequestDto.getVoteItemId());
        //voteStatusId.insert(userId, voteSelectRequestDto.getVoteItemId());
        System.out.println(voteStatusId);

        Optional<VoteStatus> voteStatus = voteStatusRepository.findById(voteStatusId);
        System.out.println("voteStatus 값은 = " + voteStatus);
        List<VoteStatus> voteStatusList = voteStatusRepository.findAll();

        for (VoteStatus voteStatusSingle:voteStatusList) {
            System.out.println(voteStatusSingle+"1");
            System.out.println(voteStatus+"2");
            if(voteStatus.equals(voteStatusSingle)){
                return false;
            }
        }

        return true;
    }
}
