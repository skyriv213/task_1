package com.example.page.entity.user;

public enum Grade {

    USER(Authority.USER),
    ADMIN(Authority.ADMIN),
    ;

    private final String authority;

    Grade(String authority) {
        this.authority = authority;
    }

    public String getAuthority(){
        return this.authority;
    }


    public static class Authority{
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
