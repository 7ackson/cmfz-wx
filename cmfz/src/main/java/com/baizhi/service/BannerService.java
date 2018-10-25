package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.Map;

public interface BannerService {
    public void insert(Banner banner);

    public void delete(int[] ids);

    public void update(Banner banner);

    public Banner selectOne(int id);

    public Map selectByPage(int page,int rows);
}
