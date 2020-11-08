package cn.ga.securityjwt.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author GuAn
 * @Description
 * @createTime 2020年11月06日 17:21:00
 */
@Mapper
public interface JwtUserMapper {

    public List<Map<String, Object>> selectByUserName(String userName);



}
