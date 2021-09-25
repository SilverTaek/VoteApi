package com.taek.daangn.platform.domain.vote;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteItemRepository extends JpaRepository<VoteItem, Long> {

    public void save(List<VoteItem> voteItems);

    public VoteItem findByVoteItemId(Long voteItemId);

    public List<VoteItem> findByVoteId(String voteId);
}
