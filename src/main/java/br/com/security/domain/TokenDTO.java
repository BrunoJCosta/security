package br.com.security.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TokenDTO {

    private String sub;
    private List<String> scope;

    public void setSub(Keys sub) {
        this.sub = sub.getValue();
    }

    public TokenDTO() {
        this.scope = new ArrayList<>();
    }

    public void addScope(String scope) {
        this.scope.add(scope);
    }
}
