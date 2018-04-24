package com.stylefeng.guns.modular.tssc.controller;

import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.util.JsonResult;
import com.stylefeng.guns.modular.tssc.entity.Company;
import com.stylefeng.guns.modular.tssc.service.ICompanyService;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;

import static com.stylefeng.guns.core.util.ToolUtil.isEmpty;

/**
 * 公司控制器
 *
 * @author fengshuonan
 * @Date 2018-04-12 15:17:17
 */
@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController {

    private String PREFIX = "/tssc/company/";

    @Resource
    private ICompanyService iCompanyService;

    /**
     * 跳转到公司首页
     */
    @ResponseBody
    @RequestMapping("/get")
    public JsonResult index() {
        Company company = iCompanyService.findCompany();
        return new JsonResult(company);
    }

    /**
     * 跳转到添加公司
     */
    @RequestMapping("/company_add")
    public String companyAdd() {
        return PREFIX + "company_add.html";
    }

    /**
     * 跳转到修改公司
     */
    @RequestMapping("/company_update/{companyId}")
    public String companyUpdate(@PathVariable Integer companyId, Model model) {
        return PREFIX + "company_edit.html";
    }

    /**
     * 获取公司列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return null;
    }

    /**
     * 新增公司
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add() {
        return super.SUCCESS_TIP;
    }

    /**
     * 删除公司
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete() {
        return SUCCESS_TIP;
    }


    /**
     * 修改公司
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public JsonResult update(String param,String brandContent,String historyContent,String socialResponsibilityContent) {
        String[] strings = param.split("&|=");
        Company company = new Company();
        if (!ToolUtil.isEmpty(brandContent)){
            brandContent = ToolUtil.getEncodeHtml(brandContent);
            company.setBrandContent(brandContent);
        }
        if(!ToolUtil.isEmpty(historyContent)){
            historyContent = ToolUtil.getEncodeHtml(historyContent);
            company.setHistoryContent(historyContent);
        }
        if(!ToolUtil.isEmpty(socialResponsibilityContent)){
            socialResponsibilityContent = ToolUtil.getEncodeHtml(socialResponsibilityContent);
            company.setSocialResponsibilityContent(socialResponsibilityContent);
        }

        for (int i = 0; i<strings.length;i+=2) {
            String str = strings[i];
            if ("id".equals(str)){
                company.setId(strings[i+1]);
            }
            if("name".equals(str)){
                company.setName(strings[i+1]);
            }
            if("introduction".equals(str)){
                company.setIntroduction(strings[i+1]);
            }
            if ("personnel".equals(str)){
                company.setPersonnel(strings[i+1]);
            }
            if ("brand".equals(str)){
                company.setBrand(strings[i+1]);
            }
            if ("history".equals(str)){
                company.setHistory(strings[i+1]);
            }
            if ("field".equals(str)){
                company.setField(strings[i+1]);
            }
            if ("socialResponsibility".equals(str)){
                company.setSocialResponsibility(strings[i+1]);
            }
        }
        iCompanyService.updateById(company);
        return new JsonResult(company);
    }

    /**
     * 公司详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Object detail() {
        return null;
    }

    @RequestMapping(value = "/brand")
    public String brand(Model model){
        Company company = iCompanyService.findCompany();
        model.addAttribute("company",company);
        return PREFIX + "brand.html";
    }
    @RequestMapping(value = "/brand_update")
    public String brandUpdate(Model model){
        Company company = iCompanyService.findCompany();
        model.addAttribute("company",company);
        return PREFIX + "brand_edit.html";
    }
    @RequestMapping(value = "/history")
    public String history(Model model){
        Company company = iCompanyService.findCompany();
        model.addAttribute("company",company);
        return PREFIX + "history.html";
    }
    @RequestMapping(value = "/history_update")
    public String historyUpdate(Model model){
        Company company = iCompanyService.findCompany();
        model.addAttribute("company",company);
        return PREFIX + "history_edit.html";
    }
    @RequestMapping(value = "/social")
    public String social(Model model){
        Company company = iCompanyService.findCompany();
        model.addAttribute("company",company);
        return PREFIX + "social.html";
    }
    @RequestMapping(value = "/social_update")
    public String socialUpdate(Model model){
        Company company = iCompanyService.findCompany();
        model.addAttribute("company",company);
        return PREFIX + "social_edit.html";
    }
}
