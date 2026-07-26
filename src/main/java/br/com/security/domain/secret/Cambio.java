package br.com.security.domain.secret;

import br.com.security.domain.Keys;
import br.com.security.domain.ScopeDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class Cambio implements SecretProtocol {

    @Value("${CAMBIO}")
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
    public ScopeDTO getScope() {
        ScopeDTO dto = new ScopeDTO();
        Keys key = getKey();
        dto.setSub(key);
        return dto;
    }
}
