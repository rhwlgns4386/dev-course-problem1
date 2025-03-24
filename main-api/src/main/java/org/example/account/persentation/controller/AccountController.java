package org.example.account.persentation.controller;

import org.example.account.domain.entity.Password;
import org.example.account.domain.entity.User;
import org.example.account.persentation.dto.UserInfoDto;
import org.example.account.persentation.exception.UserAuthenticationException;
import org.example.account.persentation.view.InputView;
import org.example.account.persentation.view.OutputView;
import org.example.dispatcher.dto.Request;
import org.example.dispatcher.session.Session;
import org.example.global.exception.PresentationException;

public class AccountController {

    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public UserInfoDto readLoginInfo() {
        return InputView.readLoginInfo();
    }

    public void login(String username, String password, Request request){
        User user = accountRepository.findByUsername(username).orElseThrow(()-> createAuthenticationException());
        if(!user.samePassword(new Password(password))){
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
