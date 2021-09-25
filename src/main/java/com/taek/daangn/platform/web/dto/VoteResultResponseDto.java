package com.taek.daangn.platform.web.dto;

import com.taek.daangn.platform.domain.vote.VoteItem;

public class VoteResultResponseDto {

    private String voteItemName;
    private Long voteItemCount;

    public VoteResultResponseDto(VoteItem voteItem){
        this.voteItemName = voteItem.getVoteItemName();
        this.voteItemCount = voteItem.getVoteItemCount();
    }
}
