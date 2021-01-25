package com.tzr.util;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;

import java.io.FileInputStream;

/**
 * @PACKAGE_NAME: com.tzr.util
 * @NAME: Read07Excel
 * @USER: 35293
 * @date: 2021年01月25日 22:19
 * @PROJECT_NAME: poi-and-easyexcel
 **/
public class Read07Excel {
    public void read07(FileInputStream fileInputStream) throws Exception{
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
