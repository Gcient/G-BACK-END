package com.simple.gbackend.mapper;

import com.simple.gbackend.domain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author GeJiaChen
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2022-12-07 14:26:39
* @Entity com.simple.gbackend.domain.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




