package org.example.account;

import org.example.CliApplication;
import org.example.account.domain.entity.*;
import org.example.account.domain.service.UserRepository;
import org.example.account.config.AccountConfig;
import org.example.cli.test.CliApplicationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
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

        assertThat(out()).contains(
                user.id()+"번 회원이 삭제 되었습니다."
        );
        Optional<User> result = userRepository.findById(user.id());
        assertThat(result).isEmpty();
    }

    @Test
    void 사용자_수정(){
        User user = userRepository.save(new User(new UserName("test"), new Email("email@naver.com"), new Password("password"),new NickName("test")));
        String updatePassword = "updatePassword";
        String email = "updateTest@naver.com";
        LocalDateTime now = LocalDateTime.now();
        run(()->{
            in(input->input.command("/accounts/edit?accountId="+user.id()).input(updatePassword,email));
            CliApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                user.id() + "번 회원",
                "계정 : " + user.userName(),
                "이메일 : " + email,
                "가입일 : " + user.createDate().format(FORMATTER)
        );

        Optional<User> optionalUser = userRepository.findById(user.id());
        assertThat(optionalUser).isPresent();
        optionalUser.ifPresent(u -> {
            assertThat(u).isEqualTo(user);
            assertThat(u.password()).isEqualTo(updatePassword);
            assertThat(u.email()).isEqualTo(email);
            assertThat(u.updateDate()).isAfterOrEqualTo(now);
        });
    }

    @Test
    void 사용자_로그인(){
        User user = userRepository.save(new User(new UserName("test"), new Email("email@naver.com"), new Password("password"),new NickName("test")));
        run(()->{
            in(input->input.command("/accounts/signin").input(user.userName(),user.password()));
            CliApplication.main(new String[]{});
        });

        assertThat(out()).contains(
               "로그인에 성공하셨습니다."
        );
    }

    @Test
    void 사용자_로그아웃(){
        User user = userRepository.save(new User(new UserName("test"), new Email("email@naver.com"), new Password("password"),new NickName("test")));
        run(()->{
            in(input->input.command("/accounts/signin").input(user.userName(),user.password()));
            CliApplication.main(new String[]{});
        });

        assertThat(out()).contains(
                "로그인에 성공하셨습니다."
        );
    }
}
