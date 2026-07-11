package br.com.security.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.core5.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class NeedsAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        if (StringUtils.isEmpty(authorization) || !authorization.startsWith("Bearer ")) {
            forbidden(response);
            return;
        }
        unauthorized(response);
    }

    private void unauthorized(HttpServletResponse response) throws IOException {
        response.setStatus(401);
        objectMapper.writeValue(response.getWriter(), "credential unauthorized");
    }

    private void forbidden(HttpServletResponse response) throws IOException {
        response.setStatus(403);
        objectMapper.writeValue(response.getWriter(), "credential Invalid");
    }
}
