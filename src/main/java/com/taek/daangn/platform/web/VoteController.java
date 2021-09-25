package com.taek.daangn.platform.web;

import com.taek.daangn.platform.common.UUIDgeneration;
import com.taek.daangn.platform.common.exception.VoteValidationException;
import com.taek.daangn.platform.service.VoteItemService;
import com.taek.daangn.platform.service.VoteSelectService;
import com.taek.daangn.platform.service.VoteService;
import com.taek.daangn.platform.web.dto.VoteResponseDto;
import com.taek.daangn.platform.web.dto.VoteSaveRequestDto;
import com.taek.daangn.platform.web.dto.VoteSelectRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sun.security.validator.ValidatorException;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;
    private final VoteSelectService voteSelectService;

    @PostMapping("/api/v1/votes")
    public String voteSave(@RequestHeader Map<String, String> map, @RequestBody VoteSaveRequestDto voteSaveRequestDto) {
        String userId = map.get("x-user-id");
        if (userId.length() != 4) {
            throw new VoteValidationException("사용자 아이디는 4자리 문자여야 합니다.");
        }
        UUIDgeneration uuiDgeneration = new UUIDgeneration();
        String voteId = uuiDgeneration.getUUID();

        return voteService.insert(voteSaveRequestDto, userId, voteId);
    }

    @GetMapping("/api/v1/votes/{postId}/{voteId}")
    public VoteResponseDto findVoteById(@RequestHeader Map<String, String> map, @PathVariable Long postId, @PathVariable String voteId) {
        String userId = map.get("x-user-id");
        if (userId.length() != 4) {
            throw new VoteValidationException("사용자 아이디는 4자리 문자여야 합니다.");
        }

        return voteService.findVoteById(userId, postId, voteId);
    }

    @PostMapping("/api/v1/vote/item")
    public String voteSelect(@RequestHeader Map<String, String> map, @RequestBody VoteSelectRequestDto voteSelectRequestDto) throws ValidatorException {

        if (voteSelectService.checkDeadline(voteSelectRequestDto)) {
            return "실패";
        }

        String userId = map.get("x-user-id");
        if (userId.length() != 4) {
            throw new VoteValidationException("사용자 아이디는 4자리 문자여야 합니다.");
        }

        if (!voteSelectService.isCheckedVoteItem(voteSelectRequestDto))
            throw new VoteValidationException("투표와 선택한 항목이 일치하지 않습니다.");


        if (voteSelectService.isCheckedUser(userId, voteSelectRequestDto)) {
            voteSelectService.select(userId, voteSelectRequestDto);
        } else {
            throw new VoteValidationException("이미 투표를 완료했습니다. 투표는 한번만 가능합니다.");
        }

        return "성공";
    }


}
