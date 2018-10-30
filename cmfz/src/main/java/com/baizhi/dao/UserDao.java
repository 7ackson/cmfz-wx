package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public List<User> selectAll();

    public int selectCountThree();

    public int selectCountTwo();

    public int selectCountOne();

    public List<Map<String,Object>> selectMan();

    public List<Map<String,Object>>  selectWoman();

    public Map selectOne(User u);

    public List<Map<String,Object>> selectRandom(@Param("id") int id);

}
