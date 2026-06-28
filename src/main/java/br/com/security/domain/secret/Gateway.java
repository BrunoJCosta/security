package br.com.security.domain.secret;

import br.com.security.domain.KeyAccess;
import br.com.security.domain.Keys;
import br.com.security.domain.TokenDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Gateway implements SecretProtocol{

    @Value("secret.gateway")
    private String gateway;

    @Override
    public String getSecret() {
        return this.gateway;
    }

    @Override
    public Keys getKey() {
        return Keys.GATEWAY;
    }

    @Override
    public TokenDTO getToken() {
        TokenDTO dto = new TokenDTO();
        Keys key = getKey();
        dto.setSub(key);
        dto.addScope(KeyAccess.bookRead());
        dto.addScope(KeyAccess.bookWrite());
        return dto;
    }
}
