package com.example.member.mapper;

import com.example.member.Member;
import com.example.member.MemberPatchDto;
import com.example.member.MemberPostDto;
import org.springframework.stereotype.Component;

@Component // 스프링 빈으로 등록
public class MemberMapper {
    // MemberPostDto를 Member로 변환
    public Member memberPostDtoToMember(MemberPostDto memberPostDto){
        return new Member(0L,
                memberPostDto.getEmail(),
                memberPostDto.getName(),
                memberPostDto.getPhone());
    }

    // MemberPatchDto를 Member로 변경
    public Member memberPatchDtoToMember(MemberPatchDto memberPatchDto){
        return new Member(memberPatchDto.getMemberId(),
                null,
                memberPatchDto.getName(),
                memberPatchDto.getPhone());
    }

    // Member를 MemberResponseDto로 변환
    public MemberResponseDto memberResponseDto(Member member){
        return new MemberResponseDto(member.getMemberId(),
                member.getEmail(),
                member.getName(),
                member.getPhone());
    }
}
