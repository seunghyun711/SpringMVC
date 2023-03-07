package com.example.SpringMVC.slice.mock;

import com.example.member.Member;
import com.example.member.MemberDto;
import com.example.member.MemberService;
import com.example.member.mapper.MemberMapper;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerMockTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;


    @MockBean // Application Context에 등록된 빈에 대한 Mockito Mock 객체를 생성하고 주입하는 역할
    private MemberService memberService; // MemberService 빈에 대한 Mock 객체를 생성해서 memberService 필드에 주입한다.


    @Autowired
    private MemberMapper mapper; // Mocking을 통해 만들어진 MockMemberService의 createMember()에서 리턴하는 Member 객체를 생성하기 위해 선언

    @Test
    void postMemberTest() throws Exception {
        // given
        MemberDto.Post post = new MemberDto.Post("hong@1com", "hong", "010-2313-4818");
        Member member = mapper.memberPostDtoToMember(post); // post 변수를 Member 객체로 변환
        member.setMemberId(1L); // 실제 createMember()의 리턴 값에는 memberId가 포함되어 있는데 memberId는 response의 Location header에 포함이 되어야 하기 때문에 MockMemberService의 createMember()에도 memberId를 리턴할 수 있게 memberId 추가

        // mockito에서 지원하는 Stubbing
        // Mock 객체가 특정 값을 리턴하는 동작을 지정하는데 사용. Mockito에서 지원하는 when() 동일한 기능
        // Mock 객체인 memberService 객체로 createMember()메서드를 호출하여 사용하도록 정의
        given(memberService.createMember(Mockito.any(Member.class))).willReturn(member); // MockMemberService의 createMember()가 리턴할 stub 데이터
        /*
         Mockito.any(Member.class)
         -> createMember()의 파라미터로 Mockito에서 지원하는 변수 타입 중하나
         -> 실제 MemberService에서 createMember()의 파라미터 타입은 Member 따라서 Mockito.any()에 Member.class 타입으로 지정
         */

        String content = gson.toJson(post);

        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/v1/members")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", Matchers.is(startsWith("/v1/members"))));
    }

}
