package com.java1234.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.java1234.entity.R;
import com.java1234.entity.SysMenu;
import com.java1234.entity.SysUser;
import com.java1234.entity.SysUserRole;
import com.java1234.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 系统菜单Controller控制器
 * @author java1234_小锋 （公众号：java1234）
 * @site www.java1234.vip
 * @company 南通小锋网络科技有限公司
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 查询所有菜单树信息
     * @return
     */
    @RequestMapping("/treeList")
    @PreAuthorize("hasAuthority('system:menu:query')")
    public R treeList(){
        List<SysMenu> menuList = sysMenuService.list(new QueryWrapper<SysMenu>().orderByAsc("order_num"));
        return R.ok().put("treeMenu",sysMenuService.buildTreeMenu(menuList));
    }

    /**
     * 添加或者修改
     * @param sysMenu
     * @return
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('system:menu:add')"+"||"+"hasAuthority('system:menu:edit')")
    public R save(@RequestBody SysMenu sysMenu){
        if(sysMenu.getId()==null || sysMenu.getId()==-1){
            sysMenu.setCreateTime(new Date());
            sysMenuService.save(sysMenu);
        }else{
            sysMenu.setUpdateTime(new Date());
            sysMenuService.updateById(sysMenu);
        }
        return R.ok();
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('system:menu:query')")
    public R findById(@PathVariable(value = "id")Long id){
        SysMenu sysMenu = sysMenuService.getById(id);
        Map<String,Object> map=new HashMap<>();
        map.put("sysMenu",sysMenu);
        return R.ok(map);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('system:menu:delete')")
    public R delete(@PathVariable(value = "id")Long id){
        int count = sysMenuService.count(new QueryWrapper<SysMenu>().eq("parent_id", id));
        if(count>0){
            return R.error("请先删除子菜单！");
        }
        sysMenuService.removeById(id);
        return R.ok();
    }


}
