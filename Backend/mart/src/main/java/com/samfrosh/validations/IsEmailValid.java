package com.samfrosh.validations;


import com.samfrosh.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class IsEmailValid implements Predicate<String> {
    @Override
    public boolean test(String email) {
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);
        if (email == null || email.isEmpty()) {
            throw new ApiException("Email is required", HttpStatus.NOT_FOUND);
        }
        String[] parts = email.split("@");
        if (parts.length != 2) {
            throw new ApiException("Invalid Email Found", HttpStatus.NOT_ACCEPTABLE);
        }
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }
}
