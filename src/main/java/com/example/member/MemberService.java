package com.example.member;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    public Member createMember(Member member){
        Member createdMember = member;
        return createdMember;
    }

    public Member updateMember(Member member){
        Member updatedMember = member;
        return updatedMember;
    }

    public Member findMember(long memberId){
        Member member =
                new Member(memberId, "hgd@gmail.com", "홍길동", "010-1234-5678");
        return member;
    }

    public List<Member> findMembers(){
        List<Member> members = List.of(
                new Member(1, "hgd@gmail.com", "홍길동", "010-1234-5678"),
                new Member(2, "hsd@gmail.com", "홍길똥", "010-6788-1678")
        );
        return members;
    }

    public void deleteMember(long memberId){

    }
}
