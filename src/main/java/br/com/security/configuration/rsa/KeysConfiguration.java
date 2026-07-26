package br.com.security.configuration.rsa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.stream.Collectors;

@Configuration
public class KeysConfiguration {

    @Value("${security.jwt.private-key}")
    private Resource privateKey;

    @Value("${security.jwt.public-key}")
    private Resource publicKey;

    @Bean
    public RSAPrivateKey privateKey() throws Exception {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(privateKey.getInputStream(), StandardCharsets.UTF_8)
        )) {

            String key = bufferedReader.lines().collect(Collectors.joining())
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s", "");

            byte[] decoded = Base64.getDecoder().decode(key);

            return (RSAPrivateKey) KeyFactory
                    .getInstance("RSA")
                    .generatePrivate(new PKCS8EncodedKeySpec(decoded));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public RSAPublicKey publicKey() throws Exception {

        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(publicKey.getInputStream(), StandardCharsets.UTF_8)
        )) {
            String key = bufferedReader.lines().collect(Collectors.joining())
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");

            byte[] decoded = Base64.getDecoder().decode(key);

            return (RSAPublicKey) KeyFactory
                    .getInstance("RSA")
                    .generatePublic(new X509EncodedKeySpec(decoded));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
