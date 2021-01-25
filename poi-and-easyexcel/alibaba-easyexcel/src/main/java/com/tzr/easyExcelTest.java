package com.tzr;

import com.alibaba.excel.EasyExcel;
import com.tzr.entity.Data;

/**
 * @PACKAGE_NAME: com.tzr
 * @NAME: easyExcelTest
 * @USER: 35293
 * @date: 2021年01月25日 23:00
 * @PROJECT_NAME: poi-and-easyexcel
 **/
public class easyExcelTest {
    static String path = "E:\\ideaProject\\poi-and-easyexcel\\alibaba-easyexcel\\";

    public static void main(String[] args) {

        easyWrite();
    }

    public static void easyWrite(){
        String fileName = path + "EasyWrite.xlsx";
        /**
         *    fileName: 写的文件路径（没有会自动创建）
         *    Data.class: 用哪个类写
         *    sheet(): 写到哪个表中
         *    doWrite()： 写的数据
         *
         *    会自动关闭流
         */
        EasyExcel.write(fileName, Data.class).sheet("工作表1").doWrite(Utils.data());
    }
}
