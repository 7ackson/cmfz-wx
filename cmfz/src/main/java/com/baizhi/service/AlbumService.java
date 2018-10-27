package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;

public interface AlbumService {

    public void insert(Album album);

    public List<Album> selectAll();
}
