package com.stylefeng.guns.modular.tssc.entity;

import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 * 业务类别表
 * @author
 */
public class Genre extends Model<Genre>{

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
     * 备注
     */
    private String remark;

    public Genre() {
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
