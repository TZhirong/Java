package com.layui;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.layui.entity.Product;
import com.layui.entity.ProductCategory;
import com.layui.mapper.ProductCategoryMapper;
import com.layui.mapper.ProductMapper;
import com.layui.service.ProductService;
import com.layui.vo.DataVO;
import com.layui.vo.ProductVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Wrapper;
import java.util.List;

@SpringBootTest
class LayuiApplicationTests {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private ProductService productService;

    @Test
    void productTest() {
        List<Product> productList = productMapper.selectList(null);
        for(Product product : productList){
            System.out.println(product.toString());
        }
    }

    @Test
    void ProductCategoryTest() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",655);
        System.out.println(productCategoryMapper.selectOne(queryWrapper));
    }

    @Test
    void ProductListTest() {
        DataVO<ProductVO> data = productService.findData(2, 5);
        System.out.println(data);
    }
}
