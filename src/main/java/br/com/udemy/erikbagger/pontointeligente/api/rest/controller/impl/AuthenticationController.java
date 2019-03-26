package br.com.udemy.erikbagger.pontointeligente.api.rest.controller.impl;

import br.com.udemy.erikbagger.pontointeligente.api.exception.BadRequestException;
import br.com.udemy.erikbagger.pontointeligente.api.rest.dto.JwtAuthenticationDto;
import br.com.udemy.erikbagger.pontointeligente.api.rest.dto.TokenDto;
import br.com.udemy.erikbagger.pontointeligente.api.security.jwt.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
    private static final String TOKEN_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Gera e retorna um novo token JWT.
     *
     * @param authenticationDto
     * @param result
     * @return ResponseEntity<Response < TokenDto>>
     * @throws AuthenticationException
     */
    @PostMapping
    public ResponseEntity<TokenDto> gerarTokenJwt(
            @Valid @RequestBody JwtAuthenticationDto authenticationDto)
            throws AuthenticationException {

        log.info("Gerando token para o email {}.", authenticationDto.getEmail());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationDto.getEmail(), authenticationDto.getSenha()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDto.getEmail());
        String token = jwtTokenUtil.obterToken(userDetails);

        return ResponseEntity.ok(new TokenDto(token));
    }

    /**
     * Gera um novo token com uma nova data de expiração.
     *
     * @param request
     * @return ResponseEntity<Response < TokenDto>>
     */
    @PostMapping(value = "/refresh")
    public ResponseEntity<TokenDto> gerarRefreshTokenJwt(HttpServletRequest request) throws BadRequestException {
        log.info("Gerando refresh token JWT.");
        Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));

        if (token.isPresent() && token.get().startsWith(BEARER_PREFIX)) {
            token = Optional.of(token.get().substring(7));
        }

        if (!token.isPresent()) {
            throw new BadRequestException("authentication.failed", "Token nao informado");
        } else if (!jwtTokenUtil.tokenValido(token.get())) {
            throw new BadRequestException("authentication.failed", "Token inválido ou expirado");
        }

        String refreshedToken = jwtTokenUtil.refreshToken(token.get());
        return ResponseEntity.ok(new TokenDto(refreshedToken));
    }
}