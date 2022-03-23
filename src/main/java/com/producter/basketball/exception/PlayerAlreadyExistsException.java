package com.producter.basketball.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerAlreadyExistsException extends RuntimeException implements GraphQLError {

    private String invalidField;

    public PlayerAlreadyExistsException(String message, String name, String surname ) {
        super(message);
        invalidField =  name +" "+ surname;
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
