package br.com.security.domain;

import lombok.Getter;

@Getter
public enum TokenAccess {

    READ("read"),
    WRITE("write")
    ;

    private final String value;

    TokenAccess(String value) {
        this.value = value;
    }

}
