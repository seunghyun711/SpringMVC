package com.example.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/members") // produces 설정 제거됨
@Validated // @PathVaraible이 추가된 변수에 유효성 검증이 정상적으로 수행되려면 해당 애너테이션을 붙인다.
public class MemberController {
    // ResponseEntity클래스로 응답 데이터를 래핑하여 http상태를 더 보기 쉽게 리턴할 수 있다.

    private MemberService memberService; // MemberService 의존

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원 정보 등록
    // @Valid : dto 클래스에서 유효성 검사가 실행되려면 @Vaild애너테이션을 붙여준다.
    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberPostDto) { // dto클래스 적용
        // @RequestParam : 클라이언트 쪽에서 전송하는 요청 데이터를 쿼리 파라미터, 폼 데이터 형식으로 전송하면 이를 서버에서 전달 받을 때 사용하는 애너테이션
        // JSON 문자열을 직접 입력하지 않고, map으로 대체
        Member member = new Member();
        member.setEmail(memberPostDto.getEmail());
        member.setName(memberPostDto.getName());
        member.setPhone(memberPostDto.getPhone());

        Member response = memberService.createMember(member);

        // 리턴 값을 ResponseEntity객체로 변경
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 회원 정보 수정
    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Min(1) long memberId,
                                      @Valid @RequestBody MemberPatchDto memberPatchDto){
        memberPatchDto.setMemberId(memberId);

        Member member = new Member();
        member.setMemberId(memberPatchDto.getMemberId());
        member.setName(memberPatchDto.getName());
        member.setPhone(memberPatchDto.getPhone());

        Member response = memberService.updateMember(member);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    // memberId로 해당 회원 정보 조회
    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive long memberId) {
        Member response = memberService.findMember(memberId);

        // 리턴 값을 ResponseEntity객체로 변경
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers() {
        List<Member> response =  memberService.findMembers();

        // 리턴 값을 ResponseEntity객체로 변경
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    // 회원 정보 삭제
    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive long memberId){
        System.out.println("# delete member");

        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
