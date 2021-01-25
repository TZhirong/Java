package com.tzr;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.FileInputStream;

/**
 * @PACKAGE_NAME: com.tzr
 * @NAME: ExcelReadTest
 * @USER: 35293
 * @date: 2021年01月24日 23:11
 * @PROJECT_NAME: poi-and-easyexcel
 **/
public class ExcelReadTest {
    private String PATH = "E:\\ideaProject\\poi-and-easyexcel\\apache-poi\\";

    @Test
    public void testRead03() throws Exception {
        //获取文件流
        FileInputStream inputStream = new FileInputStream(PATH + "人员花名册统计表03.xls");

        //创建一个工作簿,使用excel能操作的，这里都能做
        Workbook workbook = new HSSFWorkbook(inputStream);
        //获取第一个表
        Sheet sheet = workbook.getSheetAt(0);
        //获取第五行
        Row row1 = sheet.getRow(4);
        //获取第二列
        Cell cell1 = row1.getCell(1);

        //打印（5，2）数据
        System.out.println(cell1.getStringCellValue());
        //关闭流
        inputStream.close();
    }

    @Test
    public void testRead07() throws Exception {
        //获取文件流
        FileInputStream inputStream = new FileInputStream(PATH + "人员花名册统计表07.xlsx");

        //创建一个工作簿使用excel能操作的，这里都能做
        Workbook workbook = new XSSFWorkbook(inputStream);
        //获取第一个表
        Sheet sheet = workbook.getSheetAt(0);
        //获取第五行
        Row row1 = sheet.getRow(4);
        //获取第二列
        Cell cell1 = row1.getCell(1);

        //打印（5，2）数据
        System.out.println(cell1.getStringCellValue());
        //关闭流
        inputStream.close();
    }

    @Test
    public void testCellType() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(PATH + "花名册07.xlsx");
        Workbook workbook=new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Row rowTitle = sheet.getRow(0);
        if(rowTitle!=null){
            int cellCount=rowTitle.getPhysicalNumberOfCells();    //拿到第row行的那一行的总个数
            for (int i = 0; i <cellCount ; i++) {  //循环个数取出
                Cell cell = rowTitle.getCell(i);
                if(cell!=null){          //如果不等于空取出值
                    int cellType = cell.getCellType();   //这里是知道我们标题是String，考虑不确定的时候怎么取
                    String cellValue = cell.getStringCellValue();
                    System.out.print(cellValue+"|");
                }
            }
            System.out.println();
        }
        //获取表中内容
        int rowCount = sheet.getPhysicalNumberOfRows();
        for (int rowNum = 1; rowNum < rowCount; rowNum++) {
            Row rowData = sheet.getRow(rowNum);
            if (rowData != null) {
                //读取列
                int cellCount = rowTitle.getPhysicalNumberOfCells();
                for (int cellNum = 0; cellNum < cellCount; cellNum++) {
                    System.out.print("[" + (rowNum + 1) + "-" + (cellNum + 1) + "]");
                    Cell cell = rowData.getCell(cellNum);
                    //匹配数据类型
                    if (cell != null) {
                        int cellType = cell.getCellType();
                        switch (cellType) {
                            case XSSFCell.CELL_TYPE_STRING:
                                System.out.println("【字符串】" + cell.getStringCellValue());
                                break;
                            case XSSFCell.CELL_TYPE_BOOLEAN:
                                System.out.println("【布尔】" + cell.getBooleanCellValue());
                                break;
                            case XSSFCell.CELL_TYPE_NUMERIC:
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    System.out.println("【日期格式】" + new DateTime(cell.getDateCellValue()).toString("yyyy-MM-dd HH:mm:ss"));
                                    break;
                                } else {
                                    //不是日期格式，防止数据过长
                                    System.out.print("【数字】");
                                    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                                    System.out.println("转换为字符串输出整形：" + cell.toString());
                                }
                                break;
                            case XSSFCell.CELL_TYPE_BLANK:
                                System.out.print("【空】");
                                break;
                            case XSSFCell.CELL_TYPE_ERROR:
                                System.out.print("【数据类型错误");
                                break;
                            case Cell.CELL_TYPE_FORMULA:
                                String formula = cell.getCellFormula();
                                System.out.println("公式:" + formula);

                                //CellValue evaluate = formulaEvaluator.evaluate(cell);
                                //String cellValue = evaluate.formatAsString();
                                //System.out.println(cellValue);
                                break;
                            default:
                                System.out.println("该位置没有数据");
                                break;
                        }
                    }
                }
            }
        }
        fileInputStream.close();
    }
}
