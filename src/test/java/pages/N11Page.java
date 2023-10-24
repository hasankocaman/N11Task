package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class N11Page {

    public N11Page(){
        PageFactory.initElements(Driver.getDriver(),this); }

    @FindBy(xpath = "//a[@class='btnSignUp']")
    public WebElement uyeOlButonu;

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



}
