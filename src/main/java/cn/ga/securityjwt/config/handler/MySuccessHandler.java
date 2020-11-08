package cn.ga.securityjwt.config.handler;

import cn.ga.securityjwt.entity.JwtUser;
import cn.ga.securityjwt.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author GuAn
 * @Description
 * @createTime 2020年11月08日 21:30:00
 */
@Component
@Slf4j
public class MySuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        JwtUser principal = (JwtUser) authentication.getPrincipal();
        String token = JwtUtil.createJwt(principal);
        httpServletResponse.setHeader("Authorization","Bearer "+token);


    }
}
