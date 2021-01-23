package com.layui.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.layui.entity.Product;
import com.layui.entity.ProductCategory;
import com.layui.mapper.ProductCategoryMapper;
import com.layui.mapper.ProductMapper;
import com.layui.service.ProductService;
import com.layui.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 35293
 * @date 2021/1/5 16:39
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public List<PieVO> getPieVO() {
        List<ProductBarVO> list = productMapper.findAllProductBarVO();
        List<PieVO> pieVOList = list.stream()
                .map(e -> new PieVO(
                        e.getCount(),
                        e.getName()
                )).collect(Collectors.toList());
        return pieVOList;
    }

    @Override
    public BarVO getBarVO() {
        List<ProductBarVO> list = productMapper.findAllProductBarVO();
        List<String> names = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        for (ProductBarVO productBarVO : list) {
            names.add(productBarVO.getName());
            values.add(productBarVO.getCount());
        }
        BarVO barVO = new BarVO();
        barVO.setNames(names);
        barVO.setValues(values);
        return barVO;
    }

    @Override
    public DataVO<ProductVO> findData(Integer page,Integer limit) {
        DataVO dataVO  = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
        //分页
        IPage<Product> productIPage = new Page<>(page,limit);
        IPage<Product> result = productMapper.selectPage(productIPage,null);

        dataVO.setCount(result.getTotal());
        List<Product> productList = result.getRecords();
        List<ProductVO> productVOList = new ArrayList<>();
        for(Product product : productList){
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(product,productVO);

            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("id",product.getCategoryleveloneId());
            ProductCategory productCategory = productCategoryMapper.selectOne(queryWrapper);
            if(productCategory != null){
                productVO.setCategorylevelone(productCategory.getName());
            }

            queryWrapper = new QueryWrapper();
            queryWrapper.eq("id",product.getCategoryleveltwoId());
            productCategory = productCategoryMapper.selectOne(queryWrapper);
            if(productCategory!=null) {
                productVO.setCategoryleveltwo(productCategory.getName());
            }

            queryWrapper = new QueryWrapper();
            queryWrapper.eq("id",product.getCategorylevelthreeId());
            productCategory = productCategoryMapper.selectOne(queryWrapper);
            if(productCategory!=null) {
                productVO.setCategorylevelthree(productCategory.getName());
            }

            productVOList.add(productVO);
        }

        dataVO.setData(productVOList);
        return dataVO;
    }
}
