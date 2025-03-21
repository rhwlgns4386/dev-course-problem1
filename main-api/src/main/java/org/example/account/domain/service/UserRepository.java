package org.example.account.domain.service;

import org.example.account.domain.entity.User;
import org.example.account.domain.entity.UserName;
import org.example.persistance.BaseRepository;

public interface UserRepository extends BaseRepository<Long, User> {
    boolean extractByUserId(UserName userName);
}
