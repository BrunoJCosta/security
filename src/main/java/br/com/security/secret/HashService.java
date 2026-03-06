package br.com.security.secret;

import br.com.security.exception.AlgorithmInvalid;
import br.com.security.exception.UnauthorizedException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Component
@PropertySource(value = "classpath:application.yaml", encoding = "UTF-8")
class HashService {

    private static final Duration TEMPO_TOKEN = Duration.ofMinutes(2);
    private static final Duration TEMPO_TOKEN_PRORROGADO = Duration.ofSeconds(30);
    private static final String SECRET = "secret:";
    private static final String SECRET_V2 = "secret_v2:";
    public static final String SHA_256 = "SHA-256";
    private final RedisTemplate<String, String> redisTemplate;
    private final List<SecretProtocol> secretProtocol;

    HashService(RedisTemplate<String, String> redisTemplate, List<SecretProtocol> secretProtocols) {
        this.redisTemplate = redisTemplate;
        this.secretProtocol = secretProtocols;
    }

//    @Bean
//    public ApplicationRunner runner() {
//        return args -> {
//            String token = encode(Keys.BOOK);
//            valid(token,Keys.BOOK);
//            Long expire = redisTemplate.getExpire(REDIS + Keys.BOOK.getValue());
//            getTempo(expire);
//
//        };
//    }

    private static void getTempo(Long expire) {
        Duration duration = Duration.ofSeconds(expire);
        long minutos = duration.toMinutes();
        long segundos = duration.minusMinutes(minutos).toSeconds();
        System.out.println(minutos + ":" + segundos);
    }

    public String encode(Keys keys) throws NoSuchAlgorithmException, AlgorithmInvalid {
        SecretProtocol protocol = get(keys);
        String secret = protocol.getSecret();
        String tokenRedis = getTokenRedis(keys);
        MessageDigest algorithm = getAlgorithm();
        String secretFinal = secret + tokenRedis;

        byte[] digest = algorithm.digest(secretFinal.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(digest);
    }

    private static MessageDigest getAlgorithm() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(SHA_256);
    }

    public void valid(String token, Keys keys) throws AlgorithmInvalid, NoSuchAlgorithmException, UnauthorizedException {
        SecretProtocol protocol = get(keys);
        String secret = protocol.getSecret();
        String tokenRedis = redisTemplate.opsForValue().get(SECRET + protocol.getKey().getValue());
        String secretFinal = secret + tokenRedis;

        byte[] actual = getAlgorithm().digest(secretFinal.getBytes(StandardCharsets.UTF_8));
        byte[] tokenValidate = Base64.getDecoder().decode(token);

        boolean tokenValido = MessageDigest.isEqual(tokenValidate, actual);
        if (!tokenValido) {
            throw new UnauthorizedException();
        }

    }

    private String getTokenRedis(Keys keys) throws AlgorithmInvalid {
        SecretProtocol protocol = this.get(keys);
        String keyValue = protocol.getKey().getValue();
        String key = SECRET + keyValue;
        String secret = redisTemplate.opsForValue().get(key);
        Long expire = redisTemplate.getExpire(key);
        getTempo(expire);
        if (StringUtils.isNotEmpty(secret))
            return secret;

        String random = RandomStringUtils.secure().nextAlphanumeric(32);
        redisTemplate.opsForValue().set(key, random, TEMPO_TOKEN);
        return random;
    }

    private SecretProtocol get(Keys key) throws AlgorithmInvalid {
        return this.secretProtocol.stream()
                .filter(secret -> Objects.equals(key, secret.getKey()))
                .findAny()
                .orElseThrow(AlgorithmInvalid::new);
    }

}