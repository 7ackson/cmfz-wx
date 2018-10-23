package com.baizhi.test;


import com.baizhi.SpringbootMybatisApplication;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Menu;
import com.baizhi.service.AdminService;
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
    private MenuService menuService;

    @Test
    public void selectTest(){
        List<Menu> list = menuService.selectAll();
        for (Menu menu : list) {
            System.out.println(menu);
            for (Menu menu1 : menu.getList()) {
                System.out.println(menu1);
            }
        }
    }


}
