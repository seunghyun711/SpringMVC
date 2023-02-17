package com.example.member;

import java.util.List;

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
        return member;
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
