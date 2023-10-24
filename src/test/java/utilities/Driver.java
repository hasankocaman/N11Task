package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    public Driver(){

    }

    public static WebDriver  driver;

    public static WebDriver getDriver(){



        if(driver==null){

            switch (ConfigReader.getProperty("browser")){

                case "chrome":

                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver();
                    break;


                default:
                    WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver();
            }

        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }

    public static void closeDriver(){
        if (driver!=null){
          //  driver.quit();
            driver=null;
        }

    }


}
