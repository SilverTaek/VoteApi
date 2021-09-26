package com.taek.daangn.platform.domain.vote;

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

    @Column(name = "vote_id")
    private String voteId;

    public VoteStatusId(String userId, String voteId) {
        this.userId = userId;
        this.voteId = voteId;
    }
}
