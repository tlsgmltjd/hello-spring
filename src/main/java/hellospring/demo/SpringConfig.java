package hellospring.demo;

import hellospring.demo.repository.JdbcMemberRepository;
import hellospring.demo.repository.JdbcTemplateMemberRepository;
import hellospring.demo.repository.MemberRepository;
import hellospring.demo.repository.MemoryMemberRepository;
import hellospring.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// 직접 스프링 빈에 등록
@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
