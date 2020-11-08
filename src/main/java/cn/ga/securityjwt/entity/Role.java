package cn.ga.securityjwt.entity;

import lombok.Data;

import java.util.List;

/**
 * @author GuAn
 * @Description
 * @createTime 2020年11月08日 17:17:00
 */
@Data
public class Role {
    private String id;
    private String role;
    private List<JwtUser> users;

}
