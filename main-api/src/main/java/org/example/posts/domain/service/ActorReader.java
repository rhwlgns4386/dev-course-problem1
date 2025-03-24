package org.example.posts.domain.service;

import org.example.posts.domain.entity.Actor;

import java.util.Optional;

public interface ActorReader {

    Optional<Actor> read(Long id);
}
