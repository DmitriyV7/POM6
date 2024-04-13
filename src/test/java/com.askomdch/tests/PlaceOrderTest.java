package com.askomdch.tests;
import com.askomdch.pages.*;
import  com.askomdch.base_test.BaseTest;
import com.askomdch.utils.ExcelReader;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Map;




public class PlaceOrderTest extends BaseTest {
    @Test(dataProvider = "order")
    public void PlaceOrderTestAsGuest(Map<String, String> data) throws InterruptedException {
        extentTest = reports.startTest("Place Order test");
        HomePage homePage = new HomePage(driver);
        StorePage storePage = new StorePage(driver);
        CartPage cartPage = new CartPage(driver);
        homePage.setStoreLink();
        extentTest.log(LogStatus.PASS,"User opened the Store page successfully");
        storePage.storePageOrder(data);
        extentTest.log(LogStatus.PASS,"User added order to the cart successfully");
        cartPage.cartPageVerification(data);
        extentTest.log(LogStatus.PASS,"User completed order details at checkout as a Guest successfully");
        cartPage.asGuest(data);
        extentTest.log(LogStatus.PASS,"User placed an order in store as a Guest successfully");
        extentTest.log(LogStatus.PASS,"Guest User successfully landed on the Home page after checkout");
        Thread.sleep(3000);

    }
    @Test(dataProvider = "order")
    public void PlaceOrderAsRegisteredUser(Map<String, String> data) throws InterruptedException {
        extentTest = reports.startTest("Place Order test");
        HomePage homePage = new HomePage(driver);
        StorePage storePage = new StorePage(driver);
        CartPage cartPage = new CartPage(driver);
        AccountPage accountPage = new AccountPage(driver);
        homePage.setAccountLink();
        extentTest.log(LogStatus.PASS,"User opened the Account page successfully");
        accountPage.loginPositive(data);
        homePage.setStoreLink();
        extentTest.log(LogStatus.PASS,"User opened the Store page successfully");
        storePage.storePageOrder(data);
        extentTest.log(LogStatus.PASS,"User added order to the cart successfully");
        cartPage.cartPageVerification(data);
        extentTest.log(LogStatus.PASS,"User completed order details at checkout as a Registered User successfully");
        cartPage.asGuest(data);
        extentTest.log(LogStatus.PASS,"User placed an order in store as a Registered user successfully");
        extentTest.log(LogStatus.PASS,"Guest User successfully landed on the Home page after checkout");
        Thread.sleep(3000);
        homePage.setAccountLink();

    }
    @Test(dataProvider = "order")
    public void PlaceOrderAnCreateAccount(Map<String, String> data) throws InterruptedException {
        extentTest = reports.startTest("Place Order test");
        HomePage homePage = new HomePage(driver);
        StorePage storePage = new StorePage(driver);
        CartPage cartPage = new CartPage(driver);
        AccountPage accountPage = new AccountPage(driver);
        homePage.setStoreLink();
        extentTest.log(LogStatus.PASS,"User opened the Store page successfully");
        storePage.storePageOrder(data);
        extentTest.log(LogStatus.PASS,"User added order to the cart successfully");
        cartPage.cartPageVerification(data);
        extentTest.log(LogStatus.PASS,"User completed order details at checkout as a Registered User successfully");
        cartPage.createAccount(data);
        extentTest.log(LogStatus.PASS,"User placed an order in store as a Registered user successfully");
        extentTest.log(LogStatus.PASS,"Guest User successfully landed on the Home page after checkout");
        Thread.sleep(3000);

    }
    @DataProvider(name = "order")
    public Object[][] getData() {
        ExcelReader reader = new ExcelReader("src/main/resources/test_data/Test_Data.xlsx", "order");
        return reader.getData();

    }
}
