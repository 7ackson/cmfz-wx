package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;

import java.util.List;
import java.util.Map;

public interface AlbumService {

    public void insert(Album album);

    public List<Album> selectAll();

    public Map<Album,List<Chapter>> selectTwo(String uuid);
}
