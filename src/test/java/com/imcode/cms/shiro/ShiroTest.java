package com.imcode.cms.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class ShiroTest {

    @Autowired
    private SecurityManager securityManager;
    @Test
    public void test(){
        UsernamePasswordToken token=new UsernamePasswordToken("admin","123456");
        Subject subject= SecurityUtils.getSubject();
        subject.login(token);
       /* subject.isPermitted("qqq");
        subject.isPermitted("qqq");
        subject.isPermitted("qqq");
        subject.isPermitted("qqq");
        subject.isPermitted("qqq");*/
    }
}
