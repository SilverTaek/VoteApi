package com.taek.daangn.platform.web;

import com.taek.daangn.platform.service.VoteService;
import com.taek.daangn.platform.web.dto.VoteSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping("/api/v1/votes")
    public String save(@RequestBody VoteSaveRequestDto voteSaveRequestDto) {
        return voteService.insert(voteSaveRequestDto);
    }
}
