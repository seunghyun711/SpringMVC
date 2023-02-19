package com.example.member.mapstruct.mapper;

import com.example.member.Member;
import com.example.member.MemberPatchDto;
import com.example.member.MemberPostDto;
import com.example.member.MemberResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // MapStructd의 매퍼 인터페이스로 정의가 되었으며
// componentModel = "spring"은 스프링의 빈으로 등록이 되었음을 알림
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    MemberResponseDto memberResponseDto(Member member);
}
