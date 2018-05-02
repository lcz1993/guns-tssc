package com.stylefeng.guns.modular.tssc.controller;

import com.stylefeng.guns.common.constant.tips.Tip;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.util.FileUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.util.JsonResult;
import com.stylefeng.guns.modular.tssc.entity.Company;
import com.stylefeng.guns.modular.tssc.entity.Personnel;
import com.stylefeng.guns.modular.tssc.service.ICompanyService;
import com.stylefeng.guns.modular.tssc.service.IPersonnelService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 人员控制器
 *
 * @author fengshuonan
 * @Date 2018-04-16 11:59:01
 */
@Controller
@RequestMapping("/personnel")
public class PersonnelController extends BaseController {

    private String PREFIX = "/tssc/personnel/";

    @Resource
    private IPersonnelService iPersonnelService;
    @Resource
    private ICompanyService iCompanyService;
    @Resource
    private GunsProperties gunsProperties;
    /**
     * 跳转到人员首页
     */
    @RequestMapping("")
    public String index(Model model) {
        //公司本身
        Company company = iCompanyService.findCompany();
        model.addAttribute("company",company);
        //人员
        Personnel personnel = new Personnel();
        List<Personnel> personnelList = iPersonnelService.findList(personnel);
        for (Personnel p:personnelList) {
            List<String> list = new ArrayList<String>();
            String[] strings = p.getPosition().split(",");
            for (String s:strings) {
                list.add(s);
            }
            p.setPositionList(list);
        }
        model.addAttribute("personnelList",personnelList);
        return PREFIX + "personnel.html";
    }

    /**
     * 跳转到添加人员
     */
    @RequestMapping("/personnel_add")
    public String personnelAdd() {
        return PREFIX + "personnel_add.html";
    }

    /**
     * 跳转到修改人员
     */
    @RequestMapping("/personnel_update/{personnelId}")
    public String personnelUpdate(@PathVariable String personnelId, Model model) {
        model.addAttribute("personnel",iPersonnelService.get(personnelId));
        return PREFIX + "personnel_edit.html";
    }

    /**
     * 获取人员列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public JsonResult list() {
        Personnel personnel = new Personnel();
        List<Personnel> list = iPersonnelService.findList(personnel);
        return new JsonResult(list);
    }

    /**
     * 新增人员
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Tip add(@Valid Personnel personnel, BindingResult result) {

        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        if(ToolUtil.isEmpty(personnel.getId())){
            personnel.setId(ToolUtil.getUid());
        }
        if(ToolUtil.isEmpty(personnel.getPosition())){
            personnel.setPosition("职员");
        }
        iPersonnelService.insert(personnel);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除人员
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    public JsonResult delete(String id) {
        iPersonnelService.delete(id);
        return new JsonResult("ok");
    }


    /**
     * 修改人员
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Tip update(Personnel personnel) {
        iPersonnelService.update(personnel);
        return super.SUCCESS_TIP;
    }

    /**
     * 人员详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Object detail() {
        return null;
    }

    /**
     * 上传图片(上传到项目的webapp/static/img)
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public String upload(@RequestPart("file") MultipartFile picture){
        String pictureName = UUID.randomUUID().toString() + ".jpg";
        String fileSavePath = gunsProperties.getFileUploadPath() + "personnel/";
        try {
            picture.transferTo(new File(fileSavePath + pictureName));
        } catch (Exception e) {
            File file = new File(fileSavePath );
            file.mkdirs();
            try {
                picture.transferTo(new File(fileSavePath + pictureName));
            } catch (IOException e1) {
                throw new BussinessException(BizExceptionEnum.UPLOAD_ERROR);
            }
        }
        return pictureName;
    }
    /**
     * 返回图片
     *
     * @author stylefeng
     * @Date 2017/5/24 23:00
     */
    @RequestMapping("/image/{pictureId}")
    public void renderPicture(@PathVariable("pictureId") String pictureId, HttpServletResponse response) {
        String path = gunsProperties.getFileUploadPath() +"personnel/"+ pictureId + ".jpg";
        try {
            byte[] bytes = FileUtil.toByteArray(path);
            response.getOutputStream().write(bytes);
        }catch (Exception e){
            //如果找不到图片就返回一个默认图片
            try {
                response.sendRedirect("/static/img/noLogo.png");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
