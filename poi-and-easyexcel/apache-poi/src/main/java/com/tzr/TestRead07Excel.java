package com.tzr;

import com.tzr.util.Read07Excel;
import org.junit.Test;

import java.io.FileInputStream;

/**
 * @PACKAGE_NAME: com.tzr
 * @NAME: TestRead07Excel
 * @USER: 35293
 * @date: 2021年01月25日 22:20
 * @PROJECT_NAME: poi-and-easyexcel
 **/
public class TestRead07Excel {
    private String PATH = "E:\\ideaProject\\poi-and-easyexcel\\apache-poi\\";
    @Test
    public void Test01() throws Exception{
        FileInputStream fileInputStream = new FileInputStream(PATH + "花名册07.xlsx");

        Read07Excel read07Excel = new Read07Excel();
        read07Excel.read07(fileInputStream);
    }
}
