package com.stylefeng.guns.modular.tssc.service;

import com.stylefeng.guns.modular.tssc.entity.Product;

import java.util.List;

/**
 * 产品Service
 *
 * @author fengshuonan
 * @Date 2018-04-19 16:34:26
 */
public interface IProductService {
    List<Product> findList(Product product);
    Product get(Product product);
}
