package com.baizhi.service.impl;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("BannerService")
@Transactional
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao;

    @Override
    public void insert(Banner banner) {
        bannerDao.insert(banner);
    }

    @Override
    public void delete(int[] ids) {
        bannerDao.delete(ids);
    }

    @Override
    public void update(Banner banner) {
        bannerDao.update(banner);
    }

    @Override
    public Banner selectOne(int id) {
        return bannerDao.selectOne(id);
    }

    @Override
    public Map selectByPage(int page, int rows) {
        Map map = new HashMap();
        int start = (page - 1) * rows;
        int end = page * rows;
        if (page == -1) {
            start = (page - 1) * rows + 1;
            end = page * rows;
        }
        List<Banner> list = bannerDao.selectByPage(start, end);
        int count = bannerDao.selectCount();
        map.put("rows", list);
        map.put("total", count);
        return map;
    }
}
