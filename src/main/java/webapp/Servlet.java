package webapp;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.extractor.ExcelExtractor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(urlPatterns = "/hello")
public class Servlet extends HttpServlet {

    public String showParsedText() throws Exception {
        InputStream in = new FileInputStream("/Users/AVafin/IdeaProjects/itis_grades/table.xls");
        // Внимание InputStream будет закрыт
        // Если нужно не закрывающий см. JavaDoc по POIFSFileSystem :  http://goo.gl/1Auu7
        HSSFWorkbook wb = new HSSFWorkbook(in);

        ExcelExtractor extractor = new ExcelExtractor(wb);
        extractor.setFormulasNotResults(false); // Считать формулы
        extractor.setIncludeSheetNames(true);
        String text = extractor.getText();
        return text;
    }

    public String readFromExcel(String file) throws IOException{
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet myExcelSheet = myExcelBook.getSheet("2 курс 2015-2019");
        HSSFRow row = myExcelSheet.getRow(1);

        String group = row.getCell(1).getStringCellValue();
        String name = row.getCell(2).getStringCellValue();
        Double bd = row.getCell(3).getNumericCellValue();
        System.out.println("group: " + group);
        System.out.println("name: " + name);
        System.out.println("bd: " + bd);
        String[] catsNames = {
                "Васька",
                "Кузя",
                "Барсик",
                "Мурзик",
                "Леопольд",
                "Бегемот",
                "Рыжик",
                "Матроскин"
        };
        /*if(row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING){
            String name = row.getCell(0).getStringCellValue();
            System.out.println("name : " + name);
        }

        if(row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
            Date birthdate = row.getCell(1).getDateCellValue();
            System.out.println("birthdate :" + birthdate);
        }*/

        myExcelBook.close();
        return name;
    }

    protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter writer = response.getWriter()) {

            writer.println("<!DOCTYPE html><html>");
            writer.println("<head>");
            writer.println("<meta charset=\"UTF-8\" />");
            writer.println("<title>MyServlet.java:doGet(): Servlet code!</title>");
            writer.println("</head>");
            writer.println("<body>");

            writer.println("<h1>This is a simple java servlet.</h1>");
            writer.println(readFromExcel("/Users/AVafin/IdeaProjects/itis_grades/table.xls"));
            writer.println("</body>");
            writer.println("</html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
