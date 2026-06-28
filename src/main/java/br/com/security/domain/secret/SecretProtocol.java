package br.com.security.domain.secret;

import br.com.security.domain.Keys;
import br.com.security.domain.TokenDTO;

public interface SecretProtocol {

    String getSecret();

    Keys getKey();

    TokenDTO getToken();
}
