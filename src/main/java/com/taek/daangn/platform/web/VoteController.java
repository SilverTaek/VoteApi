package com.taek.daangn.platform.web;

import com.taek.daangn.platform.common.UUIDgeneration;
import com.taek.daangn.platform.common.exception.VoteValidationException;
import com.taek.daangn.platform.service.VoteService;
import com.taek.daangn.platform.web.dto.VoteSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping("/api/v1/votes")
    public String save(@RequestHeader Map<String, String> map, @RequestBody VoteSaveRequestDto voteSaveRequestDto) {
        String userId = map.get("x-user-id");
        if(userId.length() != 4) {
            throw new VoteValidationException("사용자 아이디는 4자리 문자여야 합니다.");
        }
        UUIDgeneration uuiDgeneration = new UUIDgeneration();
        String voteId = uuiDgeneration.getUUID();

        return voteService.insert(voteSaveRequestDto, userId, voteId);
    }
}
