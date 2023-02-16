package com.example.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/members") // produces 설정 제거됨
public class MemberController {
    // ResponseEntity클래스로 응답 데이터를 래핑하여 http상태를 더 보기 쉽게 리턴할 수 있다.

    // 회원 정보 등록
    @PostMapping
    public ResponseEntity postMember(@RequestBody MemberPostDto memberPostDto) { // dto클래스 적용
        // @RequestParam : 클라이언트 쪽에서 전송하는 요청 데이터를 쿼리 파라미터, 폼 데이터 형식으로 전송하면 이를 서버에서 전달 받을 때 사용하는 애너테이션
        // JSON 문자열을 직접 입력하지 않고, map으로 대체

        // 리턴 값을 ResponseEntity객체로 변경
        return new ResponseEntity<>(memberPostDto, HttpStatus.CREATED);
    }

    // 회원 정보 수정
    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") long memberId,
                                      @RequestBody MemberPatchDto memberPatchDto){
        memberPatchDto.setMemberId(memberId);
        memberPatchDto.setName("hong");

        return new ResponseEntity<>(memberPatchDto,HttpStatus.OK);
    }

    // memberId로 해당 회원 정보 조회
    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id")long memberId) {
        System.out.println("# memberId: " + memberId);

        // 리턴 값을 ResponseEntity객체로 변경
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers() {
        System.out.println("# get Members");

        // 리턴 값을 ResponseEntity객체로 변경
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 회원 정보 삭제
    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") long memberId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
