package com.java1234.controller;

import com.java1234.entity.R;
import com.java1234.entity.SysUser;
import com.java1234.service.SysUserService;
import com.java1234.util.JwtUtils;
import com.java1234.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试
 * @author java1234_小锋 （公众号：java1234）
 * @site www.java1234.vip
 * @company 南通小锋网络科技有限公司
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/user/list")
    // @PreAuthorize("hasRole('ROLE_admin2')")
    @PreAuthorize("hasAuthority('system:user2:list')")
    public R userList(@RequestHeader(required = false)String token){
        if(StringUtil.isNotEmpty(token)){
            Map<String,Object> resutlMap=new HashMap<>();
            List<SysUser> userList = sysUserService.list();
            resutlMap.put("userList",userList);
            return R.ok(resutlMap);
        }else{
            return R.error(401,"没有权限访问");
        }

    }

    @RequestMapping("/login")
    public R login(){
        String token = JwtUtils.genJwtToken("java1234");
        return R.ok().put("token",token);
    }
}
