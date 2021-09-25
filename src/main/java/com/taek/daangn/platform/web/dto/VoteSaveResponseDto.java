package com.taek.daangn.platform.web.dto;

import com.taek.daangn.platform.domain.vote.Vote;

import java.time.LocalDate;

public class VoteSaveResponseDto {
    private String userId;

    private String voteTitle;

    private String voteContent;

    private LocalDate voteDeadline;

    public VoteSaveResponseDto(Vote vote) {
        this.userId = vote.getUserId();
        this.voteTitle = vote.getVoteTitle();
        this.voteContent = vote.getVoteContent();
        this.voteDeadline = vote.getVoteDeadline();
    }
}
