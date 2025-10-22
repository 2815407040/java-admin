package com.java1234.service;

import com.java1234.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author java1234
* @description 针对表【sys_menu】的数据库操作Service
* @createDate 2022-09-15 07:52:51
*/
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> buildTreeMenu(List<SysMenu> sysMenuList);
}
