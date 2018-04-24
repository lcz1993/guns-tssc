package com.stylefeng.guns.modular.system.util;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/3/20.
 */
public class JsonResult implements Serializable {

    private static final long serialVersionUID = 1964123783885667475L;
    private Object data;//返回数据
    private String message;//提示信息
    private Integer state;//状态0，1
    private final static Integer SUCCESS = 0;
    private final static Integer ERROR = 1;

    public JsonResult(){}
    public JsonResult(Integer state, String message, Object data){
        this.state=state;
        this.message=message;
        this.data=data;
    }
    public JsonResult(Throwable e){
        state=ERROR;
        data=null;
        message=e.getMessage();
    }
    public JsonResult(Integer state, Throwable e){
        this.state=state;
        data=null;
        message=e.getMessage();
    }
    public JsonResult(Object data){
        state=SUCCESS;
        this.data=data;
        message="";
    }
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
