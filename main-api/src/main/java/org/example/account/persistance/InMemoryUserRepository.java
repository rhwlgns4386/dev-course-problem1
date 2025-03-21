package org.example.account.persistance;

import org.example.account.domain.entity.User;
import org.example.account.domain.entity.UserName;
import org.example.account.domain.service.UserRepository;
import org.example.persistance.LongKeyBaseRepository;

import java.util.Collection;

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
}
