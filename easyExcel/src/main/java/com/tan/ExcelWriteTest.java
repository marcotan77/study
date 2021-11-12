package com.tan;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Tan.
 * @create: 2020-10-14 13:39
 **/
public class ExcelWriteTest {

    /**
     * 写excel
     * @throws Exception
     * 03   new HSSFWorkbook();  .xls
     * 07   new XSSFWorkbook();  文件名后缀 .xlsx
     */
    @Test
    public void testWrite() throws Exception {
        String path = "F:\\tan\\java\\";
        // 创建一个工作薄
        Workbook workbook = new XSSFWorkbook();
        // 创建一个工作表
        Sheet sheet = workbook.createSheet("测试");
        // 创建一个行
        Row row = sheet.createRow(0);
        // 创建一个单元格
        Cell cell = row.createCell(0);
        cell.setCellValue("哈哈哈");
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String name = time+".xls";
        FileOutputStream fileOutputStream = new FileOutputStream(path +"demo"+ System.currentTimeMillis()+".xlsx");
        workbook.write(fileOutputStream);
        System.out.println("执行完成");
        fileOutputStream.close();
    }


    @Test
    public void testRead() throws Exception{
        String path ="F:\\tan\\java\\";

        // 获取文件流
        FileInputStream fileInputStream = new FileInputStream(path + "12.xlsx");
        // 创建一个工作薄
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        // 创建一个工作表
        Sheet sheet = workbook.getSheetAt(0);
        Row rowTitle = sheet.getRow(0);
        if (rowTitle != null){
            int cells = rowTitle.getPhysicalNumberOfCells();
            for (int i = 0; i < cells; i++) {
                Cell cell = rowTitle.getCell(i);
                if (cell != null){
                    int cellType = cell.getCellType();
                    String cellValue = cell.getStringCellValue();
                    System.out.print(cellValue + " | ");
                }
            }
            System.out.println();
        }
        List<AreaTestVO> areaList = new ArrayList<AreaTestVO>();

        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rows; i++) {
            Row row = sheet.getRow(i);
            if (row != null){
                AreaTestVO areaTestVO = new AreaTestVO();
                // 大区
                Cell cell = row.getCell(0);
                String areaName = cell.getStringCellValue();
                areaTestVO.setAreaName(areaName);

                // 数量
                Cell cell1 = row.getCell(1);
                double number = cell1.getNumericCellValue();
                areaTestVO.setNumber((int) number);

                //
                Cell cell2 = row.getCell(2);
                String manageName = cell2.getStringCellValue();
                areaTestVO.setManageName(manageName);

                areaList.add(areaTestVO);
            }
        }

        for (AreaTestVO a :areaList ) {
            System.out.println(a);
        }

        fileInputStream.close();
    }



}
