package br.com.security.secret;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
class Stock implements SecretProtocol {

    @Value("secret.stock")
    private String stock;

    @Override
    public String getSecret() {
        return this.stock;
    }

    @Override
    public Keys getKey() {
        return Keys.STOCK;
    }
}
