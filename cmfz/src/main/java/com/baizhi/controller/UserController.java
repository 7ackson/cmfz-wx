package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    @RequestMapping("/selectAll")
    public @ResponseBody List<User> selectAll(){
        return userService.selectAll();
    }

    @RequestMapping("/selectCount")
    public @ResponseBody List<Integer> selectCount(){
        return userService.selectCount();
    }

    @RequestMapping("/selectMan")
    public @ResponseBody
    List<Map<String, Object>> selectMan() {
        return userService.selectMan();
    }

    @RequestMapping("/selectWoman")
    public @ResponseBody
    List<Map<String, Object>> selectWoman(String phoneNum) {
        return userService.selectWoman();
    }

    @RequestMapping("/insert")
    public @ResponseBody Map insert(User u){
        return userService.insert(u);
    }

    @RequestMapping("/login")
    public @ResponseBody Map selectOne(User u){
        return userService.selectOne(u);
    }

    @RequestMapping("/member")
    public @ResponseBody List<Map<String,Object>> selectRandom(int id){
        return userService.selectRandom(id);
    }
}
