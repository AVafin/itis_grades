package webapp;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@WebServlet(urlPatterns = "/hello")
public class Servlet extends HttpServlet {

    private static final String FIRSTLIST = "1 курс 2016-2020";
    private static final String SECONDLIST = "2 курс 2015-2019";
    private static final String THIRDLIST = "3 курс 2014-2018";

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

    public class Student {
        public String group;
        public String name;
        public Double algem;
        public Double infka;
        public Double matan;
        public Double discmat;
        public Double lang;
        public Double history;
        public Double averagegrade;
        public Double aisd;
        // Add constructor, get, set, as needed.
    }

    public List<String> readFromExcel(String file) throws IOException {
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(file));

        List<String> excelSheets = new ArrayList<>();
        for (int i = 0; i < myExcelBook.getNumberOfSheets(); i++) {
            excelSheets.add(myExcelBook.getSheetName(i));
//            System.out.println("sheets " + i + ": " + excelSheets.get(i) + ". course: " + myExcelBook.getSheetName(i).subSequence(0, 1));
        }


        HSSFSheet myExcelSheet = myExcelBook.getSheet("2 курс 2015-2019");
        HSSFRow row = myExcelSheet.getRow(1);
        int noOfColumns = myExcelSheet.getRow(0).getPhysicalNumberOfCells();
        String group = row.getCell(1).getStringCellValue();
        String name = row.getCell(2).getStringCellValue();
        Double aig = row.getCell(3).getNumericCellValue();
//        System.out.println("group: " + group);
//        System.out.println("name: " + name);
//        System.out.println("aig: " + aig);
        List<String> colNames = new ArrayList<>();

        myExcelBook.close();
//        System.out.println("Количество колонок: " + noOfColumns);

        for (int col = 2; col < noOfColumns; col++) {
            String colValue = myExcelSheet.getRow(0).getCell(col).getStringCellValue().toLowerCase();
            colNames.add(colValue);
            /*if (colValue.length() > 6) {
                System.out.println("Длина: " + colValue.substring(0, 6));
            }*/
            if (colValue.length() > 6 && !colValue.substring(0, 7).equals("средний")) {
//                System.out.println(colValue);
            }
        }
        return colNames;
    }

    protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        Configuration configuration = ConfigSingleton.getConfig(getServletContext());
        Template tmpl = configuration.getTemplate("hello.ftl");
        HashMap<String, Object> root = new HashMap<>();
        root.put("username", "Амир");
        root.put("colNames", readFromExcel("/Users/AVafin/IdeaProjects/itis_grades/table.xls"));
        try {
            tmpl.process(root, response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

}
