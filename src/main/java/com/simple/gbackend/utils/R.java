package com.simple.gbackend.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * 作为统一返回类
 */

public class R extends HashMap<String,Object> {

    public static R ok(){
        R r = new R();
        r.put("status",1);
        r.put("code",2000);
        r.put("message","success");
        return r;
    }

    public R put(String key,Object value){
        super.put(key,value);
        return this;
    }
    public static R ok(ResultCode resultCode){
        R r = new R();
        r.put("status",1);
        r.put("code",resultCode.getCode());
        r.put("message",resultCode.getMessage());
        return r;
    }
    public static R ok(Object data){
        R r = new R();
        r.put("status",1);
        r.put("code",2000);
        r.put("message","success");
        r.put("data",data);
        return r;
    }

    public static R ok(ResultCode resultCode,Object data){
        R r = new R();
        r.put("status",1);
        r.put("code",resultCode.getCode());
        r.put("message",resultCode.getMessage());
        r.put("data",data);
        return r;
    }

    public static R error(){
        R r = new R();
        r.put("status",0);
        r.put("code",4000);
        r.put("message","error");
        return r;
    }

    public static R error(Object data){
        R r = new R();
        r.put("status",0);
        r.put("code",4000);
        r.put("message","error");
        r.put("data",data);
        return r;
    }
    public static R error(ResultCode resultCode){
        R r = new R();
        r.put("status",0);
        r.put("code",resultCode.getCode());
        r.put("message",resultCode.getMessage());
        return r;
    }

    public static R error(ResultCode resultCode,Object data){
        R r = new R();
        r.put("status",0);
        r.put("code",resultCode.getCode());
        r.put("message",resultCode.getMessage());
        r.put("data",data);
        return r;
    }
}
