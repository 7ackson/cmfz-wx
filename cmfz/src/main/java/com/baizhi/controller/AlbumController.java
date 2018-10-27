package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/zjinsert")
    public @ResponseBody boolean insert(MultipartFile cover, Album album, HttpServletRequest request){
        try {
            String path = request.getSession().getServletContext().getRealPath("/");

            File file = new File(path+"/upload");

            if (!file.exists()){
                file.mkdir();
            }

            String originalFilename = cover.getOriginalFilename();

            String s = UUID.randomUUID().toString();

            String extension = FilenameUtils.getExtension(originalFilename);

            String newName = s +"."+ extension;

            String uuid = UUID.randomUUID().toString();

            album.setId(uuid);

            album.setCoverImg(newName);

            albumService.insert(album);
            cover.transferTo(new File(file,newName));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("/selectAll")
    public @ResponseBody List<Album> selectAll(){
        return albumService.selectAll();
    }

}
