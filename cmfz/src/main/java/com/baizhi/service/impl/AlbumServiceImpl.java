package com.baizhi.service.impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("AlbumService")
@Transactional
public class AlbumServiceImpl implements AlbumService{

    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private ChapterDao chapterDao;

    @Override
    public void insert(Album album) {
        albumDao.insert(album);
    }

    @Override
    public List<Album> selectAll() {
        return albumDao.selectAll();
    }

    @Override
    public Map<Album,List<Chapter>> selectTwo(String uuid) {
        Map map = new HashMap();
        System.out.println(albumDao.selectOne(uuid));
        System.out.println(chapterDao.selectTwo(uuid));
        map.put(albumDao.selectOne(uuid),chapterDao.selectTwo(uuid));
        System.out.println(map);
        return map;
    }
}