package org.example.account;

import org.example.CliApplication;
import org.example.account.domain.entity.*;
import org.example.account.domain.service.UserRepository;
import org.example.account.persentation.config.AccountConfig;
import org.example.cli.test.CliApplicationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountApplicationTest extends CliApplicationTest {

    private static UserRepository userRepository;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @BeforeEach
    public void beforeEach(){
        userRepository = AccountConfig.userRepository();
        userRepository.clear();
    }

    @Test
    void 사용자_조회(){
        User user = userRepository.save(new User(new UserName("test"), new Email("email@naver.com"), new Password("password"),new NickName("test")));
        run(()->{
            in(input->input.command("/accounts/detail?accountId=" + user.id()));
            CliApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                user.id() + "번 회원",
                "계정 : " + user.userName(),
                "이메일 : " + user.email(),
                "가입일 : " + user.createDate().format(FORMATTER)
                );
    }

    @Test
    void 사용자_생성(){
        String userName = "test2";
        String email = "test2@naver.com";
        run(()->{
            in(input->input.command("/accounts/signup").input(userName,"password",email,"nickname"));
            CliApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                "계정 : " + userName,
                "이메일 : " + email
        );
    }

    @Test
    void 사용자_삭제(){
        User user = userRepository.save(new User(new UserName("test"), new Email("email@naver.com"), new Password("password"),new NickName("test")));
        run(()->{
            in(input->input.command("/accounts/remove?accountId="+user.id()));
            CliApplication.main(new String[]{});
        });

        Optional<User> result = userRepository.findById(user.id());
        assertThat(result).isEmpty();
    }
}
