package com.tzr.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

import java.util.Date;

/**
 * @PACKAGE_NAME: com.tzr.entity
 * @NAME: Data
 * @USER: 35293
 * @date: 2021年01月25日 22:30
 * @PROJECT_NAME: poi-and-easyexcel
 **/
@lombok.Data
public class Data {
    @ExcelProperty("字符串标题")
    private String string;
    @ExcelProperty("日期标题")
    private Date date;
    @ExcelProperty("数字标题")
    private Double doubleData;

    //忽略字段
    @ExcelIgnore
    private String ignore;
}
