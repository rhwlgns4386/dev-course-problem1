package org.example.account.persentation.controller;

import org.example.account.domain.entity.Password;
import org.example.account.domain.entity.User;
import org.example.account.domain.service.UserRepository;
import org.example.account.persentation.dto.UserInfoDto;
import org.example.account.persentation.exception.UserAuthenticationException;
import org.example.account.persentation.view.InputView;
import org.example.account.persentation.view.OutputView;
import org.example.dispatcher.dto.Response;

import java.util.Optional;

public class AccountController {

    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void login(String username, String password, Response response){
        User user = accountRepository.findByUsername(username).orElseThrow(()->new UserAuthenticationException("회원 정보가 일치 하지 않습니다."));
        if(!user.samePassword(new Password(password))){
            throw new UserAuthenticationException("회원 정보가 일치 하지 않습니다.");
        }
        response.setSession();
        OutputView.renderSuccessLogin();
    }

    public UserInfoDto readLoginInfo() {
        return InputView.readLoginInfo();
    }
}
