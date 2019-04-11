/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3complejidad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javafx.scene.control.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author RICARDO
 */
public class FileWizard {

    public FileWizard() {
    }
    
    public ArrayList<BigInteger> loadFromFile (String f_name) throws IOException
    {
        ArrayList<BigInteger> list = new ArrayList<>();
        BufferedReader buffer = new BufferedReader(new FileReader(f_name));
        String line;

        while((line=buffer.readLine())!=null)
            list.add(new BigInteger(line));

        buffer.close();
        return list;
    }

    public ArrayList<BigInteger> loadToFile (String f_name , ArrayList<BigInteger> list) throws IOException
    {
        PrintWriter printer = new PrintWriter(new FileWriter(f_name));

        list.forEach((num) -> {printer.println(num.toString());   });

        printer.close();
        return list;
    }
    
    public void writeToExcel(String path) throws IOException
    {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Java Books");
         
        Object[][] bookData = {
                {"Head First Java", "Kathy Serria", 79},
                {"Effective Java", "Joshua Bloch", 36},
                {"Clean Code", "Robert martin", 42},
                {"Thinking in Java", "Bruce Eckel", 35},
        };
 
        int rowCount = 0;
         
        for (Object[] aBook : bookData) {
            Row row = sheet.createRow(++rowCount);
             
            int columnCount = 0;
             
            for (Object field : aBook) {
                org.apache.poi.ss.usermodel.Cell cell = row.createCell(++columnCount);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
             
        }
         
         
        try (FileOutputStream outputStream = new FileOutputStream(path)) {
            workbook.write(outputStream);
            System.out.println(path+" written successfuly");
        }
    } 
  
    
    
}