package Functions;

import Constants.Constants;
import logger.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.Driver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

public abstract class Functions {

    static Log log = new Log();
    protected WebDriver driver = Driver.getDriver();
    WebDriverWait wait;
    private static int timeout = 5;

    protected Functions() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }


    public void sendKeysUserSSOFunction(WebElement sendKeysElement) {
        wait.until(ExpectedConditions.visibilityOf(sendKeysElement));
        sendKeysElement.sendKeys(Constants.clientId);
    }

    public void sendKeysUserPassFunction(WebElement sendKeysElement) {
        wait.until(ExpectedConditions.visibilityOf(sendKeysElement));
        sendKeysElement.sendKeys(Constants.clientSecret);
    }

    public void clickFunction(WebElement clickElement) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(clickElement));
            //  clickWithJS(clickElement);
            log.info(clickElement.getText()+"  element is clicked");

            clickElement.click();
        } catch (NoSuchElementException e) {

            throw new IllegalStateException(clickElement.getText()+ " element not found.");
        }

    }

    public void sendKeysFunction(WebElement sendKeysElement, String value) {
        wait.until(ExpectedConditions.visibilityOf(sendKeysElement));
        sendKeysElement.sendKeys(value);
    }

    public void selectElementFromDropDownFunction(WebElement dropdown, String element) {
        Select slc = new Select(dropdown);
        slc.selectByVisibleText(element);
    }

    public void assertionFunction(WebElement actual, String expected) {
        wait.until(ExpectedConditions.visibilityOf(actual));
        Assert.assertEquals(actual.getText(), expected);
    }

    public String getText(WebElement getTextElement) {

        String extractedText = getTextElement.getText();
        return extractedText;
    }

    public Actions actionFunction() {
        Actions action = new Actions(driver);
        return action;

    }

    public void waitForAllElementsToLoad(List<WebElement> allElements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(allElements));
    }
    public void loadingDocumentsWaitDisappear(String textToDisappear){
        WebElement disappear=driver.findElement(By.xpath("//div[contains(text(),'"+textToDisappear+"')]"));
        if(disappear.isDisplayed()){
            wait.until(ExpectedConditions.invisibilityOf(disappear));
        }
    }
    public void takeScreenShot(String screenShotName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());

        try {
            FileUtils.copyFile(screenshot, new File("src/test/Test_Screenshots/" + screenShotName +"_"+timestamp+ ".png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void scrollIntoPage(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public String getAttributeValue(WebElement element,String attributeValue){
        String value=element.getAttribute(attributeValue);
        return value;
    }
    public void Control (boolean statement, String onTrue, String onFalse){
        if (statement==true){
            log.info(onTrue);
        }
        else {
            log.info(onFalse);}
    }
    public static boolean assertListElementsEqual(List<String> list1, List<String> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }

        return IntStream.range(0, list1.size())
                .allMatch(i -> list1.get(i).equals(list2.get(i)));
    }
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    public static void scrollIntoVIewJS(WebElement element) {
        JavascriptExecutor jsexecutor = ((JavascriptExecutor) Driver.getDriver());
        jsexecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        log.info(element.getText()+ "  element is viewed");
    }

    public boolean retryingFindClick(By by) {
        boolean result = false;
        int attempts = 0;
        while(attempts < 2) {
            try {
                driver.findElement(by).click();
                result = true;
                break;
            } catch(StaleElementReferenceException e) {
            }
            attempts++;
        }
        return result;
    }
    private static void assertDateDifference(List<LocalDate> dateDifferences, int filterDays) {
        dateDifferences.stream()
                .mapToLong(date -> ChronoUnit.DAYS.between(date, LocalDate.now()))
                .forEach(days -> {
                    System.out.printf("Difference between today: %d days%n", days);
                    // Add your assertion logic here
                    if (days > filterDays) {
                        System.out.println("Assertion failed: Difference is greater than filterDays. Filterdays="+ filterDays);
                    }
                });
    }

}
