package com.cy.demo.elasticsearch.controller;

import java.util.Arrays;
import java.util.Map;

import com.cy.demo.elasticsearch.common.utils.PageUtils;
import com.cy.demo.elasticsearch.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cy.demo.elasticsearch.entity.DemoUserEntity;
import com.cy.demo.elasticsearch.service.DemoUserService;


/**
 * 用户表
 *
 * @author caoyoung
 * @email cy2298842774@163.com
 * @date 2018-12-13 16:46:50
 */
@RestController
@RequestMapping("user")
public class DemoUserController {
    @Autowired
    private DemoUserService demoUserService;

    /**
     * 列表
     */
    @GetMapping("/search/{page}/{pageSize}")
    public R list(@PathVariable Integer page,@PathVariable Integer pageSize,String userName,String nickName) {
        PageUtils result = demoUserService.queryPage(page,pageSize,userName,nickName);
        return R.ok(result);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        DemoUserEntity demoUser = demoUserService.selectById(id);

        return R.ok().put("demoUser", demoUser);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody DemoUserEntity demoUser) {
        demoUserService.insert(demoUser);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody DemoUserEntity demoUser) {
        demoUserService.updateById(demoUser);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/byIds")
    public R delete(@RequestBody Long[] ids) {
        demoUserService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

}
