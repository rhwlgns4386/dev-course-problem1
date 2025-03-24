package org.example.auth.domain.service;

import org.example.auth.domain.entity.Account;
import org.example.persistance.BaseRepository;

import java.util.Optional;

public interface AccountReader{
    Optional<Account> findByUsername(String username);
}
