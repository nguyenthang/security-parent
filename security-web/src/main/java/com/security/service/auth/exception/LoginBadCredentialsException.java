package com.security.service.auth.exception;

import org.springframework.security.authentication.BadCredentialsException;

/**
 * Created by thangnguyen-imac on 7/16/16.
 */
public class LoginBadCredentialsException extends BadCredentialsException {

    public LoginBadCredentialsException(String msg) {
        super(msg);
    }
}
