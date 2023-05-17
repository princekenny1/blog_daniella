package com.example.demo.security;

public class SecurityConstraint {
    public static final String SECRET = "secretKeyToGenerateJwt";
    public static final String TOKEN_PREFIX= "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME= 30000000;
}
