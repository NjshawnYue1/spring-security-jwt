package cn.ga.securityjwt.config.filter;

import cn.ga.securityjwt.entity.JwtUser;
import cn.ga.securityjwt.service.JwtUserServiceImpl;
import cn.ga.securityjwt.util.JwtUtil;
import cn.ga.securityjwt.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author GuAn
 * @Description
 * @createTime 2020年11月08日 15:08:00
 */
@Slf4j
public class MyAuthenticateFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    MyAuthenticateFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/login");
    }


    /**
     * 认证
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken uToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(uToken);

    }

    /**
     * 认证成功之后
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        JwtUser principal = (JwtUser) authResult.getPrincipal();
        log.info("principal[ {} ]", principal);
        String token = JwtUtil.createJwt(principal);

        response.setHeader("Authorization", "Bearer " + token);
    }


}
