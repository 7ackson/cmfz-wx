package com.baizhi.controller;


import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/detail")
public class DetailController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/wen")
    public Map<Album,List<Chapter>> selectAll(String uuid){
       return albumService.selectTwo(uuid);
    }
}
