package com.stylefeng.guns.modular.tssc.entity;

import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 * 服务领域基类
 *@author lcz
 */
public class Field extends Model<Field>{

    private static final long serialVersionUID = 1L;
    /**
     * 唯一主键
     */
    private String id;
    /**
     *名称
     */
    private String name;
    /**
     * 简介
     */
    private String synopsis;
    /**
     * 介绍
     */
    private String introduce;

    public Field() {
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

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
