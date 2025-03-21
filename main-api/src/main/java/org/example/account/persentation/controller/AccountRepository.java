package org.example.account.persentation.controller;

import org.example.account.domain.entity.User;
import org.example.persistance.BaseRepository;

import java.util.Optional;

public interface AccountRepository extends BaseRepository<Long, User> {
    Optional<User> findByUsername(String username);
}
