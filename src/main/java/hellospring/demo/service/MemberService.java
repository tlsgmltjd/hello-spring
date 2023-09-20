package hellospring.demo.service;

import hellospring.demo.domain.Member;
import hellospring.demo.repository.MemberRepository;
import hellospring.demo.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// cmd + shift + T <- 테스트 생성
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    * 회원가입
    * */
    public Long join(Member memebr) {
        // 같은 이름이 있는 중복 회원 X
        validataDuplicateMember(memebr); // 중복 회원 검증
        memberRepository.save(memebr);
        return memebr.getId();
    }

    private void validataDuplicateMember(Member memebr) {
        memberRepository.findByName(memebr.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                 });
    }

    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /*
     * 특정 회원 조회
     * */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
