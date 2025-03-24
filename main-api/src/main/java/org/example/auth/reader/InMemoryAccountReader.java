package org.example.auth.reader;

import org.example.auth.domain.entity.Account;
import org.example.auth.domain.service.AccountReader;
import org.example.user.domain.entity.User;
import org.example.user.domain.service.UserRepository;

import java.util.Optional;

public class InMemoryAccountReader implements AccountReader {

    private final UserRepository userRepository;

    public InMemoryAccountReader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        Optional<User> user = userRepository.findByUserName(username);
        return Optional.ofNullable(user.map(user1 -> new Account(user1.id(),user1.userName(),user1.password())).orElse(null));
    }
}
