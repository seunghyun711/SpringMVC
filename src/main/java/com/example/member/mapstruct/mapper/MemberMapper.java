package com.example.member.mapstruct.mapper;

import com.example.member.Member;
import com.example.member.MemberPatchDto;
import com.example.member.MemberPostDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring") // 스프링 빈으로 등록된다
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
