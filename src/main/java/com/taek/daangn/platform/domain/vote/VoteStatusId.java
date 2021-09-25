package com.taek.daangn.platform.domain.vote;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Embeddable
public class VoteStatusId implements Serializable {
    @Column(name = "user_id")
    private String userId;
    @Column(name = "vote_item_id")
    private Long voteItemId;

    public VoteStatusId(String userId, Long voteItemId){
        this.userId = userId;
        this.voteItemId = voteItemId;
    }

    public void insert(String userId, Long voteItemId){
        this.userId = userId;
        this.voteItemId = voteItemId;
    }
}
