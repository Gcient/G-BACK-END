package com.simple.gbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple.gbackend.constants.UserConstant;
import com.simple.gbackend.domain.entity.User;
import com.simple.gbackend.domain.request.PageAddUser;
import com.simple.gbackend.domain.request.PageUpdateUser;
import com.simple.gbackend.domain.request.UserRequest;
import com.simple.gbackend.domain.response.PageLoginUser;
import com.simple.gbackend.service.UserService;
import com.simple.gbackend.mapper.UserMapper;
import com.simple.gbackend.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Queue;

/**
* @author GeJiaChen
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2022-12-07 14:26:39
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public R login(UserRequest userRequest, HttpServletRequest request) {
        System.out.println(userRequest);
        // TODO: 2022/12/7   完善对用户名和密码格式的校验
        if (userRequest.getUserAccount()==null){
            return R.error();
        }

        User user = this.getOne(new QueryWrapper<User>().eq("user_account", userRequest.getUserAccount()));
        if(user==null){
            return R.error();
        }
        if(user.getUserPassword().equals(userRequest.getUserPassword())){
            PageLoginUser loginUser  = new PageLoginUser();
            BeanUtils.copyProperties(user,loginUser);
            request.getSession().setAttribute(UserConstant.USER_LOGIN_INFO,loginUser);
            return R.ok();
        }
        return R.error();
    }

    @Override
    public R getCurrentUser(HttpServletRequest request) {
        PageLoginUser user = (PageLoginUser)request.getSession().getAttribute(UserConstant.USER_LOGIN_INFO);
        if(user==null){
            return R.error();
        }
        return R.ok(user);
    }

    @Override
    public R outLogin(HttpServletRequest request) {
        PageLoginUser user = (PageLoginUser)request.getSession().getAttribute(UserConstant.USER_LOGIN_INFO);
        if(user==null){
            return R.ok();
        }
        request.getSession().removeAttribute(UserConstant.USER_LOGIN_INFO);
        return R.ok();
    }

    @Override
    public R getUsers(Integer current, Integer pageSize, HttpServletRequest request,String userAccount,String nickName,Integer gender,Integer isValid,Boolean openButton) {
        PageLoginUser user = (PageLoginUser)request.getSession().getAttribute(UserConstant.USER_LOGIN_INFO);
        if (user==null){
            return R.error();
        }
        if(user.getPermissionNumber()!=0){
            return R.error();
        }
        if (userAccount==null&&nickName==null&&gender==null&&isValid==null){
            Page<User> page = new Page<>(current,pageSize);
            if(page==null){
                return R.error();
            }
            IPage<User> users = this.page(page);
            if (users==null){
                return R.error();
            }
            return R.ok(users.getRecords()).put("total",users.getTotal()).put("success",true);
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(!openButton){
            if(userAccount!=null){
                wrapper.like("user_account",userAccount);
            }
            if (nickName!=null){
                wrapper.and(item->{
                    item.like("nick_name",nickName);
                });
            }
            if (gender!=null){
                wrapper.and(item->{
                    item.like("gender",gender);
                });
            }
            if (isValid!=null){
                wrapper.and(item->{
                    item.like("is_valid",isValid);
                });
            }
        }else{
            if(userAccount!=null){
                wrapper.eq("user_account",userAccount);
            }
            if (nickName!=null){
                wrapper.and(item->{
                    item.eq("nick_name",nickName);
                });
            }
            if (gender!=null){
                wrapper.and(item->{
                    item.eq("gender",gender);
                });
            }
            if (isValid!=null){
                wrapper.and(item->{
                    item.eq("is_valid",isValid);
                });
            }
        }
        List<User> list = this.list(wrapper);
        return R.ok(list).put("total",list.size()).put("success",true);
    }

    @Override
    public R updateUserByAdmin(PageUpdateUser pageUpdateUser, HttpServletRequest request) {
        PageLoginUser user = (PageLoginUser)request.getSession().getAttribute(UserConstant.USER_LOGIN_INFO);
        if(user==null){
            return R.error();
        }
        if(user.getPermissionNumber()!=0){
            return R.error();
        }
        User newUser = new User();
        BeanUtils.copyProperties(pageUpdateUser,newUser);
        newUser.setUpdateTime(new Date());
        System.out.println(newUser);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_account",pageUpdateUser.getUserAccount());
        this.update(newUser,wrapper);
        return R.ok();
    }

    @Override
    public R addUser(PageAddUser pageAddUser, HttpServletRequest request) {
        PageLoginUser user = (PageLoginUser)request.getSession().getAttribute(UserConstant.USER_LOGIN_INFO);
        if(user==null||user.getPermissionNumber()!=0){
            return R.error();
        }
        if(pageAddUser==null){
            return R.error();
        }
        if(pageAddUser.getUserAccount()==null||pageAddUser.getUserAccount().equals("")){
            return R.error();
        }
        if(pageAddUser.getNickName()==null||pageAddUser.getNickName().equals("")){
            return R.error();
        }
        if(pageAddUser.getUserPassword()==null||pageAddUser.getUserPassword().equals("")){
            return R.error();
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_account",pageAddUser.getUserAccount());
        User one = this.getOne(wrapper);
        if(one!=null){
            return R.error();
        }

        QueryWrapper<User> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("nick_Name",pageAddUser.getNickName());
        User one2 = this.getOne(wrapper2);
        if(one2!=null){
            return R.error();
        }
        User user1 = new User();
        BeanUtils.copyProperties(pageAddUser,user1);
        System.out.println(user1);
        boolean b = this.save(user1);
        if(b){
            return R.ok();
        }
        return R.error();
    }

    @Override
    public R deleteUser(String userAccount, HttpServletRequest request) {
        PageLoginUser user = (PageLoginUser)request.getSession().getAttribute(UserConstant.USER_LOGIN_INFO);
        if(user==null||user.getPermissionNumber()!=0){
            return R.error();
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_account",userAccount);
        User one = this.getOne(wrapper);
        if(one==null){
            return R.error();
        }
        boolean b = this.removeById(one.getId());
        if(b) {
            return R.ok();
        }
        return R.error();
    }

    @Override
    public R deleteUserBatch(String[] userAccounts, HttpServletRequest request) {
        if(userAccounts==null||userAccounts.length==0){
            return R.error();
        }
        PageLoginUser user = (PageLoginUser)request.getSession().getAttribute(UserConstant.USER_LOGIN_INFO);
        if(user==null||user.getPermissionNumber()!=0){
            return R.error();
        }
        //自定义删除
//        this.removeByIds()
        return null;
    }
}




