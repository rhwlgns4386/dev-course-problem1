package org.example.auth.persentation.view;

import org.example.auth.persentation.dto.UserInfoDto;
import org.example.cli.input.CliInput;

public class InputView {

    public static UserInfoDto readLoginInfo() {
        System.out.println("아이디 : ");
        String userName = CliInput.readLine();
        System.out.println("비밀번호 : ");
        String password = CliInput.readLine();
        return new UserInfoDto(userName, password, null, null);
    }
}
