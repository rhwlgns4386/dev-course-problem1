package org.example.account.domain.entity;

import org.example.global.exception.FormatException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmailTest {

    @Test
    void 이메일_정상_생성(){
        assertThatCode( () -> new Email("wlgns4386@naver.com")).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"wlgns4366naver.com","wlgns4386@naver","wlgns4386@.com"})
    void 이메일_폼이_아니면예외(String email){
        assertThatThrownBy(()->new Email(email)).isInstanceOf(FormatException.class);
    }
}