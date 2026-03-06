package br.com.security.secret;

import br.com.security.exception.AlgorithmInvalid;
import br.com.security.exception.UnauthorizedException;

import java.security.NoSuchAlgorithmException;

public interface TokenService {

    String get(String key) throws AlgorithmInvalid, NoSuchAlgorithmException;

    void valid(String token, String key) throws AlgorithmInvalid, NoSuchAlgorithmException, UnauthorizedException;
}
