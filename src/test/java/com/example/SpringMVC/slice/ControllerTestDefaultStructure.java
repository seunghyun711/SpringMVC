package com.example.SpringMVC.slice;

import com.example.member.MemberDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest // 스프링 부트 기반의 애플리케이션을 테스트하기 위한 Application Context 생성
@AutoConfigureMockMvc // Controller 테스트를 위한 애플리케이션의 자동 구성 작업을 하는 역할
public class ControllerTestDefaultStructure {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson; // Json 변환 라이브러리

    // HTTP Post Request 테스트
    @Test
    void postMemberTest() throws  Exception {
        // given
        MemberDto.Post post = new MemberDto.Post("hong@com", "hong", "010-2222-2222");
        String content = gson.toJson(post); // MemberDto.Post 객체를 Json포맷으로 변환

        // when
        ResultActions actions =
                mockMvc.perform( // 대상 컨트롤러의 핸들러 메서드에 요청 전송을 위한 메서드 perform()
                        post("/v1/members") // post()메서드로 HTTP POST METHOD의 request URL 설정
                                .accept(MediaType.APPLICATION_JSON) // 클라이언트 쪽에서 리턴 받을 응답 데이터 타입으로 json 타입 설정
                                .contentType(MediaType.APPLICATION_JSON) // 서버 쪽에서 처리 가능한 Content Type으로 Json 타입 설정
                                .content(content) // request body 데이터 설정
                );

        // then
        actions // perform() 메서드는 ResultActions 타입의 객체를 리턴하고 이 객체를 이용해 전송한 request에 대한 검증 수행한다.
                .andExpect(status().isCreated()) // request status가 201인지 매치시킨다. -> 백엔드에 리소스인 회원 정보가 잘 설장 되었는지 검증
                .andExpect(header().string("Location", is(startsWith("/v1/members")))); // Location header가 예상하는 값과 일치한다 -> 백엔드 측에 리소스가 잘 생성되었음을 의미
    }

    @Test
    void getMember() throws Exception {
        // given
        // 테스트를 위한 데이터를 미리 생성
        MemberDto.Post post = new MemberDto.Post("hong@com", "hong", "010-2222-2222");
        String postContent = gson.toJson(post);

        ResultActions actions =
                mockMvc.perform(
                        post("/v1/members")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(postContent)
                );

        // postMember()의 response에 전달되는 Location header 값을 가져오는 로직
        String location = actions.andReturn().getResponse().getHeader("Location"); // "/v1/members/1

        // when / then
        mockMvc.perform(
                        get(location) // Location header 값을 get으로 전달
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())// HTTP status가 200 ok 인지 검증
                // getMember() 핸들러 메서드에서 리턴하는 response body의 각 프로퍼티(email,name,phone)의 값을 검장하는 코드
                .andExpect(jsonPath("$.email").value(post.getEmail()))
                .andExpect(jsonPath("$.name").value(post.getName()))
                .andExpect(jsonPath("$.phone").value(post.getPhone()));
    }
}
