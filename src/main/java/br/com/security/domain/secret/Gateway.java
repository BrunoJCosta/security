package br.com.security.domain.secret;

import br.com.security.domain.KeyAccess;
import br.com.security.domain.Keys;
import br.com.security.domain.ScopeDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Gateway implements SecretProtocol{

    @Value("${GATEWAY}")
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
    public ScopeDTO getScope() {
        ScopeDTO dto = new ScopeDTO();
        Keys key = getKey();
        dto.setSub(key);
        dto.addScope(KeyAccess.bookRead());
        dto.addScope(KeyAccess.bookWrite());
        dto.addScope(KeyAccess.cambioRead());
        return dto;
    }
}
