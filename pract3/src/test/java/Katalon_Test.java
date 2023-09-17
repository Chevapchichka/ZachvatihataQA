import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Katalon_Test {
    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\winuser\\Desktop\\GitHub\\ZachvatihataQA\\pract3\\chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.manage().window().fullscreen();
    }

    @Test
    public void Confirmed_Pay() throws Exception {
        driver.get(baseUrl);

        String Amount = (String) driver.findElement(By.xpath("//*[@id=\"total-amount\"]")).getText();
        String Currency = (String) driver.findElement(By.xpath("//*[@id=\"currency\"]")).getText();
        String order_number = (String) driver.findElement(By.id("order-number-cnt")).getText();
        WebElement inputCardNumber = driver.findElement(By.id("input-card-number"));
        inputCardNumber.click();
        inputCardNumber.clear();
        inputCardNumber.sendKeys("4000000000000051");
        WebElement inputCardHolder = driver.findElement(By.id("input-card-holder"));
        inputCardHolder.click();
        inputCardHolder.clear();
        inputCardHolder.sendKeys("LADA KC");
        driver.findElement(By.id("card-expires-month")).click();
        new Select(driver.findElement(By.id("card-expires-month"))).selectByVisibleText("02");
        driver.findElement(By.id("card-expires-year")).click();
        new Select(driver.findElement(By.id("card-expires-year"))).selectByVisibleText("2040");
        driver.findElement(By.id("input-card-cvc")).click();
        driver.findElement(By.id("input-card-cvc")).clear();
        driver.findElement(By.id("input-card-cvc")).sendKeys("543");
        driver.findElement(By.id("action-submit")).click();
        //driver.findElement(By.id("successButton")).submit();

        assertEquals("CONFIRMED", driver.findElement(By.xpath("//div[@id='payment-item-status']/div[2]")).getText());
        assertEquals(order_number, "Order number: " + driver.findElement(By.xpath("//div[@id='payment-item-ordernumber']/div[2]")).getText());
        assertEquals(Currency + "   " + Amount, driver.findElement(By.xpath("//*[@id=\"payment-item-total\"]/div[2]")).getText());
    }
    @Test
    public void InvalidCardNumber() throws Exception {
        driver.get(baseUrl);

        WebElement inputCardNumber = driver.findElement(By.id("input-card-number"));
        inputCardNumber.click();
        inputCardNumber.clear();
        inputCardNumber.sendKeys("0000000000000000");
        WebElement inputCardHolder = driver.findElement(By.id("input-card-holder"));
        inputCardHolder.click();
        inputCardHolder.clear();
        inputCardHolder.sendKeys("LADA KC");
        driver.findElement(By.id("card-expires-month")).click();
        new Select(driver.findElement(By.id("card-expires-month"))).selectByVisibleText("02");
        driver.findElement(By.id("card-expires-year")).click();
        new Select(driver.findElement(By.id("card-expires-year"))).selectByVisibleText("2040");
        driver.findElement(By.id("input-card-cvc")).click();
        driver.findElement(By.id("input-card-cvc")).clear();
        driver.findElement(By.id("input-card-cvc")).sendKeys("543");
        driver.findElement(By.id("action-submit")).click();

        assertEquals("Card number is not valid", driver.findElement(By.xpath("//*[@id=\"card-number-field\"]/div/label")).getText());

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();

    }

}
