package com.baizhi.service.impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.MenuDao;
import com.baizhi.service.StairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("StairService")
@Transactional
public class StairServiceImpl implements StairService {
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private AlbumDao albumDao;

    public Map selectAll(){
        Map map = new HashMap();
        map.put("menu",menuDao.selectAll());
        map.put("album",albumDao.selectAll());
        return map;
    }
}
