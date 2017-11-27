import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
 
public class App {
 
    public static void main(String[] args) throws Exception {
        InputStream in = new FileInputStream("table.xls");
        // Внимание InputStream будет закрыт
        // Если нужно не закрывающий см. JavaDoc по POIFSFileSystem :  http://goo.gl/1Auu7
        HSSFWorkbook wb = new HSSFWorkbook(in);

        ExcelExtractor extractor = new ExcelExtractor(wb);
        extractor.setFormulasNotResults(false); // Считать формулы
        extractor.setIncludeSheetNames(true);
        String text = extractor.getText();

        System.out.println(text);

    }
}