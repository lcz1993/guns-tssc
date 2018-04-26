package com.stylefeng.guns.modular.tssc.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 服务预约
 * @author lcz
 */
@TableName(value = "service_order")
public class Bespeak extends Model<Bespeak>{
    private static final long serialVersionUID = 1L;
    /**
     * 唯一主键
     */
    private String id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 职称
     */
    private String title;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * 电话
     */
    private String tel;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 公司网址
     */
    private String companyWeb;
    /**
     *预算
     */
    private String budget;
    /**
     *品牌
     */
    private String brand;
    /**
     *服务项目
     */
    private String service;
    /**
     *备注
     */
    private String remark;
    /**
     * 是否受理
     */
    private String isaccept;

    public Bespeak() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyWeb() {
        return companyWeb;
    }

    public void setCompanyWeb(String companyWeb) {
        this.companyWeb = companyWeb;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsaccept() {
        return isaccept;
    }

    public void setIsaccept(String isaccept) {
        this.isaccept = isaccept;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
