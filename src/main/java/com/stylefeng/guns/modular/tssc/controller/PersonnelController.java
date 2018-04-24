package com.stylefeng.guns.modular.tssc.controller;

import com.stylefeng.guns.common.constant.tips.Tip;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.util.FileUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.util.JsonResult;
import com.stylefeng.guns.modular.tssc.entity.Personnel;
import com.stylefeng.guns.modular.tssc.service.IPersonnelService;
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
    private GunsProperties gunsProperties;
    /**
     * 跳转到人员首页
     */
    @RequestMapping("")
    public String index() {
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
    public String personnelUpdate(@PathVariable Integer personnelId, Model model) {
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
        personnel.setPosition("职员");
        iPersonnelService.insert(personnel);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除人员
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete() {
        return SUCCESS_TIP;
    }


    /**
     * 修改人员
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public JsonResult update(@Param("param") String param) {
        String[] strings = param.split("&|=");
        Personnel personnel = new Personnel();
        for (int i = 0; i<strings.length;i+=2) {
            String str = strings[i];
            if (str.equals("id")){
                personnel.setId(strings[i+1]);
            }
            if(str.equals("name")){
                personnel.setName(strings[i+1]);
            }
            if(str.equals("engName")){
                personnel.setEngName(strings[i+1]);
            }
            if (str.equals("position")){
                personnel.setPosition(strings[i+1]);
            }
            if (str.equals("introduce")){
                personnel.setIntroduce(strings[i+1]);
            }
            if (str.equals("image")){
                personnel.setImage(strings[i+1]);
            }
        }
        iPersonnelService.update(personnel);
        return new JsonResult("ok");
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
        String fileSavePath = gunsProperties.getFileUploadPath();
        String location = "personnel\\\\";
        try {
            picture.transferTo(new File(fileSavePath +location+ pictureName));
        } catch (Exception e) {
            File file = new File(fileSavePath +location);
            file.mkdirs();
            try {
                picture.transferTo(new File(fileSavePath +location+ pictureName));
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
    @RequestMapping("/{pictureId}")
    public void renderPicture(@PathVariable("pictureId") String pictureId, HttpServletResponse response) {
        String path = gunsProperties.getFileUploadPath() +"personnel\\\\"+ pictureId + ".jpg";
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
