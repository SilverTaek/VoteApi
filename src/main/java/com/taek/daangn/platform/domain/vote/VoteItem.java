package com.taek.daangn.platform.domain.vote;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public void insert(String voteId, String voteItemName, Long voteItemCount){
        this.voteId = voteId;
        this.voteItemName = voteItemName;
        this.voteItemCount = voteItemCount;
    }

    public void count(Long voteItemCount) {
        this.voteItemCount = voteItemCount;
    }
}
