package com.taek.daangn.platform.domain.vote;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@DynamicInsert
@Entity
public class VoteItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteItemId;

    private String voteId;

    private String voteItemName;

    private Long voteItemCount;

    public VoteItem(String voteId, String voteItemName) {
        this.voteId = voteId;
        this.voteItemName = voteItemName;
    }

    public void count(Long voteItemCount) {
        this.voteItemCount = voteItemCount;
    }
}
