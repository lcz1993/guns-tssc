package com.stylefeng.guns.modular.tssc.controller;

import com.stylefeng.guns.common.constant.tips.Tip;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.tssc.entity.Recruit;
import com.stylefeng.guns.modular.tssc.service.IRecruitService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.tools.Tool;
import javax.validation.Valid;
import java.util.List;

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
    @Resource
    private IRecruitService iRecruitService;

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
    public String recruitUpdate(@PathVariable String recruitId, Model model) {
        Recruit recruit = new Recruit();
        recruit.setId(recruitId);
        model.addAttribute("recruit",iRecruitService.get(recruit));
        return PREFIX + "recruit_edit.html";
    }

    /**
     * 获取招聘列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public List<Recruit> list(String condition) {
        Recruit recruit = new Recruit();
        recruit.setName(condition);
        List<Recruit> list = iRecruitService.findList(recruit);
        return list;
    }

    /**
     * 新增招聘
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Tip add(@Valid Recruit recruit, BindingResult result) {
        String body = recruit.getContent();
        body = ToolUtil.getEncodeHtml(body);
        recruit.setContent(body);
        iRecruitService.insert(recruit);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除招聘
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Tip delete( String recruitId, Model model) {
        if(!ToolUtil.isEmpty(recruitId)){
            Recruit recruit = new Recruit();
            recruit.setId(recruitId);
            iRecruitService.delete(recruit);
        }
        return SUCCESS_TIP;
    }


    /**
     * 修改招聘
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Tip update(@Valid Recruit recruit, BindingResult result) {
        String body = recruit.getContent();
        body = ToolUtil.getEncodeHtml(body);
        recruit.setContent(body);
        iRecruitService.update(recruit);
        return super.SUCCESS_TIP;
    }

    /**
     * 招聘详情
     */
    @RequestMapping(value = "/detail/{recruitId}")
    public Object detail(@PathVariable String recruitId, Model model) {
        Recruit recruit = new Recruit();
        recruit.setId(recruitId);
        model.addAttribute("recruit",iRecruitService.get(recruit));
        return PREFIX + "recruit_edit.html";
    }
}
