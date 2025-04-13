package TestCases;

import Pages.LoginPage;
import Pages.PLP;
import Utility.ExUtility;
import Pages.CheckoutStepTwoPage;
import Pages.CheckoutCompletePage;
import Pages.CheckoutStepOnePage;
import Pages.CartPage;
import com.google.common.util.concurrent.UncheckedTimeoutException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class LoginTest {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options=new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getData(Method method) {
        String excelPath = "C:\\Users\\Hassan\\Downloads\\dataProviding.xlsx";
        ExUtility excel = new ExUtility(excelPath, "Sheet1");

        int rowCount = excel.getRowCount();
        int colCount = excel.getColCount();

        Object[][] data = new Object[rowCount - 1][colCount];
        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = excel.getCellData(i, j);
            }
        }
        return data;
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillUsername(username);
        loginPage.fillpassword(password);
        loginPage.Clickonloginbtn();

    }

    @Test
    public void testStandareduser() {
        LoginPage loginPage = new LoginPage(driver);
        PLP plp = new PLP(driver);
        CartPage cartPage=new CartPage(driver);
        CheckoutStepOnePage stepOne =new CheckoutStepOnePage(driver);
        CheckoutStepTwoPage stepTwo =new CheckoutStepTwoPage(driver);
        loginPage.fillUsername("standard_user");
        loginPage.fillpassword("secret_sauce");
        loginPage.Clickonloginbtn();


        String[] sortOptions = {
                "Name (A to Z)",
                "Name (Z to A)",
                "Price (low to high)",
                "Price (high to low)"

        };


        try {
            for (String option : sortOptions) {
                plp.selectSortOption(option);
                Thread.sleep(500); // pause to observe sorting

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        plp.addBackpackToCart();
        plp.addJacketToCart();
        plp.addBikeLightToCart();
        plp.addTshirtToCart();
        plp.addOnesieToCart();
        plp.clickIcon();
        plp.ResetState();
        plp.aboutPage();
        System.out.println("The number of added items is: "+ plp.getItemsAdded());
        driver.get("https://www.saucedemo.com/inventory.html");
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        plp.goToCart();
        cartPage.clickCheckout();

        stepOne.enterCheckoutInfo("Hassan", "Tester", "12345");
        stepOne.clickContinue();

        stepTwo.clickFinish();
        driver.navigate().to("https://www.saucedemo.com/inventory.html");
        plp.clickIcon();

//        Assert.assertTrue(completePage.isOrderComplete(), "Order was not completed successfully.");
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        plp.Logout();
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
