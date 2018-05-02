package com.stylefeng.guns.modular.tssc.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 团队阵容
 */
public class Personnel  extends Model<Personnel> {
    private static final long serialVersionUID = 1L;

    /**
     * 与数据库属性一一对应
     */
    /**
     * 唯一主键
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 对应数据库 eng_name，英文名称
     */
    private String engName;
    /**
     * 职位
     */
    private String position;
    /**
     * 介绍
     */
    private String introduce;
    /**
     * 图片
     */
    private String image;

    @TableField(exist = false)
    private List<String> positionList = new ArrayList<>();

    public Personnel() {
    }

    public List<String> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<String> positionList) {
        this.positionList = positionList;
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

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
