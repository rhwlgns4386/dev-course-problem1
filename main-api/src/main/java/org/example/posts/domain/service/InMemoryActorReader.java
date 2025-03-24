package org.example.posts.domain.service;

import org.example.account.domain.service.UserRepository;
import org.example.posts.domain.entity.Actor;

import java.util.Optional;

public class InMemoryActorReader implements ActorReader {

    private final UserRepository userRepository;

    public InMemoryActorReader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Actor> read(Long id) {
        Actor actor = userRepository.findById(id).map(Actor::new).orElse(null);
        return Optional.ofNullable(actor);
    }
}
