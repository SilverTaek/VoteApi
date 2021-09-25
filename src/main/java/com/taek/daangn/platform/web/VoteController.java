package com.taek.daangn.platform.web;

import com.taek.daangn.platform.common.UUIDgeneration;
import com.taek.daangn.platform.common.exception.VoteValidationException;
import com.taek.daangn.platform.service.VoteItemService;
import com.taek.daangn.platform.service.VoteSelectService;
import com.taek.daangn.platform.service.VoteService;
import com.taek.daangn.platform.web.dto.VoteSaveRequestDto;
import com.taek.daangn.platform.web.dto.VoteSelectRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
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
        if(userId.length() != 4) {
            throw new VoteValidationException("사용자 아이디는 4자리 문자여야 합니다.");
        }
        UUIDgeneration uuiDgeneration = new UUIDgeneration();
        String voteId = uuiDgeneration.getUUID();

        return voteService.insert(voteSaveRequestDto, userId, voteId);
    }

    @PostMapping("/api/v1/vote/item")
    public String voteSelect(@RequestHeader Map<String, String> map, @RequestBody VoteSelectRequestDto voteSelectRequestDto) throws ValidatorException {
        System.out.println("어디서 실패?");
        if(voteSelectService.checkDeadline(voteSelectRequestDto)){
            return "실패";
        }
        System.out.println("어디서 실패?");
        String userId = map.get("x-user-id");
        if(userId.length() != 4) {
            throw new VoteValidationException("사용자 아이디는 4자리 문자여야 합니다.");
        }
        System.out.println("어디서 실패?");
        if(!voteSelectService.isCheckedVoteItem(voteSelectRequestDto))
            throw new VoteValidationException("투표와 선택한 항목이 일치하지 않습니다.");
        System.out.println("어디서 실패?");

        if(voteSelectService.isCheckedUser(userId, voteSelectRequestDto)){
            System.out.println("여긴아니야?");
            voteSelectService.select(userId, voteSelectRequestDto);
        } else {
            throw new VoteValidationException("이미 투표를 완료했습니다. 투표는 한번만 가능합니다.");
        }
        System.out.println("어디서 실패?");
        return "성공";
    }
}
