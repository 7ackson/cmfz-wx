package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDao {
    public void insert(Banner banner);

    public void delete(int[] ids);

    public void update(Banner banner);

    public List<Banner> selectByPage(@Param("start") int start, @Param("end")int end);

    public int selectCount();

    public Banner selectOne(int id);
}
