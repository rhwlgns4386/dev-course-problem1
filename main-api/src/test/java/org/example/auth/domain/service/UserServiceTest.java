package org.example.auth.domain.service;

import org.example.global.exception.EntityNotFoundException;
import org.example.user.domain.entity.*;
import org.example.user.domain.exception.DuplicateUserNameException;
import org.example.user.domain.service.UserRepository;
import org.example.user.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserServiceTest {

    private static UserService service;
    private static final User TEST_USER = new User(1L, new UserName("userName"), new Email("email@email.com"), new Password("password"),new NickName("test"));

    @BeforeEach
    void setUp() {
        service = new UserService(new UserTestRepository());
    }

    @Test
    void 회원조회() {

        User user = service.findById(1L);

        assertThat(user.userName()).isEqualTo(TEST_USER.userName());
        assertThat(user.email()).isEqualTo(TEST_USER.email());
        assertThat(user.password()).isEqualTo(TEST_USER.password());
        assertThat(user.createDate()).isEqualTo(TEST_USER.createDate());
    }

    @Test
    void 회원이_없는경우_예외() {
        assertThatThrownBy(() -> service.findById(2L)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void 사용자_생성() {

        UserName userName = new UserName("userName2");
        Email email = new Email("email@email.com");
        Password password = new Password("password");
        NickName nickName = new NickName("test");
        LocalDateTime createDate = LocalDateTime.now();


        Long id = service.save(userName, email, password,nickName);

        User user = service.findById(id);
        assertThat(user.userName()).isEqualTo(userName.toStringValue());
        assertThat(user.email()).isEqualTo(email.toStringValue());
        assertThat(user.password()).isEqualTo(password.toStringValue());
        assertThat(user.createDate()).isAfterOrEqualTo(createDate);
        assertThat(user.nickName()).isEqualTo(nickName.toStringValue());
    }

    @Test
    void 아이디가_중복되면_예외() {

        UserName userName = new UserName("userName");
        Email email = new Email("email@email.com");
        Password password = new Password("password");

        assertThatThrownBy(() -> service.save(userName, email, password,new NickName("test"))).isInstanceOf(DuplicateUserNameException.class);
    }

    @Test
    void 사용자_수정() {
        UserName userName = new UserName("userName2");
        Long id = service.save(userName, new Email("email@email.com"), new Password("password"), new NickName("test"));
        Email email = new Email("email@email.com");
        Password password = new Password("updatePassword");
        LocalDateTime updateDate = LocalDateTime.now();

        service.update(id, email, password);

        User user = service.findById(id);
        assertThat(user.userName()).isEqualTo(userName.toStringValue());
        assertThat(user.email()).isEqualTo(email.toStringValue());
        assertThat(user.password()).isEqualTo(password.toStringValue());
        assertThat(user.updateDate()).isAfterOrEqualTo(updateDate);
    }

    @Test
    void 사용자_삭제() {
        UserName userName = new UserName("userName2");
        Long id = service.save(userName, new Email("email@email.com"), new Password("password"), new NickName("test"));


        service.remove(id);

        assertThatThrownBy(() -> service.findById(id)).isInstanceOf(EntityNotFoundException.class);
    }

    private static class UserTestRepository implements UserRepository {

        private List<User> users;

        public UserTestRepository() {
            users = initArray();
        }

        private ArrayList<User> initArray() {
            return new ArrayList<>(List.of(TEST_USER));
        }

        @Override
        public Optional<User> findById(Long id) {
            for (User user : users) {
                if (user.id().equals(id)) {
                    return Optional.of(user);
                }
            }
            return Optional.empty();
        }

        @Override
        public User save(User element) {
            User user = new User((long) users.size() + 1, new UserName(element.userName()), new Email(element.email()), new Password(element.password()),new NickName(element.nickName()));
            users.add(user);
            return user;
        }

        @Override
        public List<User> findAll() {
            return users;
        }

        @Override
        public void deleteById(Long id) {
            findById(id).ifPresent(users::remove);
        }

        @Override
        public boolean extractById(Long id) {
            Optional<User> byId = findById(id);
            if (!byId.isPresent()) {
                return false;
            }
            return true;
        }

        @Override
        public void clear() {
            users = initArray();
        }

        @Override
        public boolean extractByUserId(UserName userName) {
            for (User user : users) {
                if (user.userName().equals(userName.toStringValue())) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Optional<User> findByUserName(String username) {
            return Optional.empty();
        }
    }
}
