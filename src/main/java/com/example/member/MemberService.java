package com.example.member;

import com.example.exception.BusinessLogicException;
import com.example.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 스프링 빈으로 등록된다.
public class MemberService {
    // 회원 생성
    public Member createMember(Member member) {
        Member createdMember = member;
        return createdMember;
    }

    // 회원 정보 수정
    public Member updateMember(Member member) {
        Member updatedMember = member;
        return updatedMember;
    }

    // id로 회원 정보 조회
    public Member findMember(long memberId) {
        Member member = new Member(memberId, "hong@.com", "hong", "011-2222-2222");
        throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
    }

    // 전체 회원 정보 조회
    public List<Member> findMembers() {
        List<Member> members = List.of(
                new Member(1, "hong@.com", "hong", "011-2222-2222"),
                new Member(2, "seung@.com", "seung", "011-3333-3333")
        );
        return members;
    }

    // 회원 정보 삭제
    public void deleteMember(long memberId) {

    }
}
