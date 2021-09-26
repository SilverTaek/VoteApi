package com.taek.daangn.platform.web;

import com.taek.daangn.platform.common.UUIDgeneration;
import com.taek.daangn.platform.common.exception.VoteValidationException;
import com.taek.daangn.platform.domain.vote.Vote;
import com.taek.daangn.platform.service.VoteSelectService;
import com.taek.daangn.platform.service.VoteService;
import com.taek.daangn.platform.web.dto.VoteResponseDto;
import com.taek.daangn.platform.web.dto.VoteSaveRequestDto;
import com.taek.daangn.platform.web.dto.VoteSelectRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;
    private final VoteSelectService voteSelectService;

    @PostMapping("/api/v1/votes")
    public String voteSave(@RequestHeader Map<String, String> map, @RequestBody VoteSaveRequestDto voteSaveRequestDto) {
        return voteService.insert(voteSaveRequestDto, map.get("x-user-id"), new UUIDgeneration().getUUID());
    }

    @GetMapping("/api/v1/votes/{postId}/{voteId}")
    public VoteResponseDto findVoteById(@RequestHeader Map<String, String> map, @PathVariable Long postId, @PathVariable String voteId) {
        return voteService.findVoteById(map.get("x-user-id"), postId, voteId);
    }

    @PostMapping("/api/v1/vote/item")
    public Map<String, String> voteSelect(@RequestHeader Map<String, String> map, @RequestBody VoteSelectRequestDto voteSelectRequestDto) {
        Map<String, String> response = new HashMap<>();

        if (voteSelectService.checkDeadline(voteSelectRequestDto)) {
            response.put("Reason", "마감 된 투표입니다.");
            response.put("Result", "실패");
            return response;
        }

        if (voteSelectService.isCheckedUser(map.get("x-user-id"), voteSelectRequestDto)) {
            voteSelectService.select(map.get("x-user-id"), voteSelectRequestDto);
            response.put("Result", "성공");
        } else {
            throw new VoteValidationException("이미 투표를 완료했습니다. 투표는 한번만 가능합니다.");
        }

        return response;
    }

}
