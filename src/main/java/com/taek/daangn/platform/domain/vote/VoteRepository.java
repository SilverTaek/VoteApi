package com.taek.daangn.platform.domain.vote;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, String> {

    public Vote findByVoteId(String voteId);

    public List<Vote> findByUserId(String userId);
}
