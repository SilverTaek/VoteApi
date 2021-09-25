package com.taek.daangn.platform.domain.vote;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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

    private LocalDate voteDeadline;

    private String voteTitle;

    private String voteContent;

    @OneToMany
    private List<VoteItem> voteItem;

    public void insert(String voteId, String userId, Long postId, LocalDate voteDeadline, String voteTitle, String voteContent) {
        this.voteId = voteId;
        this.userId = userId;
        this.postId = postId;
        this.voteDeadline = voteDeadline;
        this.voteTitle = voteTitle;
        this.voteContent = voteContent;
    }

}
