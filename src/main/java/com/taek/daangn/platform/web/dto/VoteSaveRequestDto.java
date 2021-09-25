package com.taek.daangn.platform.web.dto;

import com.taek.daangn.platform.domain.vote.VoteItem;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class VoteSaveRequestDto {
    private Long postId;

    private String voteTitle;

    private String voteContent;

    private List<VoteItem> voteItem;

    private LocalDate voteDeadline;

}
