package com.taek.daangn.platform.common;

import com.taek.daangn.platform.common.exception.VoteValidationException;
import com.taek.daangn.platform.domain.vote.Vote;

public class Validation {

    public void userIdValidation(String userId) {
        if (userId.length() != 4) {
            throw new VoteValidationException("사용자 ID는 4자리 문자여야 합니다.");
        }
    }

    public void postIdValidation(Long postId) {
        if (postId > Long.MAX_VALUE || postId < Long.MIN_VALUE)
            throw new VoteValidationException("게시글 ID는 64비트의 Long 타입이여야 합니다.");
    }

    public void voteItemValidation(int count) {
        if (count < 2 || count > 100) {
            throw new VoteValidationException("투표 항목은 최소 2개 최대 100개까지 입력 가능합니다.");
        }
    }

    public void isUsedVoteId(Vote isUsedVoteId) {
        if (isUsedVoteId != null) {
            throw new VoteValidationException("투표 ID가 중복되었습니다.");
        }
    }
}
