package com.example.member;

import com.example.exception.BusinessLogicException;
import com.example.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional // 트랜잭션 적용
public class MemberService {
   private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member){
        // 이메일 검증
        verifyExistsEmail(member.getEmail());
        return memberRepository.save(member);
    }

    @Transactional(propagation = Propagation.REQUIRED) // 현재 진행 중인 트랜잭션이 존재하면 그 트랜잭션 사용하고, 존재하지 않으면 새 트랜잭션을 생성한다.
    public Member updateMember(Member member){
        // 회원 검증(id)
        Member findMember = findVerifiedMember(member.getMemberId());
        Optional.ofNullable(member.getName())
                .ifPresent(name -> findMember.setName(name));
        Optional.ofNullable(member.getPhone())
                .ifPresent(phone -> findMember.setPhone(phone));

        // 회원 정보 업데이트된 시간 추가
        findMember.setModifiedAt(LocalDateTime.now());
        return memberRepository.save(findMember);
    }

    /*
    @Tractional(readOnly = true)로 설정하면 commit이 실행되면 영속성 컨텍스트를 flush 하지 않는다.
    flush 처리를 하지 않고 스냅샷 생성도 하지 않는다, 따라서 데이터 변경이 없는 조회 메서드에 사용하면
    불필요한 동작을 줄일 수 있어 효율적이다.
     */
    @Transactional(readOnly = true) // 메서들 레밸 트랜젝션 적용
    public Member findMember(long memberId){
        return findVerifiedMember(memberId);
    }

    public Page<Member> findMembers(int page, int size){
        return memberRepository.findAll(PageRequest.of(page, size, Sort.by("memberId").descending()));
    }

    public void deleteMember(long memberId){
        Member findMember = findVerifiedMember(memberId);
        memberRepository.delete(findMember);
    }

    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) { // 현재 이메일의 회원이 존재하면 예외 처리
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        }
    }
}
