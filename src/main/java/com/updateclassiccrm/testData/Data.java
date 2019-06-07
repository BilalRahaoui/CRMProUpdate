package com.updateclassiccrm.testData;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Data {
    //Read data for test from src\main\resources\Data\fileName
    public static Object[][] getDataFromExcel(String fileName,String sheetName) throws Exception {
        //File path
        String invalidLoginDataPath = "src/main/resources/Data/" + fileName + ".xlsx";
        File file = new File(invalidLoginDataPath);
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);
        int rows = sheet.getLastRowNum() + 1;
        int columns = sheet.getRow(0).getLastCellNum();
        Object[][] data;
        data = new Object[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                data[i][j] = sheet.getRow(i).getCell(j).toString();
            }
        }
        return data;
    }
}
