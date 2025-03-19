package org.example.posts.domain.exeption;

public class EntityCreationException extends BusinessException {
    public EntityCreationException(String message) {
        super(message);
    }

    public EntityCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
