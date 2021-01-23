package com.layui.vo;

import lombok.Data;

/**
 * @author 35293
 * @date 2021/1/5 16:35
 */
@Data
public class ProductVO {
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private Integer stock;
    private String categorylevelone;
    private String categoryleveltwo;
    private String categorylevelthree;
    private String fileName;
}
