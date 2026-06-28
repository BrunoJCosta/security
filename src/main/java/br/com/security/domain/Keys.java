package br.com.security.domain;

import br.com.security.exception.AlgorithmInvalid;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum Keys {

    BOOK("book"),
    CAMBIO("cambio"),
    GATEWAY("gateway"),
    STOCK("stock");

    private final String value;

    Keys(String value) {
        this.value = value;
    }

    public static Keys get(String key) throws AlgorithmInvalid {
        return Arrays.stream(Keys.values())
                .filter(k -> Objects.equals(key, k.value))
                .findFirst()
                .orElseThrow(AlgorithmInvalid::new);
    }
}
