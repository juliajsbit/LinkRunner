package govitallLinkRunner.core.utils.print;


import govitallLinkRunner.GovitallRobot;
import govitallLinkRunner.GovitallRobotCore;
import govitallLinkRunner.core.WebResource;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ParserExcel extends PrintResult{

   /* public String parse(HashMap<String, WebResource> map) {
        File file = new File(fileName);
        String result = "";
        InputStream in = null;
        HSSFWorkbook wb = null;
        try{
            in = new FileInputStream(file);
            wb = new HSSFWorkbook(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(0);
        for(Map.Entry<String, WebResource> webResource : robot.internalLinks.entrySet());
        Iterator<Row> it = sheet.iterator();
        int a = webResource.getValue().httpStatusCode;
        while (.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            Cell cell = cells.next();
            cell.setCellValue(en.toString().);
            while (cells.hasNext()) {
                Cell cell = cells.next();
                int cellType = cell.

            }
            result += "\n";
        }

        return result;
    }*/

    /*public  static  String fileName = getFileName();
    public GovitallRobot robot = new GovitallRobot();*/

}
