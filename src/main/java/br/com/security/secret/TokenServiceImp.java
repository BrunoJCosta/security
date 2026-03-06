package br.com.security.secret;

import br.com.security.exception.AlgorithmInvalid;
import br.com.security.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
class TokenServiceImp implements TokenService{

    public final HashService hashService;

    @Override
    public String get(String key) throws AlgorithmInvalid, NoSuchAlgorithmException {
        Keys keys = Keys.get(key);
        return hashService.encode(keys);
    }

    @Override
    public void valid(String token, String key) throws AlgorithmInvalid,
            NoSuchAlgorithmException, UnauthorizedException {
        Keys keys = Keys.get(key);
        hashService.valid(token, keys);
    }
}
