package com.taek.daangn.platform.domain.vote;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Table(name = "vote_status")
@Entity
public class VoteStatus {

    @EmbeddedId
    private VoteStatusId voteStatusId;

    private String voteItemId;

    public void insert(VoteStatusId voteStatusId, String voteItemId) {
        this.voteStatusId = voteStatusId;
        this.voteItemId = voteItemId;
    }
}
