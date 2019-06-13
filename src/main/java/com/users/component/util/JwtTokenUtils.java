package com.users.component.util;

import com.users.modules.user.entity.CrUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiangchao on 2019/2/27.
 * Jwt工具类
 */
@Component
public class JwtTokenUtils {
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_ID = "id";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String CLAIM_KEY_ROLES = "roles";

    /**
     * 密钥
     */
    private String secret="szC!run9Rcx_a";

    //过期时长，单位为秒,可以通过配置写入。
    private int expiration = 86400;

    /**
     * 从令牌中获取用户名
     * @param token 令牌
     * @return
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            username =getClaimsFromToken(token).getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 从令牌中获取创建时间
     * @param token 令牌
     * @return
     */
    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    /**
     * 从令牌中获取过期时间
     * @param token 令牌
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 判断令牌是否过期
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 生成过期时间
     * @return
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从数据中生成令牌
     * @param claims
     * @return
     */
    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从用户信息生成令牌
     * @param userDetails 用户
     * @return
     */
    public String generateToken(CrUser userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(CLAIM_KEY_ID, userDetails.getId());
        claims.put(CLAIM_KEY_ROLES, userDetails.getAuthorities());
        return generateToken(claims);
    }

    public Boolean canTokenBeRefreshed(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 刷新令牌
     * @param token 令牌
     * @return
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 注销令牌(设置过期时间为当前时间)
     * @param token 令牌
     * @return
     */
    public String logoutTokens(String token) {
        String logoutToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            //claims.put(CLAIM_KEY_CREATED, new Date());
            logoutToken = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(new Date(System.currentTimeMillis()))
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact();
        } catch (Exception e) {
            logoutToken = null;
        }
        return logoutToken;
    }

    /**
     * 验证令牌
     * @param token 令牌
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        CrUser user = (CrUser) userDetails;
        final String username = getUsernameFromToken(token);
        //final Date created = getCreatedDateFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }
}
