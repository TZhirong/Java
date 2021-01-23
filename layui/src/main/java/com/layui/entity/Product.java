package com.layui.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 35293
 * @date 2021/1/5 12:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private Integer stock;
    private Integer categoryleveloneId;
    private Integer categoryleveltwoId;
    private Integer categorylevelthreeId;
    private String fileName;

}
