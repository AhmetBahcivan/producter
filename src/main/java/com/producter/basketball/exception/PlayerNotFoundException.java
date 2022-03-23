package com.producter.basketball.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerNotFoundException extends RuntimeException implements GraphQLError {

    private String invalidField;

    private Map<String, Object> extensions = new HashMap<>();

    public PlayerNotFoundException(String message, String invalidPlayerId) {
        super(message);
        this.invalidField = invalidPlayerId;
    }

    public PlayerNotFoundException(String message, String name, String surname) {
        super(message);
        this.invalidField = "name surname";
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return Collections.singletonMap("invalidField", invalidField);
    }

}
