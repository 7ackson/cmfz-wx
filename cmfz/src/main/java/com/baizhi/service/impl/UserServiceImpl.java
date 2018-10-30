package com.baizhi.service.impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    @Override
    public List<Integer> selectCount() {
        List list = new ArrayList();
        list.add(userDao.selectCountOne());
        list.add(userDao.selectCountTwo());
        list.add(userDao.selectCountThree());
        return list;
    }

    @Override
    public List<Map<String, Object>> selectMan() {
        return userDao.selectMan();
    }

    @Override
    public List<Map<String, Object>> selectWoman() {
        return userDao.selectWoman();
    }

    @Override
    public Map selectOne(User u) {
        Map map = new HashMap();
        System.out.println(map);
        if(userDao.selectOne(u) != null){
            map = userDao.selectOne(u);
        }else{
            map.put("error","-200");
            map.put("errmsg","密码错误");
        }
        return map;
    }

    @Override
    public List<Map<String,Object>> selectRandom(int id) {
        return userDao.selectRandom(id);
    }

    @Override
    public Map insert(User user) {
        Map map = new HashMap();
        System.out.println("user:"+user);
        if(userDao.selectPhone(user) != null){
            map.put("error","-200");
            map.put("erroe_msg","该手机号已经存在");
        }else{
            map.put("password",user.getPassword());
            map.put("phone",user.getPhoneNum());
            userDao.insert(user);
        }
        return map;
    }
}
