package hellospring.demo.repository;

import hellospring.demo.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // @AfterEach
    // 한번에 여러 테스트를 실행하면 메모리 DB에 테스트 직전의 데이터가 남을 수 있다.
    // 테스트가 종료될 때마다 아래 메서드를 실행시킨다.
    // 테스트는 각각 독립적으로 실행되어야 한다.
    // 테스트와 구현 클래스를 먼저 만들고 만들어서 돌려보는 것 -> TDD 테스트 주도 개발
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        repository.save(member);

        // then
        Member result = repository.findById(member.getId()).get();
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // when
        Member result = repository.findByName(member1.getName()).get();

        // then
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // when
        List<Member> result =  repository.findAll();

        // then
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
