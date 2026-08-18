package br.com.security.domain.secret;

import br.com.security.domain.KeyAccess;
import br.com.security.domain.Keys;
import br.com.security.domain.ScopeDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class Book implements SecretProtocol {

    @Value("${BOOK}")
    private String book;

    @Override
    public String getSecret() {
        return this.book;
    }

    @Override
    public Keys getKey() {
        return Keys.BOOK;
    }

    @Override
    public ScopeDTO getScope() {
        ScopeDTO dto = new ScopeDTO();
        Keys key = getKey();
        dto.setSub(key);
        dto.addScope(KeyAccess.cambioRead());
        dto.addScope(KeyAccess.stockRead());
        return dto;
    }


}
