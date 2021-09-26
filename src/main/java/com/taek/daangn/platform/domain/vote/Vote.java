package com.taek.daangn.platform.domain.vote;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
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

    private LocalDateTime voteDeadline;

    private String voteTitle;

    private String voteContent;

    @OneToMany
    private List<VoteItem> voteItem;
    //그냥 생성자로 만들자 , 정적 팩토리 메서드

    @Builder
    public Vote(String voteId, Long postId, LocalDateTime voteDeadline, String voteTitle, String voteContent) {
        this.voteId = voteId;
        this.postId = postId;
        this.voteDeadline = voteDeadline;
        this.voteTitle = voteTitle;
        this.voteContent = voteContent;

    }

    public void setVoteIdAndUserId(String voteId, String userId) {
        this.voteId = voteId;
        this.userId = userId;
    }

    @PrePersist
    public void isCheckedDeadline(){
        if(this.voteDeadline == null) {
            this.voteDeadline = LocalDateTime.now().plusDays(1);
        }
    }
}
