package com.layui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.layui.entity.Product;
import com.layui.entity.ProductCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {
}
