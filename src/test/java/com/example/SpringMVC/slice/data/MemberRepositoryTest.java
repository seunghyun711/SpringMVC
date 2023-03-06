package com.example.SpringMVC.slice.data;

import com.example.member.Member;
import com.example.member.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest // MemberRepository의 기능을 정상적으로 사용하기 위한 Configuration을 스프링이 자동으로 구성해준다.
// 또한 DataJpaTest는 @Transactional 애너테이션을 포함하고 있어 하나의 테스트 케이스 실행이 종료되는 시점에 데이터베이스에 저장된 데이터는 롤백한다.
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    // 회원 저장 테스트
    @Test
    public void saveMemberTest() {
        // given
        Member member = new Member();
        member.setEmail("hong@com");
        member.setName("hong");
        member.setPhone("010-2222-2222");

        // when
        Member savedMember = memberRepository.save(member);

        // then
        // 회원 정보가 저장되었는지 검증
        assertNotNull(savedMember);
        assertTrue(member.getEmail().equals(savedMember.getEmail()));
        assertTrue(member.getName().equals(savedMember.getName()));
        assertTrue(member.getPhone().equals(savedMember.getPhone()));
    }

    // 회원 정보 조회 테스트
    @Test
    public void findByEmailTest() {
        // given
        Member member = new Member();
        member.setEmail("hong@com");
        member.setName("hong");
        member.setPhone("010-2222-2222");

        // when
        memberRepository.save(member);
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());

        // then
        assertTrue(findMember.isPresent());
        assertTrue(findMember.get().getEmail().equals(member.getEmail()));
    }
}
