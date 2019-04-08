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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;

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
        FileInputStream file = new FileInputStream(new File(path));
        Workbook workbook = new XSSFWorkbook(file);
    }
    
}