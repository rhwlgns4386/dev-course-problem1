package org.example.account.persentation.controller;

import org.example.account.domain.entity.User;
import org.example.account.domain.service.UserService;
import org.example.account.persentation.dto.UserInfoDto;
import org.example.account.persentation.view.InputView;
import org.example.account.persentation.view.OutputView;

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void load(Long id) {
        User user = userService.findById(id);
        OutputView.renderUser(user);
    }

    public UserInfoDto readUserInfo() {
        return InputView.readUserInfo();
    }

    public void singup(UserInfoDto userInfoDto) {
        Long id = userService.save(userInfoDto.toUserName(), userInfoDto.email(), userInfoDto.password(), userInfoDto.nickName());
        User user = userService.findById(id);
        OutputView.renderUser(user);
    }

    public void remove(Long id) {
        userService.remove(id);
        OutputView.renderDelete(id);
    }

    public UserInfoDto readUpdateInfo() {
        return InputView.readUpdateInfo();
    }

    public void edit(Long id, UserInfoDto userInfoDto) {
        userService.update(id,userInfoDto.email(),userInfoDto.password());
        User user = userService.findById(id);
        OutputView.renderUser(user);
    }
}
