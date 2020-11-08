package cn.ga.securityjwt.config.filter;

import cn.ga.securityjwt.util.JwtUtil;
import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author GuAn
 * @Description
 * @createTime 2020年11月08日 16:25:00
 */
@Slf4j
public class MyAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationManager(httpServletRequest);
        if (authenticationToken == null) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationManager(HttpServletRequest httpServletRequest) {

        String token = httpServletRequest.getHeader("Authorization");
        if (StrUtil.isNotBlank(token) && token.startsWith("Bearer ")) {
            try {
                Jws<Claims> claimsJws = JwtUtil.parseToken(token);
                Claims body = claimsJws.getBody();
                String username = (String) body.get("username");
                ArrayList<LinkedHashMap<String, Object>> roles = (ArrayList) (body.get("roles"));
                List<SimpleGrantedAuthority> list = roles.stream().map(authority -> new SimpleGrantedAuthority((String) authority.get("authority"))).collect(Collectors.toList());

                if (StrUtil.isNotBlank(username)) {
                    return new UsernamePasswordAuthenticationToken(username, null, list);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
