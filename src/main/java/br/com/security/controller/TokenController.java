package br.com.security.controller;

import br.com.security.exception.AlgorithmInvalid;
import br.com.security.secret.TokenService;
import br.com.security.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RequestMapping("/token")
@RestController
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @GetMapping
    public ResponseEntity<String> get(@RequestParam String key) throws NoSuchAlgorithmException, AlgorithmInvalid {
        return ResponseEntity.ok(tokenService.get(key));
    }

    @GetMapping("valid")
    public ResponseEntity<Void> valid(@RequestParam String token, @RequestParam String key) throws NoSuchAlgorithmException, AlgorithmInvalid, UnauthorizedException {
        tokenService.valid(token, key);
        return ResponseEntity.noContent().build();
    }


}
