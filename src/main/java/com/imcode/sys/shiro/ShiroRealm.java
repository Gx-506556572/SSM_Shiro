package com.imcode.sys.shiro;

import com.imcode.sys.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm  {

//登录认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
      String username=token.getUsername();
      String password=new String(token.getPassword());

        User user=new User("admin","123456",0);
        if(!user.getUsername().equals(username)){
            throw new UnknownAccountException("用户不存在");
        }
        if (!user.getPassword().equals(password)){
            throw new CredentialsException("密码错误");
        }
        if (user.getStatus()==1){
            throw new DisabledAccountException("账号被禁用");
        }
        if (user.getStatus()==2){
            throw new LockedAccountException("账号被锁定");
        }
        //创建简单认证信息对象
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(token.getPrincipal(),token.getCredentials(),getName());
        System.out.println("登录认证");
        return info;
    }
//授权认证
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        String username= principal.getPrimaryPrincipal().toString();

//角色
        Set<String> roleNameSet=new HashSet<String>();
        roleNameSet.add("系统管理员");
        roleNameSet.add("运维");
//权限
        Set<String> authenticNameSet=new HashSet<String>();
        authenticNameSet.add("sys:user:create");
        authenticNameSet.add("sys:user:update");
        authenticNameSet.add("sys:user:select");
        authenticNameSet.add("sys:user:delete");

        //简单授权对象，包括用户角色和权限信息
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addRoles(roleNameSet);
        info.addStringPermissions(authenticNameSet);
        System.out.println("授权完成");
        return info;
    }


}
