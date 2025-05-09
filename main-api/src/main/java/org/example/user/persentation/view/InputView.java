package org.example.user.persentation.view;

import org.example.cli.input.CliInput;
import org.example.user.persentation.dto.UserInfoDto;

public class InputView {
    public static UserInfoDto readUserInfo() {
        System.out.println("아이디 : ");
        String userName = CliInput.readLine();
        System.out.println("비밀번호 : ");
        String password = CliInput.readLine();
        System.out.println("이메일 : ");
        String email = CliInput.readLine();
        System.out.println("닉네임 : ");
        String nickName = CliInput.readLine();
        return new UserInfoDto(userName, password, email, nickName);
    }

    public static UserInfoDto readUpdateInfo() {
        System.out.println("비밀번호 : ");
        String password = CliInput.readLine();
        System.out.println("이메일 : ");
        String email = CliInput.readLine();
        return new UserInfoDto(password, email);
    }
}
