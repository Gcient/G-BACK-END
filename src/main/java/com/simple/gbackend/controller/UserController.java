package com.simple.gbackend.controller;


import com.simple.gbackend.domain.request.PageAddUser;
import com.simple.gbackend.domain.request.PageUpdateUser;
import com.simple.gbackend.domain.request.UserRequest;
import com.simple.gbackend.service.UserService;
import com.simple.gbackend.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    public UserService userService;

    @PostMapping("/login")
    public R login(@RequestBody UserRequest userRequest, HttpServletRequest request){
        return userService.login(userRequest,request);
    }

    @GetMapping("/current")
    public R getCurrentUser(HttpServletRequest request){
        return userService.getCurrentUser(request);
    }

    @GetMapping("/outLogin")
    public R outLogin(HttpServletRequest request){
        return userService.outLogin(request);
    }

    @GetMapping("/list")
    public R getUsers(@RequestParam("current") Integer current,
                      @RequestParam("pageSize") Integer pageSize,
                      @RequestParam(value = "userAccount",required = false) String userAccount,
                      @RequestParam(value = "nickName",required = false) String nickName,
                      @RequestParam(value = "gender",required = false) Integer gender,
                      @RequestParam(value = "isValid",required = false) Integer isValid,
                      @RequestParam(value = "openButton",required = false) Boolean openButton,
                      HttpServletRequest request){
        return userService.getUsers(current,pageSize,request,userAccount,nickName,gender,isValid,openButton);
    }

    @PostMapping("/updateUserByAdmin")
    public R updateUserByAdmin(@RequestBody PageUpdateUser pageUpdateUser,HttpServletRequest request){
        return userService.updateUserByAdmin(pageUpdateUser,request);
    }

    @PostMapping("/addUser")
    public R addUser(@RequestBody PageAddUser pageAddUser,HttpServletRequest request){
        return userService.addUser(pageAddUser,request);
    }

    @PostMapping("/deleteUser")
    public R deleteUser(@RequestBody String userAccount,HttpServletRequest request){
        return userService.deleteUser(userAccount,request);
    }

    @PostMapping("/deleteUserBatch")
    public R deleteUserBatch(@RequestBody String[] userAccounts,HttpServletRequest request){
        return userService.deleteUserBatch(userAccounts,request);
    }
}
