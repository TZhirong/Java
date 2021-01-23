package com.layui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.layui.entity.Product;
import com.layui.vo.ProductBarVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper extends BaseMapper<Product> {
    @Select("select p.name,sum(quantity) count from order_detail od,product p where od.product_id = p.id group by product_id")
    public List<ProductBarVO> findAllProductBarVO();
}
