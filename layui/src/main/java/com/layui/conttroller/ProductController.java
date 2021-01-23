package com.layui.conttroller;

import com.layui.service.ProductService;
import com.layui.vo.BarVO;
import com.layui.vo.DataVO;
import com.layui.vo.PieVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 35293
 * @date 2021/1/5 21:29
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping("/list")
    @ResponseBody
    public DataVO list(Integer page, Integer limit){
        return productService.findData(page, limit);
    }

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url){
        return url;
    }

    @RequestMapping("/barVO")
    @ResponseBody
    public BarVO getBarVO(){
        return productService.getBarVO();
    }

    @RequestMapping("/pieVO")
    @ResponseBody
    public List<PieVO> getPieVO(){
        return productService.getPieVO();
    }

}
