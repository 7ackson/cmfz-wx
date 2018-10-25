package com.baizhi.test;


import com.baizhi.SpringbootMybatisApplication;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Menu;
import com.baizhi.service.AdminService;
import com.baizhi.service.BannerService;
import com.baizhi.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

public class AdminTest extends AdminExtends {

    @Autowired
    private BannerService bannerService;

    @Test
    public void selectTest(){
        Map map = bannerService.selectByPage(1,5);
        List<Banner> list = (List<Banner>) map.get("rows");
        for (Banner banner : list) {
            System.out.println(banner);
        }
    }


}
