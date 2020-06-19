package com.imcode.common.util;


import com.imcode.sys.shiro.ShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;

public class ShiroUtil {

    static {
        //初始化shiro安全管理器
        DefaultSecurityManager securityManager=new DefaultSecurityManager();
        //设置用户权限信息到安全管理器
        Realm realm=new ShiroRealm();
        securityManager.setRealm(realm);
        //使用SecurityUtils将securityManager设置到运行环境
        SecurityUtils.setSecurityManager(securityManager);
        //配置缓存管理器
        //CacheManager cache=new MemoryConstrainedCacheManager();
        CacheManager cache=new EhCacheManager();
        securityManager.setCacheManager(cache);
    }

    public static Subject login(String username, String password){
        //创建一个subject实例
        Subject subject=SecurityUtils.getSubject();
        //创建用于认证的token,记录用户的身份和凭证
        AuthenticationToken token=new UsernamePasswordToken(username,password);
        //主体登陆
        subject.login(token);
        return subject;

    }
}
