package com.stylefeng.guns.modular.tssc.controller;

import com.stylefeng.guns.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 招聘控制器
 *
 * @author fengshuonan
 * @Date 2018-04-24 17:08:26
 */
@Controller
@RequestMapping("/recruit")
public class RecruitController extends BaseController {

    private String PREFIX = "/tssc/recruit/";

    /**
     * 跳转到招聘首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "recruit.html";
    }

    /**
     * 跳转到添加招聘
     */
    @RequestMapping("/recruit_add")
    public String recruitAdd() {
        return PREFIX + "recruit_add.html";
    }

    /**
     * 跳转到修改招聘
     */
    @RequestMapping("/recruit_update/{recruitId}")
    public String recruitUpdate(@PathVariable Integer recruitId, Model model) {
        return PREFIX + "recruit_edit.html";
    }

    /**
     * 获取招聘列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return null;
    }

    /**
     * 新增招聘
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add() {
        return super.SUCCESS_TIP;
    }

    /**
     * 删除招聘
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete() {
        return SUCCESS_TIP;
    }


    /**
     * 修改招聘
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update() {
        return super.SUCCESS_TIP;
    }

    /**
     * 招聘详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Object detail() {
        return null;
    }
}
