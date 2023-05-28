package com.solution.recipetalk.security.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.solution.recipetalk.security.service.UserLoginContext;
import com.solution.recipetalk.security.token.JWTAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;

@Slf4j
public class RefreshAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Value("${jwt.refresh-secret-key}") private String refreshSecretKey;
    @Value("${jwt.issuer}") private String issuer;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // refresh token 검증
        String username = verifyToken((JWTAuthenticationToken) authentication);

        UserLoginContext userLoginContext = (UserLoginContext) userDetailsService.loadUserByUsername(username);

        return new JWTAuthenticationToken(userLoginContext.getUserLogin(), null, userLoginContext.getAuthorities());
    }

    private String verifyToken(JWTAuthenticationToken token) {
        String username;

        try {
            Algorithm algorithm = Algorithm.HMAC256(refreshSecretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();

            DecodedJWT jwt = verifier.verify(token.getJsonWebToken());

            Map<String, Claim> claims = jwt.getClaims();

            username = claims.get("username").asString();

        } catch(SignatureVerificationException e) {
            log.warn(e.getMessage());
            throw new BadCredentialsException("invalid token signature");
        } catch(TokenExpiredException e) {
            log.warn(e.getMessage());
            throw new BadCredentialsException("token has expired");
        } catch(JWTVerificationException e) {
            log.warn(e.getMessage());
            throw new BadCredentialsException("invalid token");
        }

        return username;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JWTAuthenticationToken.class);
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
