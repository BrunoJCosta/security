package br.com.security.domain;

import br.com.security.exception.AlgorithmInvalid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

@Service
@RequiredArgsConstructor
@Slf4j
class AlgorithmServiceImp implements AlgorithmService {

    public final HashService hashService;

    @Override
    public TokenDTO get(String key, String target) throws AlgorithmInvalid,
            NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        log.debug("acessou o endpoint");
        Keys keys = Keys.get(key);
        Keys keyTarget = Keys.get(target);
        log.debug("chave veio de {} para {}", keys.getValue(), keyTarget.getValue());
        return hashService.getToken(keys, keyTarget);
    }


}
