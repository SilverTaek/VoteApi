package com.taek.daangn.platform.domain.vote;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class VoteItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteItemId;

    private String voteId;

    private String voteItemName;

    private Long voteItemCount;

    @Builder
    public VoteItem(String voteItemName){
        this.voteItemName = voteItemName;
    }

    public void insert(String voteId, String voteItemName, Long voteItemCount){
        this.voteId = voteId;
        this.voteItemName = voteItemName;
        this.voteItemCount = voteItemCount;
    }

    public void count(Long voteItemCount) {
        this.voteItemCount = voteItemCount;
    }
}
