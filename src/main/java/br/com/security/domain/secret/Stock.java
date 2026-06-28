package br.com.security.domain.secret;

import br.com.security.domain.Keys;
import br.com.security.domain.TokenDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

    @Override
    public TokenDTO getToken() {
        TokenDTO dto = new TokenDTO();
        Keys key = getKey();
        dto.setSub(key);
        return dto;
    }
}
