package br.com.security.domain;

import br.com.security.exception.AlgorithmInvalid;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

public interface AlgorithmService {

    TokenDTO get(String key, String target) throws AlgorithmInvalid, NoSuchAlgorithmException, SignatureException, InvalidKeyException;
}
