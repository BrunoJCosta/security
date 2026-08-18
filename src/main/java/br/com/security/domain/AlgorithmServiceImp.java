package br.com.security.domain;

import br.com.security.exception.AlgorithmInvalid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

@Service
@RequiredArgsConstructor
class AlgorithmServiceImp implements AlgorithmService {

    public final HashService hashService;

    @Override
    public TokenDTO get(String key, String target) throws AlgorithmInvalid,
            NoSuchAlgorithmException, SignatureException, InvalidKeyException {

        Keys keys = Keys.get(key);
        Keys keyTarget = Keys.get(target);
        return hashService.getToken(keys, keyTarget);
    }


}
