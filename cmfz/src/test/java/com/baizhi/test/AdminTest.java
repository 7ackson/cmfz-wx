package com.baizhi.test;


import com.baizhi.SpringbootMybatisApplication;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Menu;
import com.baizhi.entity.User;
import com.baizhi.service.AdminService;
import com.baizhi.service.BannerService;
import com.baizhi.service.MenuService;
import com.baizhi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

public class AdminTest extends AdminExtends {

    @Autowired
    private UserService userService;
    @Test
    public void selectTest(){
        List<Map<String, Object>> list = userService.selectMan();
        for (Map<String, Object> stringObjectMap : list) {
            System.out.println(stringObjectMap);
        }

    }
}
