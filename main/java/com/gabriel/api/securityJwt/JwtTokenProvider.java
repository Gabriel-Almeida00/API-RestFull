package com.gabriel.api.securityJwt;

import com.auth0.jwt.algorithms.Algorithm;
import com.gabriel.api.data.vo.v1.security.TokenVO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-lenght:3600000}")
    private long validtyInMilliseconds = 3600000;

    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenVO createAccessToken(String username, List<String> roles){
        Date now = new Date();
        Date validaty = new Date(now.getTime() + validtyInMilliseconds);
        var accessToken = getAccessToken(username, roles, now, validaty);
        var refreshToken = getAccessToken(username, roles, now);
        return new TokenVO(username, true, now, validaty, accessToken, refreshToken);
    }

    private String getAccessToken(String username, List<String> roles, Date now) {
    }

    private String getAccessToken(String username, List<String> roles, Date now, Date validaty) {
    }
}
