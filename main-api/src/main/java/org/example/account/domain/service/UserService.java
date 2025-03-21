package org.example.account.domain.service;

import org.example.account.domain.entity.*;
import org.example.global.exception.EntityNotFoundException;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userTestRepository) {
        this.userRepository = userTestRepository;
    }

    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(UserService::entityNotFoundException);
    }

    public Long save(UserName userName, Email email, Password password,NickName nickName) {
        if(userRepository.extractByUserId(userName)){
            throw new DuplicateUserNameException("아이디가 중복 됩니다.");
        }
        User user = userRepository.save(new User(userName, email, password,nickName));
        return user.id();
    }

    public void update(Long id, Email email, Password password) {
        User user = findById(id);
        user.update(email, password);
    }

    public void remove(Long id) {
        if(!userRepository.extractById(id)) {
            throw entityNotFoundException();
        }
        userRepository.deleteById(id);
    }

    private static EntityNotFoundException entityNotFoundException() {
        return new EntityNotFoundException("사용자를 찾을 수 없습니다.");
    }
}
