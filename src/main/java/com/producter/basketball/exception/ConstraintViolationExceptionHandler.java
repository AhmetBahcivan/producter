package com.producter.basketball.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ConstraintViolationExceptionHandler extends RuntimeException implements GraphQLError {

    private String invalidField;

    public ConstraintViolationExceptionHandler(String message) {
        super(message);
        invalidField =  message.substring(message.lastIndexOf("propertyPath="),message.indexOf(", rootBeanClass"));
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
