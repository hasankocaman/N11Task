package pages;

import logger.Log;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBaseRapor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.testng.AssertJUnit.assertEquals;


public class MainPage extends TestBaseRapor {

    WebDriverWait wait = null;
    Actions actions = new Actions(Driver.getDriver());

    ChromeOptions options = new ChromeOptions();
    static Log log = new Log();
    private static int timeout = 5;

    public MainPage() {
        PageFactory.initElements(Driver.getDriver(), this);

    }
    @FindBy(xpath = "//a[@class='btnSignIn']")
    public WebElement girisYapButonu;

    @FindBy(xpath = "//*[text()='Daha Sonra']")
    public WebElement dahaSonraButonu;


    @FindBy(xpath = "//input[@id='email']")
    public WebElement ePostaAdresiKutusu;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement sifreKutusu;

    @FindBy(xpath = "//div[@class='green_flat']")
    public WebElement loginButton;

    @FindBy(id = "searchData")
    public WebElement aramaKutusu;

    @FindBy (xpath = "//div[@class='resultText ']")
    public WebElement sonucYazisi;

    @FindBy(xpath = "//div[@class='pagination']//a")
    public List<WebElement> sayfalar;

    @FindBy(xpath = "//span[@title='Favorilere ekle']")
    public List<WebElement> ikinciSayfaUrunler;

    @FindBy(xpath = "//a[@title='Hesabım']")
    public WebElement hesabim;

    @FindBy(xpath = "(//a[text()='Favorilerim & Listelerim'])[1]")
    public WebElement favorilerimListelerim;

    @FindBy(xpath = "//div[@class='my_wishlist__container__header__tabs__option active']")
    public WebElement favorilerim;

    @FindBy(xpath = "(//div[@class='deleteProFromFavorites'])[1]")
    public WebElement favoriSilButon;

    @FindBy(xpath = "//span[@class='toaster-sub-title']")
    public WebElement uyariMesaji;

    @FindBy(xpath = "//a[@class='logoutBtn']")
    public WebElement cikisYap;


    public void setUp() {

//        - N11 sitesi açılır.
//        - Ana sayfanın açıldığı kontrol edilir.
        Driver.getDriver().get(ConfigReader.getProperty("Url"));
        wait(1);
        log.info(Driver.getDriver().getCurrentUrl() + "   test Url acildi");
        Control(Driver.getDriver().getCurrentUrl().contains("https://www.n11.com/"), "https://www.n11.com/ url acildi",
                "https://www.n11.com/ url acilamadi !!! acilan url=" + Driver.getDriver().getCurrentUrl());

//Add chrome switch to disable notification - "**--disable-notifications**"
        options.addArguments("--disable-notifications");
        Driver.getDriver().manage().window().maximize();
        dahaSonraButonu.click();
        try {
            wait(1);
            JavascriptExecutor js = (JavascriptExecutor)Driver.getDriver();
            js.executeScript("document.querySelector(\"body > efilli-layout-n11\")" +
                    ".shadowRoot.querySelector(\"div.desktop > div > div.banner__accept-button\").click();");
            log.info("cookie tiklandi");
            options.addArguments("--disable-notifications");
            Driver.getDriver().manage().deleteAllCookies(); // Deletes all the cookies
        } catch (Exception e) {
            log.error("Cerezler kabul edilemedi.");
        }





    }




    public void click(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        findElement(by).click();
    }

    public WebElement findElement(By by) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return Driver.getDriver().findElement(by);
    }


    public void ifExistClick(WebElement element) {
        try {
            wait(5);
            //waitForVisibility(element,5);
            if (element.isDisplayed()) {
                log.info(element.getText() + " elementi gozuktu");
                waitAndClick(element, 5);
                log.info(element.getText() + " elementine tiklandi");
            }

        } catch (Exception e) {
            log.error(element.getText()+"   elementine tiklanamadi");
        }
    }



    public  void waitAndClick(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                log.info(element.getText()+" elementine tiklandi");
                return;
            } catch (WebDriverException e) {
                wait(1);
                log.error(element.getText()+" elementine tiklanamadi!!!");
            }
        }
    }

    public static void wait(int secs) {

        try {
            Thread.sleep(1000 * secs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void sendKeys(WebElement element, String text) {
        sendKeys(element, text);

    }

    public void alanTemizle(WebElement element) {

        while (!element.getAttribute("value").equals("")) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    public int belirliAraliktaSayiUret(int size) {
        Random rand = new Random();

        int R = rand.nextInt(1,size);

        return R;

    }





    public static void scrollIntoVIewJS(WebElement element) {
        JavascriptExecutor jsexecutor = ((JavascriptExecutor) Driver.getDriver());
        jsexecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void elementiGoreneKadarKaydirVeTikla(WebElement element){
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript("arguments[0].scrollIntoView()",element);

        wait(5);
        log.info(element.getText()+"  elementi goruldu");
        wait(5);
        jse.executeScript("arguments[0].click();", element);
        log.info("  element tiklandi ve sepete gidildi");
        wait(3);

    }
    public static void  clickNew (WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        boolean breakIt = true;
        while (true) {
            breakIt = true;
            try {
                wait(5);
        jse.executeScript("arguments[0].scrollIntoView()",element);
                wait(3);
                jse.executeScript("arguments[0].click();", element);
                log.info(element.getText()+"  elementi tiklandi");
                wait(5);
            } catch (Exception e) {
                if (e.getMessage().contains("element is not attached")) {
                    breakIt = false;
                }
            }
            if (breakIt) {
                break;
            }

        }

    }

    static String urunBilgisi;
    static String tutarBilgisi;
    static String urunveTutar;




    public void txtDosyasinaYaz(String urunBilgisi) {
        String filename = "fileToRead.txt";
        FileWriter fw = null;
        try {
            fw = new FileWriter(filename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);

            String text = urunBilgisi;
            out.write(text);
            log.info("txt dosyasina yazildi");
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//do stuff
//open streams again and write
    }

    public void Control(boolean statement, String onTrue, String onFalse) {
        if (statement == true) {
            log.info(onTrue);
        } else {
            log.info(onFalse);
        }
    }


    public static int rnd(){
        Random rnd=new Random();
        return rnd.nextInt(1,49);
    }


    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }
}


