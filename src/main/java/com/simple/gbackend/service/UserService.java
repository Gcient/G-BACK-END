package com.simple.gbackend.service;

import com.simple.gbackend.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.simple.gbackend.domain.request.PageAddUser;
import com.simple.gbackend.domain.request.PageUpdateUser;
import com.simple.gbackend.domain.request.UserRequest;
import com.simple.gbackend.utils.R;

import javax.servlet.http.HttpServletRequest;

/**
* @author GeJiaChen
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2022-12-07 14:26:39
*/
public interface UserService extends IService<User> {

    R login(UserRequest userRequest, HttpServletRequest request);

    R getCurrentUser(HttpServletRequest request);

    R outLogin(HttpServletRequest request);

    R getUsers(Integer current, Integer pageSize, HttpServletRequest request,String userAccount,String nickName,Integer gender,Integer isValid,Boolean openButton);

    R updateUserByAdmin(PageUpdateUser pageUpdateUser, HttpServletRequest request);

    R addUser(PageAddUser pageAddUser, HttpServletRequest request);

    R deleteUser(String userAccount, HttpServletRequest request);

    R deleteUserBatch(String[] userAccounts, HttpServletRequest request);
}
