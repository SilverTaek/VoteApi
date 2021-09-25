package com.taek.daangn.platform.domain.vote;


import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, String> {
    public Vote findByVoteId(String voteId);
}
