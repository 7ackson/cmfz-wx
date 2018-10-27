package com.baizhi.dao;

import com.baizhi.entity.Album;

import java.util.List;

public interface AlbumDao {
    public List<Album> selectAll();

    public void insert(Album album);
}
