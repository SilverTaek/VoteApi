package com.taek.daangn.platform.domain.vote;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Builder
@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String voteId;

    private String userId;

    private Long postId;

    private LocalDateTime voteDeadline;

    private String voteTitle;

    private String voteContent;

    @OneToMany
    private List<VoteItem> voteItem;
}
