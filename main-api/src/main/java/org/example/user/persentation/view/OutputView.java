package org.example.user.persentation.view;

import org.example.user.domain.entity.User;

import java.time.format.DateTimeFormatter;

public class OutputView {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void renderUser(User user) {
        System.out.printf("%s번 회원\n",user.id());
        System.out.printf("계정 : %s\n",user.userName());
        System.out.printf("이메일 : %s\n",user.email());
        System.out.printf("가입일 : %s\n",user.createDate().format(FORMATTER));
    }

    public static void renderDelete(Long id) {
        System.out.printf("%s번 회원이 삭제 되었습니다.\n",id);
    }
}
