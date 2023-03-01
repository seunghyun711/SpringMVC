package com.example.member.mapper;

import com.example.member.Member;
import com.example.member.MemberPatchDto;
import com.example.member.MemberPostDto;
import com.example.member.MemberResponseDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    // MemberPostDto -> Member
    Member memberPostDtoToMember(MemberPostDto memberPostDto);

    // MemberPatchDto -> Member
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);

    // Member -> MemberResponseDto
    MemberResponseDto memberToMemberResponseDto(Member member);

    List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);

}
