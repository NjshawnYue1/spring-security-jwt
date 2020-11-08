package cn.ga.securityjwt.service;

import cn.ga.securityjwt.dao.JwtUserMapper;
import cn.ga.securityjwt.entity.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author GuAn
 * @Description
 * @createTime 2020年11月06日 17:24:00
 */
@Service
public class JwtUserServiceImpl implements UserDetailsService {
    @Resource
    JwtUserMapper mapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<Map<String, Object>> maps = mapper.selectByUserName(userName);
        Map<String, Object> map = maps.get(0);
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUserName((String)map.get("user_name"));
        jwtUser.setPassword((String)map.get("pass_word"));
        List<String> list = new ArrayList<String>();
        maps.forEach(item->{
            list.add((String)item.get("role"));
        });
        jwtUser.setRoles(list);

        return jwtUser;

    }
}
