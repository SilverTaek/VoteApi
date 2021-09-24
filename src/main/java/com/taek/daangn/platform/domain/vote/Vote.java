package com.taek.daangn.platform.domain.vote;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Vote {

    @Id
    private String voteId;

    private String userId;

    private Long postId;

    private LocalDateTime voteDeadline;

    private String voteTitle;

    private String voteContent;

    @OneToMany
    private List<VoteItem> voteItem;

    @Builder
    public Vote(Long postId, String voteTitle, String voteContent, LocalDateTime voteDeadline, List<VoteItem> voteItems){
        this.postId = postId;
        this.voteTitle = voteTitle;
        this.voteContent = voteContent;
        this.voteDeadline = voteDeadline;
        this.voteItem = voteItems;
    }

    public void insert(String voteId, String userId, Long postId, LocalDateTime voteDeadline, String voteTitle, String voteContent){
        this.voteId = voteId;
        this.userId = userId;
        this.postId = postId;
        this.voteDeadline = voteDeadline;
        this.voteTitle = voteTitle;
        this.voteContent = voteContent;
    }

}
