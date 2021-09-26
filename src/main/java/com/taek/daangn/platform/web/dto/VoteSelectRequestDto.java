package com.taek.daangn.platform.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VoteSelectRequestDto {

    private Long postId;

    private String voteId;

    private Long voteItemId;

}
