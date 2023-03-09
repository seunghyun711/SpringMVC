package com.example.member.mapper;

import com.example.member.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    // MemberPostDto -> Member
    Member memberPostDtoToMember(MemberDto.Post requestBody);

    // MemberPatchDto -> Member
    Member memberPatchDtoToMember(MemberDto.Patch requestBody);

    // Member -> MemberResponseDto
//    @Mapping(source = "stamp.stampCount",target = "stampCount") // stamp 엔티티의 stampCount필드로 매핑
    MemberDto.Response memberToMemberResponseDto(Member member);

    List<MemberDto.Response> membersToMemberResponses(List<Member> members);

}
