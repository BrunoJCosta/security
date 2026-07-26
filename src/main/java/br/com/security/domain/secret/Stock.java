package br.com.security.domain.secret;

import br.com.security.domain.Keys;
import br.com.security.domain.ScopeDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class Stock implements SecretProtocol {

    @Value("${STOCK}")
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
    public ScopeDTO getScope() {
        ScopeDTO dto = new ScopeDTO();
        Keys key = getKey();
        dto.setSub(key);
        return dto;
    }
}
