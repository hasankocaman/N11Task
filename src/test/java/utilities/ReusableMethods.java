package utilities;

import com.google.common.collect.Table;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class ReusableMethods {
// bir method olusturalim
    // sayfa ismi, dosya yolu,sayfa ismi ve satir, hucre indexini verince hucre bilgisini dondursun

    public static Table.Cell hucreGetir(String path, String sayfaIsmi, int satirIndex, int hucreIndex){
        Cell cell=null;
        try {
            FileInputStream fileInputStream=new FileInputStream(path);
            Workbook workbook= WorkbookFactory.create(fileInputStream);
            cell=workbook.getSheet(sayfaIsmi).getRow(satirIndex).getCell(hucreIndex);

        } catch (IOException e) {
            e.printStackTrace();
        }


        return (Table.Cell) cell;
    }

    public static Map<String,String> mapOlustur(String path, String sayfaAdi) {
        Map<String,String> excelMap=new TreeMap<>();
        Workbook workbook=null;
        //ilk adim excelde istenen sayfaya ulasmak
        try {
            FileInputStream fis=new FileInputStream(path);
            workbook=WorkbookFactory.create(fis);

        } catch (IOException e) {
            e.printStackTrace();
        }

        int satirSayisi=workbook.getSheet(sayfaAdi).getLastRowNum();
        String key="";
        String value="";
        for (int i = 0; i <satirSayisi ; i++) {
            //ikinci adim tablodaki hucreleri map'e uygun hale donusturmek
            key=  workbook.getSheet(sayfaAdi).getRow(i).getCell(0).toString();
            value=workbook.getSheet(sayfaAdi).getRow(i).getCell(1).toString()+
             ", "+workbook.getSheet(sayfaAdi).getRow(i).getCell(2).toString()+
             ", "+workbook.getSheet(sayfaAdi).getRow(i).getCell(3).toString();
//ucuncu adim key value haline getirdigimiz satirlari map'e eklemek
excelMap.put(key,value);
        }

        return excelMap;
    }

    public static String getScreenshot(String name) throws IOException {
        // naming the screenshot with the current date to avoid duplication
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // TakesScreenshot is an interface of selenium that takes the screenshot
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/target/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        return target;
    }

}
