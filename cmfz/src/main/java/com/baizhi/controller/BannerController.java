package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/selectByPage")
    public @ResponseBody Map selectByPage(int page, int rows){
        Map map = bannerService.selectByPage(page, rows);
        return map;
    }

    @RequestMapping("/insert")
    public @ResponseBody boolean insert(Banner banner, MultipartFile img,HttpServletRequest request){
        try {
            String path = request.getSession().getServletContext().getRealPath("/");
            File file = new File(path+"/upload");
            if(!file.exists()){
                file.mkdir();
            }
            String originalFilename = img.getOriginalFilename();
            String uName = UUID.randomUUID().toString();
            String extension = FilenameUtils.getExtension(originalFilename);
            String newName = uName+"."+extension;
            banner.setUrl(newName);
            img.transferTo(new File(file,newName));
            bannerService.insert(banner);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("/update")
    public void update(Banner banner){
        bannerService.update(banner);
    }

    @RequestMapping("delete")
    public @ResponseBody boolean delete(int id){
        try {
            bannerService.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("selectOne")
    public @ResponseBody Banner selectOne(int id){
        return bannerService.selectOne(id);
    }

}
