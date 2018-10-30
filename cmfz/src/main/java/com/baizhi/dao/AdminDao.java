package com.baizhi.dao;

import com.baizhi.entity.Admin;

import java.util.List;
import java.util.Map;

public interface AdminDao {
    public Admin selectOne(Admin admin);

    public List<Map> selectMap();
}
