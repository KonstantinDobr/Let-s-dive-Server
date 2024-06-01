package com.samsung.server.exception;

public class UserRelationshipNotFoundException extends RuntimeException {
    public UserRelationshipNotFoundException(String message) {
        super(message);
    }
}
