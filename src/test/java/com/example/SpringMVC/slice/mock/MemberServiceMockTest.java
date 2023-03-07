package com.example.SpringMVC.slice.mock;

import com.example.exception.BusinessLogicException;
import com.example.member.Member;
import com.example.member.MemberRepository;
import com.example.member.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

// 스프링을 사용하지 않고 Junit에서 Mockito 기능을 사용하기 위해 사용되는 애너테이션
@ExtendWith(MockitoExtension.class)
public class MemberServiceMockTest {
    @Mock // 해당 객체를 Mock 객체로 생성
    private MemberRepository memberRepository;

    // Mock 객체 주입 즉, memberService 객체는 주입 받은 memberRepository Mock 객체를 포함
    @InjectMocks
    private MemberService memberService;

    @Test
    public void createMemberTest(){
        // given
        Member member = new Member("hongse@com","hong","010-2333-3222");

        // memberRepository Mock 객체로 Stubbing
        given(memberRepository.findByEmail(Mockito.anyString())).willReturn(Optional.of(member));

        // when / then
        assertThrows(BusinessLogicException.class, () -> memberService.createMember(member));
    }

}
