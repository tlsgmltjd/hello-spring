package hellospring.demo.service;

import hellospring.demo.domain.Member;
import hellospring.demo.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given <- 이러한 상황이 주어져서
        Member member = new Member();
        member.setName("hello");

        // when <- 이걸 실행 했을 떄
        Long saveId = memberService.join(member);

        // then <- 이게 나와야해
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
    }

    @Test
    void 중복회원예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try {
//            memberService.join(member1);
//            memberService.join(member2);
//        } catch (IllegalStateException e) {
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }


        // than
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}