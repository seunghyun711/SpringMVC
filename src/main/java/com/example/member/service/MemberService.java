package com.example.member.service;

import com.example.exception.BusinessLogicException;
import com.example.exception.ExceptionCode;
import com.example.member.Member;
import com.example.member.Repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // 스프링 빈으로 등록된다.
public class MemberService {
    private MemberRepository memberRepository;

    // MemberReposotirou DI
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 생성
    public Member createMember(Member member) {
        // 이미 등록된 이메일인지 검증
        verifyExistsEmail(member.getEmail());
        // 회원 정보 저장
        return memberRepository.save(member);
    }

    // 회원 정보 수정
    public Member updateMember(Member member) {
        // 존재하는 회원인지 검증
        Member findMember = findVerifiedMember(member.getMemberId());
        // 이름 정보와 전화 번호 정보 업데이트
        Optional.ofNullable(member.getName()) // null인 경우
                .ifPresent(name -> findMember.setName(name));
        Optional.ofNullable(member.getPhone())
                .ifPresent(phone -> findMember.setPhone(phone));

        // 회원 정보 업데이트
        return memberRepository.save(findMember);
    }

    // id로 회원 정보 조회
    public Member findMember(long memberId) {
        return findVerifiedMember(memberId);
    }

    // 전체 회원 정보 조회
    public List<Member> findMembers() {
        return (List<Member>) memberRepository.findAll();
    }

    // 회원 정보 삭제
    public void deleteMember(long memberId) {
        Member findMember = findVerifiedMember(memberId);

        // 특정 회원 정보 삭제
        memberRepository.deleteById(memberId);
    }

    // 이미 존재하는 회원인지 검증
    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    // 이미 등록된 이메일 주소인지 검증
    public void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        }
    }
}
