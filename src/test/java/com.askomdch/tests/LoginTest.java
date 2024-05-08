package com.askomdch.tests;

import com.askomdch.pages.AboutPage;
import com.askomdch.pages.AccountPage;
import com.askomdch.pages.HomePage;
import  com.askomdch.base_test.BaseTest;
import com.askomdch.utils.ExcelReader;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Map;




public class LoginTest extends BaseTest {
    @Test(dataProvider = "login")
    public void LoginTest(Map<String, String> data) throws InterruptedException {
        extentTest = reports.startTest("Login test");
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = new AccountPage(driver);
        homePage.setHomePageLink();
        homePage.setAccountLink();
        extentTest.log(LogStatus.PASS,"User opened the Account page successfully");
        accountPage.loginPositive(data);
        extentTest.log(LogStatus.PASS,"User Logged in to the Account page successfully");
    }

//    @Test(dataProvider = "login")
    public void LoginTestRemember(Map<String, String> data) throws InterruptedException {
        extentTest = reports.startTest("Login Remember test");
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = new AccountPage(driver);
        homePage.setAccountLink();
        extentTest.log(LogStatus.PASS,"User opened the Account page successfully");
        accountPage.loginRemember(data);
        extentTest.log(LogStatus.PASS,"User Logged in and clicked 'Remember' checkmark on the Account page successfully");
    }

//    @Test(dataProvider = "login")
    public void LoginNegative(Map<String, String> data) throws InterruptedException {
        extentTest = reports.startTest("Login Negative test");
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = new AccountPage(driver);
        homePage.setAccountLink();
        extentTest.log(LogStatus.PASS,"User opened the Account page successfully");
        accountPage.loginNegative(data);
        extentTest.log(LogStatus.PASS,"User can't logged in with not correct password and username");
    }

//    @Test(dataProvider = "login")
    public void RegisterUserPositive(Map<String, String> data) throws InterruptedException {
        extentTest = reports.startTest("Registration new user Positive test");
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = new AccountPage(driver);
        homePage.setAccountLink();
        extentTest.log(LogStatus.PASS,"User opened the Account page successfully");
        accountPage.registerUserPositive(data);
        extentTest.log(LogStatus.PASS,"User made registration on the Account page successfully");
    }

//    @Test(dataProvider = "login")
    public void RegisterUserNegative(Map<String, String> data) throws InterruptedException {
        extentTest = reports.startTest("Registration new user Negative test");
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = new AccountPage(driver);
        homePage.setAccountLink();
        extentTest.log(LogStatus.PASS,"User opened the Account page successfully");
        accountPage.registerUserNegative(data);
        extentTest.log(LogStatus.PASS,"User can not made registration on the Account page ");
    }

//    @Test(dataProvider = "login")
    public void AboutPageTest(Map<String, String> data) throws InterruptedException {
        extentTest = reports.startTest("About page test");
        HomePage homePage = new HomePage(driver);
        AboutPage aboutPage = new AboutPage(driver);
        homePage.setAboutLink();
        aboutPage.teamNameVerification(data);
    }

    @DataProvider(name = "login")
    public Object[][] getData() {
        ExcelReader reader = new ExcelReader("src/main/resources/test_data/Test_Data.xlsx", "login");
        return reader.getData();
    }
}
