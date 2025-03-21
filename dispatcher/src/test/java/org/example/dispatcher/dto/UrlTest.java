package org.example.dispatcher.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class UrlTest {

    @ParameterizedTest
    @CsvSource(value = {"/base/url?boardId=1,boardId,1","/base/url?boardId==1,boardId,=1","/base/url?boardId=1&boardId=2,boardId,2"})
    void 파라미터_검색(String url,String key, String value) {
        Url object = new Url(url);
        assertThat(object.getParmeter(key)).isEqualTo(value);
    }

}