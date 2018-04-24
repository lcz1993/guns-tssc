package com.stylefeng.guns.modular.tssc.controller;

import com.stylefeng.guns.common.constant.tips.Tip;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.modular.tssc.entity.Bespeak;
import com.stylefeng.guns.modular.tssc.entity.Genre;
import com.stylefeng.guns.modular.tssc.service.IBespeakService;
import com.stylefeng.guns.modular.tssc.service.IGenreService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

/**
 * 服务预约控制器
 *
 * @author fengshuonan
 * @Date 2018-04-24 10:59:21
 */
@Controller
@RequestMapping("/bespeak")
public class BespeakController extends BaseController {

    private String PREFIX = "/tssc/bespeak/";
    @Resource
    private IBespeakService iBespeakService;
    @Resource
    private IGenreService iGenreService;
    /**
     * 跳转到服务预约首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bespeak.html";
    }

    /**
     * 跳转到添加服务预约
     */
    @RequestMapping("/bespeak_add")
    public String bespeakAdd() {
        return PREFIX + "bespeak_add.html";
    }

    /**
     * 跳转到修改服务预约
     */
    @RequestMapping("/bespeak_update/{bespeakId}")
    public String bespeakUpdate(@PathVariable String bespeakId, Model model) {
        List<Genre> genreList = iGenreService.findList(new Genre());
        model.addAttribute("genres",genreList);
        Bespeak bespeak = new Bespeak();
        bespeak.setId(bespeakId);
        bespeak = iBespeakService.get(bespeak);
        model.addAttribute("bespeak",bespeak);
        return PREFIX + "bespeak_edit.html";
    }

    /**
     * 获取服务预约列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Bespeak bespeak = new Bespeak();
        List<Bespeak> list = iBespeakService.findList(bespeak);
        for (Bespeak b : list) {
            if("0".equals(b.getIsaccept())){
                b.setIsaccept("已受理");
            }else{
                b.setIsaccept("待受理");
            }
            String[] ids = b.getService().split(",");
            String service = "";
            Genre genre = new Genre();
            for (String id : ids){
                genre = new Genre();
                genre.setId(id);
                service += iGenreService.get(genre).getName()+",";
            }
            b.setService(service);
        }
        return list;
    }

    /**
     * 新增服务预约
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add() {
        return super.SUCCESS_TIP;
    }

    /**
     * 删除服务预约
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String bespeakId) {
        Bespeak bespeak = new Bespeak();
        bespeak.setId(bespeakId);
        //受理后受理情况由1改为0
        bespeak.setIsaccept("0");
        int i = iBespeakService.update(bespeak);
        return SUCCESS_TIP;
    }


    /**
     * 修改服务预约
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Tip update(Bespeak bespeak, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        int i = iBespeakService.update(bespeak);
        return super.SUCCESS_TIP;
    }

    /**
     * 服务预约详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Object detail() {
        return null;
    }
}

