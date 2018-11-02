package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public List<User> selectAll();

    public List<Integer> selectCount();

    public List<Map<String, Object>> selectMan();

    public List<Map<String, Object>> selectWoman();

    public Map selectOne(User u);

    public List<Map<String,Object>> selectRandom(int id);

    public Map insert(User user);

    public void inserts(List<User> list);

}
