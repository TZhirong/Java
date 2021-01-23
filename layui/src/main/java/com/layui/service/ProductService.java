package com.layui.service;

import com.layui.vo.BarVO;
import com.layui.vo.DataVO;
import com.layui.vo.PieVO;
import com.layui.vo.ProductVO;

import java.util.List;

/**
 * @author 35293
 * @date 2021/1/5 16:37
 */
public interface ProductService {
    public DataVO<ProductVO> findData(Integer page,Integer limit);
    public BarVO getBarVO();
    public List<PieVO> getPieVO();
}
