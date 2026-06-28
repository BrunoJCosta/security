package br.com.security.domain.secret;

import br.com.security.domain.Keys;
import br.com.security.domain.TokenDTO;
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

    @Override
    public TokenDTO getToken() {
        TokenDTO dto = new TokenDTO();
        Keys key = getKey();
        dto.setSub(key);
        return dto;
    }
}
