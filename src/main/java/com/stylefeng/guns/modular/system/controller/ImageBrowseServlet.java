package com.stylefeng.guns.modular.system.controller;

import com.ckfinder.connector.ConnectorServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/ckfinder/core/connector/java/connector.java",initParams = {
        @WebInitParam(name = "XMLConfig", value = "classpath:ckfinder.xml"),
        @WebInitParam(name = "debug", value = "false"),
        @WebInitParam(name = "configuration", value = "com.stylefeng.guns.modular.system.util.CkfinderConfiguration")
})
public class ImageBrowseServlet extends ConnectorServlet {

}
