package com.taek.daangn.platform.domain.vote;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteStatusRepository extends JpaRepository<VoteStatus, VoteStatusId> {

   // public void save(VoteStatusId voteStatusId);

    public VoteStatus findByVoteStatusId(VoteStatusId voteStatusId);

    public List<VoteStatus> findAllByVoteStatusId_UserId(String userId);
}
