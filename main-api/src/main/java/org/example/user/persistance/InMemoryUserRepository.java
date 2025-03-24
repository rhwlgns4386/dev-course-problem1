package org.example.user.persistance;

import org.example.persistance.LongKeyBaseRepository;
import org.example.user.domain.entity.User;
import org.example.user.domain.entity.UserName;
import org.example.user.domain.service.UserRepository;

import java.util.Collection;
import java.util.Optional;

public class InMemoryUserRepository extends LongKeyBaseRepository<User> implements UserRepository {
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
    public Optional<User> findByUserName(String username) {
        Collection<User> users = store.values();
        for (User user : users) {
            if(user.userName().equals(username)){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
