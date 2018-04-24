package com.stylefeng.guns.modular.tssc.controller;

import com.stylefeng.guns.common.constant.tips.Tip;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.util.JsonResult;
import com.stylefeng.guns.modular.tssc.entity.Field;
import com.stylefeng.guns.modular.tssc.service.IFieldService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 服务领域控制器
 *
 * @author fengshuonan
 * @Date 2018-04-17 16:22:40
 */
@Controller
@RequestMapping("/field")
public class FieldController extends BaseController {

    private String PREFIX = "/tssc/field/";

    @Resource
    private IFieldService iFieldService;
    /**
     * 跳转到服务领域首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "field.html";
    }

    /**
     * 跳转到添加服务领域
     */
    @RequestMapping("/field_add")
    public String fieldAdd() {
        return PREFIX + "field_add.html";
    }

    /**
     * 跳转到修改服务领域
     */
    @RequestMapping("/field_update/{fieldId}")
    public String fieldUpdate(@PathVariable String fieldId, Model model) {
        if (ToolUtil.isEmpty(fieldId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        Field field = new Field();
        field.setId(fieldId);
        field = iFieldService.get(field);
        model.addAttribute("fieldId", fieldId);
        model.addAttribute("field", field);
        return PREFIX + "field_edit.html";
    }

    /**
     * 获取服务领域列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public JsonResult list(String condition) {
        List<Field> list = iFieldService.findList(new Field());
        return new JsonResult(list);
    }

    /**
     * 新增服务领域
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Tip add(@Valid Field field, BindingResult result) {

        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        if(ToolUtil.isEmpty(field.getId())){
            field.setId(ToolUtil.getUid());
        }
        // 判断名称是否重复
//        Field s = new Field();
//        s.setName(field.getName());
//        List<Field> list = iFieldService.findList(s);
//        if (list.size() != 0) {
//            throw new BussinessException(BizExceptionEnum.USER_ALREADY_REG);
//        }
        iFieldService.insert(field);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除服务领域
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonResult delete(String id) {
        Field field = new Field();
        field.setId(id);
        iFieldService.delete(field);
        return new JsonResult("ok");
    }


    /**
     * 修改服务领域
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Tip update(@Valid Field field, BindingResult result) {

        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        if(ToolUtil.isEmpty(field.getId())){
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        // 判断名称是否重复
//        Field s = new Field();
//        s.setName(field.getName());
//        List<Field> list = iFieldService.findList(s);
//        for (Field f : list){
//            String id = f.getId();
//            if(!field.getId().equals(id)){
//                throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
//            }
//        }
        iFieldService.update(field);
        return super.SUCCESS_TIP;
    }

    /**
     * 服务领域详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Object detail() {
        return null;
    }

    /**
     * 验证名称唯一性
     * @param id
     * @param name
     * @return
     */
    @RequestMapping(value = "/check")
    @ResponseBody
    public JsonResult check(String id ,String name) {
        Field field = new Field();
        field.setName(name);
        List<Field> list = iFieldService.findList(field);
        for (Field f : list){
            if(!f.getId().equals(id)){
                return new JsonResult("no");
            }
        }
        return new JsonResult("ok");
    }
}
