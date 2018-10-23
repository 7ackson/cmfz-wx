package com.baizhi.test;

import com.baizhi.SpringbootMybatisApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//自动开启spring工厂
@RunWith(SpringRunner.class)
//启动spring boot自动配置
@SpringBootTest(classes = SpringbootMybatisApplication.class)
public class AdminExtends {
}
