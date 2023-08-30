package com.example.springboot;

import com.alibaba.fastjson.JSON;
import com.example.springboot.domain.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootApplicationTests {

    @Test
    void contextLoads() {
        SysUser sysUser = new SysUser();
        sysUser.setId(null);
        sysUser.setUserName("name");
        System.out.println(JSON.toJSONString(sysUser));
    }

}
