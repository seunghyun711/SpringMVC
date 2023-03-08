package com.example.SpringMVC.restdocs.member;

import com.example.member.Member;
import com.example.member.MemberController;
import com.example.member.MemberDto;
import com.example.member.MemberService;
import com.example.member.mapper.MemberMapper;
import com.google.gson.Gson;
import org.apiguardian.api.API;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.headers.HeaderDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static com.example.SpringMVC.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.example.SpringMVC.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class) // Controller 테스트 위한 전용 애너테이션
@MockBean(JpaMetamodelMappingContext.class) // JPA에서 사용하는 빈들을 Mock 객체로 주입하는 설정
@AutoConfigureRestDocs // Spring Rest Docs에 대한 자동 구성
public class MemberControllerRestDocsTest {

    @MockBean // 테스트 대상 Controller 클래스가 의존하는 객체를 Mock Bean 객체로 주입 받기
    private MemberService memberService;

    @MockBean // MemberMapper로 MemberDto.Post와 Member 간 실제 매핑 작업이 일어나게 되지 않게 Mock 객체로 주입 받는다.
    private MemberMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Test
    public void postMemberTest() throws  Exception {
        // given
        MemberDto.Post post = new MemberDto.Post("hong@com", "hong", "010-2221-1111");
        String content = gson.toJson(post);


        given(mapper.memberPostDtoToMember(Mockito.any(MemberDto.Post.class))).willReturn(new Member());

        Member mockResultMember = new Member();
        mockResultMember.setMemberId(1L);
        given(memberService.createMember(Mockito.any(Member.class))).willReturn(mockResultMember);


        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/v1/members")// request 전송
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isCreated()) // response에 대한 기대 값 검증
                .andExpect(header().string("Location", is(startsWith("/v1/members/"))))
                .andDo(document( // API 문서 스펙 정보를 전달 받아 실질적인 문서화 작업을 수행한다.
                        "post-member", // API 문서 스니핏의 식별자 역할 문서 스니핏은 post-member 디렉토리 하위에 생성된다.
                        getRequestPreProcessor(), // request에 해당하는 문서영역 전처리 역할
                        getResponsePreProcessor(), // response에 해당하는 문서영역 전처리 역할
                        requestFields( // 문서로 표현될 request body
                                List.of(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("phone").type(JsonFieldType.STRING).description("폰 번호")
                                )
                        ),
                        responseHeaders( // 문서로 표현될 response header
                                headerWithName(HttpHeaders.LOCATION).description("Location header, 등록된 리소스의 URI") // HTTP response의 Location header를 의미
                        )
                ));
    }
}
