package br.com.security.domain;

import br.com.security.exception.AlgorithmInvalid;
import br.com.security.domain.secret.SecretProtocol;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import java.security.*;
import java.time.*;
import java.util.List;
import java.util.Objects;

@Component
@PropertySource(value = "classpath:application.yaml", encoding = "UTF-8")
class HashService {

    private final List<SecretProtocol> secretProtocol;
    private final JwtEncoder jwtEncoder;
    private final AssinaturaService assinaturaService;

    HashService(List<SecretProtocol> secretProtocols,
                JwtEncoder jwtEncoder,
                AssinaturaService assinaturaService) {
        this.secretProtocol = secretProtocols;
        this.jwtEncoder = jwtEncoder;
        this.assinaturaService = assinaturaService;
    }

    public TokenDTO getToken(Keys keys) throws AlgorithmInvalid, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        SecretProtocol protocol = getProtocol(keys);
        ScopeDTO tokenProtocolo = protocol.getScope();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("security")
                .expiresAt(Instant.now().plusSeconds(30))
                .subject(tokenProtocolo.getSub())
                .claim("scope", tokenProtocolo.getScope())
                .build();

        String token = jwtEncoder
                .encode(JwtEncoderParameters.from(claims))
                .getTokenValue();

        String assinatura = assinaturaService.get(protocol.getSecret());

        return new TokenDTO(token, assinatura);
    }

    private SecretProtocol getProtocol(Keys key) throws AlgorithmInvalid {
        return this.secretProtocol.stream()
                .filter(secret -> Objects.equals(key, secret.getKey()))
                .findAny()
                .orElseThrow(AlgorithmInvalid::new);
    }

}