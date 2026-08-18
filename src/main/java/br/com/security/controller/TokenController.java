package br.com.security.controller;

import br.com.security.domain.AlgorithmService;
import br.com.security.domain.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/token")
@RestController
@RequiredArgsConstructor
public class TokenController {

    private final AlgorithmService algorithmService;

    @GetMapping
    public ResponseEntity<String> get(@RequestParam String key,
                                      @RequestParam String target) throws Exception {
        TokenDTO algorithm = algorithmService.get(key, target);
        return ResponseEntity.ok()
                .header("X-Signature", algorithm.assinatura())
                .body(algorithm.token());
    }

}