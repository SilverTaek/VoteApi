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

    private Long voteItemId;

    public VoteStatus(VoteStatusId voteStatusId, Long voteItemId) {
        this.voteStatusId = voteStatusId;
        this.voteItemId = voteItemId;
    }
}
