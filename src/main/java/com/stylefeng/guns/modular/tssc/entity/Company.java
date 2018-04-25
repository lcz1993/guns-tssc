package com.stylefeng.guns.modular.tssc.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.stylefeng.guns.common.persistence.model.Menu;

import java.io.Serializable;

public class Company extends Model<Company> {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String introduction;
    private String personnel;
    private String brand;
    private String brandContent;
    private String field;
    private String history;
    private String historyContent;
    private String socialResponsibility;
    private String socialResponsibilityContent;
    private String growup;
    private String growupContent;
    private String welfare;
    private String welfareContent;

    public Company() {
    }

    public String getGrowup() {
        return growup;
    }

    public void setGrowup(String growup) {
        this.growup = growup;
    }

    public String getGrowupContent() {
        return growupContent;
    }

    public void setGrowupContent(String growupContent) {
        this.growupContent = growupContent;
    }

    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }

    public String getWelfareContent() {
        return welfareContent;
    }

    public void setWelfareContent(String welfareContent) {
        this.welfareContent = welfareContent;
    }

    public String getHistoryContent() {
        return historyContent;
    }

    public void setHistoryContent(String historyContent) {
        this.historyContent = historyContent;
    }

    public String getBrandContent() {
        return brandContent;
    }

    public void setBrandContent(String brandContent) {
        this.brandContent = brandContent;
    }

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getSocialResponsibility() {
        return socialResponsibility;
    }

    public void setSocialResponsibility(String socialResponsibility) {
        this.socialResponsibility = socialResponsibility;
    }

    public String getSocialResponsibilityContent() {
        return socialResponsibilityContent;
    }

    public void setSocialResponsibilityContent(String socialResponsibilityContent) {
        this.socialResponsibilityContent = socialResponsibilityContent;
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
