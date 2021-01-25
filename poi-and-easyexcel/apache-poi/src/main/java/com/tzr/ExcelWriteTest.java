package com.tzr;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @PACKAGE_NAME: com.tzr
 * @NAME: ExcelWriteTest
 * @USER: 35293
 * @date: 2021年01月24日 21:01
 * @PROJECT_NAME: poi-and-easyexcel
 **/
public class ExcelWriteTest {

    private String PATH = "E:\\ideaProject\\poi-and-easyexcel\\apache-poi\\";

    @Test
    public void testWrite03() throws IOException {
        //1、创建一个工作部 03版本
        Workbook workbook = new HSSFWorkbook();
        //2、创建一个工作表
        Sheet sheet = workbook.createSheet("观众统计表");
        //3、创建一行
        Row row1 = sheet.createRow(0);
        //4、创建一个单元格写入数据（1，1）位置
        Cell cell11 = row1.createCell(0);
        cell11.setCellValue("今日新增观众");
        //（1，2）
        Cell cell12 = row1.createCell(1);
        cell12.setCellValue("555");

        //第二行(2,1)
        Row row2 = sheet.createRow(1);
        Cell cell21 = row2.createCell(0);
        cell21.setCellValue("统计时间");
        //(2,2)
        Cell cell22 = row2.createCell(1);
        String time = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        cell22.setCellValue(time);

        //生成一张表 03版本
        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "观众统计表03.xls");
        //输出
        workbook.write(fileOutputStream);

        //关闭流
        fileOutputStream.close();

        System.out.println("观众统计表03生成完毕");
    }

    @Test
    public void testWrite07() throws IOException {
        //1、创建一个工作部 07版本
        Workbook workbook = new XSSFWorkbook();
        //2、创建一个工作表
        Sheet sheet = workbook.createSheet("观众统计表");
        //3、创建一行
        Row row1 = sheet.createRow(0);
        //4、创建一个单元格写入数据（1，1）位置
        Cell cell11 = row1.createCell(0);
        cell11.setCellValue("今日新增观众");
        //（1，2）
        Cell cell12 = row1.createCell(1);
        cell12.setCellValue("888");

        //第二行(2,1)
        Row row2 = sheet.createRow(1);
        Cell cell21 = row2.createCell(0);
        cell21.setCellValue("统计时间");
        //(2,2)
        Cell cell22 = row2.createCell(1);
        String time = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        cell22.setCellValue(time);

        //生成一张表 07版本
        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "观众统计表07.xls");
        //输出
        workbook.write(fileOutputStream);

        //关闭流
        fileOutputStream.close();

        System.out.println("观众统计表07生成完毕");
    }

    @Test
    public void testWrite03BigData() throws Exception{
        //时间
        long begin = System.currentTimeMillis();

        //创建一个工作簿
        Workbook workbook = new HSSFWorkbook();
        //创建表
        Sheet sheet = workbook.createSheet("测试03版存入大数据");
        //写入数据
        for (int rowNum = 0; rowNum < 65536; rowNum++) {
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 10; cellNum++) {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        System.out.println("over");
        //生成一张表 03版本
        FileOutputStream OutputStream = new FileOutputStream(PATH + "GigData03.xls");
        //输出
        workbook.write(OutputStream);

        //关闭流
        OutputStream.close();
        long end = System.currentTimeMillis();
        System.out.println((double)(end-begin)/1000);
    }

    @Test
    public void testWrite07BigData() throws Exception{
        //时间
        long begin = System.currentTimeMillis();

        //创建一个工作簿
        Workbook workbook = new XSSFWorkbook();
        //创建表
        Sheet sheet = workbook.createSheet("测试07版存入大数据");
        //写入数据
        for (int rowNum = 0; rowNum < 65536; rowNum++) {
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 10; cellNum++) {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        System.out.println("over");
        //生成一张表 07版本
        FileOutputStream OutputStream = new FileOutputStream(PATH + "GigData07.xlsx");
        //输出
        workbook.write(OutputStream);

        //关闭流
        OutputStream.close();
        long end = System.currentTimeMillis();
        System.out.println((double)(end-begin)/1000);
    }

    //优化07版写入大数据，提速(SXSSF)
    @Test
    public void testWrite07BigDataSj() throws Exception{
        //时间
        long begin = System.currentTimeMillis();

        //创建一个工作簿
        Workbook workbook = new SXSSFWorkbook();
        //创建表
        Sheet sheet = workbook.createSheet("测试优化07版存入大数据");
        //写入数据
        for (int rowNum = 0; rowNum < 65536; rowNum++) {
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 10; cellNum++) {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        System.out.println("over");
        //生成一张表 07版本优化提速
        FileOutputStream OutputStream = new FileOutputStream(PATH + "GigData07Ts.xlsx");
        //输出
        workbook.write(OutputStream);
        //关闭流
        OutputStream.close();
        //清除临时文件
        ((SXSSFWorkbook) workbook).dispose();
        long end = System.currentTimeMillis();
        System.out.println((double)(end-begin)/1000);
    }


}
