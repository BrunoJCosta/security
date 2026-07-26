package br.com.security.domain;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

@Service
public class AssinaturaService {

    private final PrivateKey privateKey;

    AssinaturaService(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public String get(String token) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);

        signature.update(token.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(signature.sign());
    }

}
