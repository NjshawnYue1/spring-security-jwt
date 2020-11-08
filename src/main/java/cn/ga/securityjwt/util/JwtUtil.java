package cn.ga.securityjwt.util;

import cn.ga.securityjwt.entity.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;

import java.security.Key;
import java.util.*;

/**
 * @author GuAn
 * @Description
 * @createTime 2020年11月08日 13:58:00
 */
@Slf4j
public class JwtUtil {

    private final static String secret = "275NJUfDuBHcxcrmCoRaXShv9iBCMHrP/9KlMCbFu48=";

    public static String createJwt(JwtUser jwtUser) {
//        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        String s = Base64.getEncoder().encodeToString(key.getEncoded());
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        String username = jwtUser.getUsername();
        Calendar cl = Calendar.getInstance();
        cl.setTime(new Date());
        cl.add(Calendar.DATE, 7);
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        Map<String, Object> map = new HashMap<>(2);
        map.put("username", username);
        map.put("roles", authorities);
        return Jwts.builder()
                .setHeaderParam("TYP", "JWT")
                .setClaims(map)
                .setExpiration(cl.getTime())
                .signWith(key).compact();
    }


    public static Jws<Claims> parseToken(String token) {

         return Jwts.parserBuilder().setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(token.replace("Bearer", ""));
    }

}
