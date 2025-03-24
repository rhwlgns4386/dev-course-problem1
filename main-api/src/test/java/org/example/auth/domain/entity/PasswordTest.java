package org.example.auth.domain.entity;

import org.example.user.domain.entity.Password;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PasswordTest {


    @Test
    void 비밀번호는_빈값일_수_없다(){
        assertThatThrownBy( () -> new Password("")).isInstanceOf(IllegalArgumentException.class);
    }
}