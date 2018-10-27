package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import static java.lang.String.valueOf;

@Controller
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/chapterinsert")
    private @ResponseBody boolean  chapterInsert(String albumName,MultipartFile urlMp3, Chapter chapter, HttpServletRequest request){
        try {
            long bytes = urlMp3.getSize();
            double d = bytes/1024/1024;
            System.out.println(d);
            String size = valueOf(d);
            String realPath = request.getSession().getServletContext().getRealPath("/");
            File file = new File(realPath+"/mp3");
            if(!file.exists()){
                file.mkdir();
            }
            String originalFilename = urlMp3.getOriginalFilename();
            String s = UUID.randomUUID().toString();
            String extension = FilenameUtils.getExtension(originalFilename);
            System.out.println();
            String newName = s+"."+extension;
            try {
                urlMp3.transferTo(new File(file,newName));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
            File source = new File(realPath + "/mp3/" + newName);
            Encoder encoder = new Encoder();
            MultimediaInfo m = encoder.getInfo(source);
            long ls = m.getDuration();
            long fen = ls/1000/60;
            long miao = ls/1000%60;
            String time = valueOf(fen);
            String sss = valueOf(miao);
            chapter.setUrl(newName);
            chapter.setSize(size+"MB");
            chapter.setDuration(time+"分"+sss+"秒");
            System.out.println("albumName"+albumName);
            System.out.println(chapter);
            Album album = new Album();
            album.setId(albumName);
            chapter.setAlbum(album);
            chapterService.chapterInsert(chapter);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("/down")
    public void down(Chapter chapter, HttpServletRequest request, HttpServletResponse response){
        String realPath = request.getSession().getServletContext().getRealPath("/mp3");
        String url = chapter.getUrl();
        String filePath = realPath+"/"+url;
        File file = new File(filePath);

        String extension = FilenameUtils.getExtension(chapter.getUrl());
        String newName = chapter.getName() + "." + extension;
        try {
            response.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode(newName,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("audio/mpeg");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(file));
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("selectOne")
    public @ResponseBody Chapter selectOne(int id){
        return chapterService.selectOne(id);
    }

}
