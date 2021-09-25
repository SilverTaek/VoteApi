package com.taek.daangn.platform.web.dto;

import com.taek.daangn.platform.domain.vote.VoteItem;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VoteItemSaveRequestDto {

    private String voteItemName;

    public VoteItem toEntity(){
        return VoteItem.builder()
                .voteItemName(voteItemName)
                .build();
    }
}
