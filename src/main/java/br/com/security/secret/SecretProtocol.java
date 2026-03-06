package br.com.security.secret;

public interface SecretProtocol {

    String getSecret();

    Keys getKey();
}
