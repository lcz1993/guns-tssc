package com.stylefeng.guns.modular.tssc.service.impl;

import com.stylefeng.guns.modular.tssc.dao.ProductDao;
import com.stylefeng.guns.modular.tssc.entity.Product;
import org.springframework.stereotype.Service;
import com.stylefeng.guns.modular.tssc.service.IProductService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品Dao
 *
 * @author fengshuonan
 * @Date 2018-04-19 16:34:26
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Resource
    private ProductDao productDao;

    @Override
    public List<Product> findList(Product product) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("genreId",product.getGenreId());
        map.put("teamId",product.getTeamId());
        map.put("year",product.getYear());
        return productDao.selectByMap(map);
    }

    @Override
    public Product get(Product product) {
        return productDao.selectById(product.getId());
    }
}
