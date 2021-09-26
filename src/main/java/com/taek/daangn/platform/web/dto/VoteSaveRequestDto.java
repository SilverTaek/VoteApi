package com.taek.daangn.platform.web.dto;

import com.taek.daangn.platform.domain.vote.Vote;
import com.taek.daangn.platform.domain.vote.VoteItem;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class VoteSaveRequestDto {
    private Long postId;

    private String voteTitle;

    private String voteContent;

    private List<VoteItem> voteItem;

    private LocalDateTime voteDeadline;

    public Vote toEntity(){
        return Vote.builder()
                .postId(postId)
                .voteTitle(voteTitle)
                .voteContent(voteContent)
                .voteDeadline(voteDeadline)
                .build();
    }
}
