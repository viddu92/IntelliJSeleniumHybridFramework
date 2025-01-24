package utils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//Utilities

public class Utilities {

    static  XSSFWorkbook workbook;
    public static Object[][] readTestData(String sheetName){
        try {
            FileInputStream fisExcel = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata.xlsx"));
            workbook = new XSSFWorkbook(fisExcel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       XSSFSheet sheet = workbook.getSheet(sheetName);
        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][cols];

        for(int i=0 ; i<rows ; i++){
            XSSFRow row = sheet.getRow(i+1);
            for(int j=0; j<cols ; j++){
                XSSFCell cell = row.getCell(j);
                CellType cellType = cell.getCellType();

                switch (cellType){
                    case STRING :
                        data[i][j] = cell.getStringCellValue();
                    break;

                    case NUMERIC:
                        data[i][j] = Integer.toString((int)cell.getNumericCellValue());
                    break;

                    case BOOLEAN:
                        data[i][j] = cell.getBooleanCellValue();
                    break;
                }
            }
        }

        return data;
    }

    public  static String takeScreenshotOnFailure(WebDriver driver,String testName){

        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File srcScreenshot = screenshot.getScreenshotAs(OutputType.FILE);
        String destScreenshot = System.getProperty("user.dir")+"\\Screenshots\\"+testName+"_"+System.currentTimeMillis()+".jpg";
        try {
            FileHandler.copy(srcScreenshot,new File(destScreenshot));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return destScreenshot;
    }
}
