package org.example.user.domain.service;

import org.example.persistance.BaseRepository;
import org.example.user.domain.entity.User;
import org.example.user.domain.entity.UserName;

import java.util.Optional;

public interface UserRepository extends BaseRepository<Long, User> {
    boolean extractByUserId(UserName userName);

    Optional<User> findByUserName(String username);
}
