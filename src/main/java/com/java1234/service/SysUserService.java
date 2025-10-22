package com.java1234.service;

import com.java1234.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author java1234
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2022-08-30 10:49:41
*/
public interface SysUserService extends IService<SysUser> {


    SysUser getByUsername(String username);

    String getUserAuthorityInfo(Long userId);
}
