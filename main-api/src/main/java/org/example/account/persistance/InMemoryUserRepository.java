package org.example.account.persistance;

import org.example.account.domain.entity.User;
import org.example.account.domain.entity.UserName;
import org.example.account.domain.service.UserRepository;
import org.example.account.persentation.controller.AccountRepository;
import org.example.persistance.LongKeyBaseRepository;

import java.util.Collection;
import java.util.Optional;

public class InMemoryUserRepository extends LongKeyBaseRepository<User> implements UserRepository , AccountRepository {
    @Override
    public boolean extractByUserId(UserName userName) {
        Collection<User> users = store.values();
        for (User user : users) {
            if(user.userName().equals(userName.toStringValue())){
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Collection<User> users = store.values();
        for (User user : users) {
            if(user.userName().equals(username)){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
