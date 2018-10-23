package com.baizhi.test;


import com.baizhi.SpringbootMybatisApplication;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

public class AdminTest extends AdminExtends {

    @Autowired
    private AdminService adminService;

    @Test
    public void selectTest(){
        Admin admin = new Admin();
        admin.setName("a306694171");
        admin.setPassword("3166017q");
        Map map = adminService.login(admin);
        boolean b = (boolean) map.get("login");
        System.out.println(b);
    }


}
