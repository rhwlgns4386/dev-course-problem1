package org.example.auth.persentation.controller;

import org.example.auth.domain.entity.Account;
import org.example.auth.domain.service.AccountReader;
import org.example.auth.persentation.dto.UserInfoDto;
import org.example.auth.persentation.exception.UserAuthenticationException;
import org.example.auth.persentation.view.InputView;
import org.example.auth.persentation.view.OutputView;
import org.example.dispatcher.dto.Request;
import org.example.dispatcher.session.Session;
import org.example.global.exception.PresentationException;

public class AccountController {

    private final AccountReader accountReader;

    public AccountController(AccountReader accountReader) {
        this.accountReader = accountReader;
    }

    public UserInfoDto readLoginInfo() {
        return InputView.readLoginInfo();
    }

    public void login(String username, String password, Request request){
        Account user = accountReader.findByUsername(username).orElseThrow(()-> createAuthenticationException());
        if(!user.samePassword(password)){
            throw createAuthenticationException();
        }
        if(request.hasSession()){
            throw new PresentationException("이미 로그인되어 있습니다.");
        }
        request.setSession();
        Session session = request.getSession();
        session.add("userId",user.id());
        OutputView.renderSuccessLogin();
    }

    private static UserAuthenticationException createAuthenticationException() {
        return new UserAuthenticationException("회원 정보가 일치 하지 않습니다.");
    }

    public void singout(Request request) {
        if(!request.hasSession()){
            throw new PresentationException("로그인이 되어 있지 않습니다.");
        }
        request.logout();
        OutputView.renderSuccessLogout();
    }
}
