package com.taek.daangn.platform.domain.vote;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteStatusRepository extends JpaRepository<VoteStatus, VoteStatusId> {

    public VoteStatus findByVoteStatusId(VoteStatusId voteStatusId);
}
