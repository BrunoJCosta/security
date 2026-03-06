package br.com.security.secret;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class Cambio implements SecretProtocol {

    @Value("secret.cambio")
    private String cambio;

    @Override
    public String getSecret() {
        return this.cambio;
    }

    @Override
    public Keys getKey() {
        return Keys.CAMBIO;
    }
}
