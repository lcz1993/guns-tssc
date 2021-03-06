package com.stylefeng.guns.modular.tssc.entity;

import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 *招聘实体类
 * @author lcz
 */
public class Recruit extends Model<Recruit>{

    private static final long serialVersionUID = 1L;
    /**
     * 唯一主键
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 内容
     */
    private String content;
    /**
     *职位类别
     */
    private String category;
    /**
     *待遇
     */
    private String treatment;
    /**
     *性质
     */
    private String nature;
    /**
     *地点
     */
    private String place;
    /**
     *人数
     */
    private String number;
    /**
     *上班时段
     */
    private String interval;
    /**
     *休假制度
     */
    private String vacation;
    /**
     *上班时间
     */
    private String working;
    /**
     *条件需求
     */
    private String condition;

    public Recruit() {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getVacation() {
        return vacation;
    }

    public void setVacation(String vacation) {
        this.vacation = vacation;
    }

    public String getWorking() {
        return working;
    }

    public void setWorking(String working) {
        this.working = working;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
