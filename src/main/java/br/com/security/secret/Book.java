package br.com.security.secret;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class Book implements SecretProtocol {

    @Value("secret.book")
    private String book;

    @Override
    public String getSecret() {
        return this.book;
    }

    @Override
    public Keys getKey() {
        return Keys.BOOK;
    }
}
