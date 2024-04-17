package com.askomdch.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.Map;

public class AccountPage extends HomePage {

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(tagName = "h1")
    public WebElement accountHeading;
    @FindBy(id = "username")
    protected WebElement userNameInput;
    @FindBy(id = "reg_username")
    protected WebElement newRegisterUserNameInput;
    @FindBy(id = "reg_email")
    protected WebElement newRegisterEmailInput;
    @FindBy(id = "password")
    protected WebElement passwordInput;
    @FindBy(id = "reg_password")
    protected WebElement newPasswordInput;
    @FindBy(id = "rememberme")
    protected WebElement rememberMeCheckBox;
    @FindBy(xpath = "//*[@name = 'login']")
    protected WebElement loginButton;
    @FindBy(xpath = "//*[@name='register']")
    protected WebElement registerBtn;
    @FindBy(xpath = "//*[@class='woocommerce-MyAccount-content']//strong")
    protected WebElement loginMessage;
    @FindBy(xpath = "//*[@class= 'woocommerce-LostPassword lost_password']/a")
    protected WebElement lostPasswordLink;
    @FindBy(xpath = "//*[@class='woocommerce-error']//strong")
    protected WebElement errorMessage;
    @FindBy(xpath = "//*[@class='woocommerce-error']//li")
    protected WebElement errorMessage2;
    @FindBy(xpath = "//*[@class = 'woocommerce-MyAccount-navigation']//*[contains(text(),'Logout')]")
    public WebElement logoutBtn;

    public void loginPositive(Map<String, String> data) throws InterruptedException {
        Assert.assertEquals(driver.getTitle(),"Account – AskOmDch", "Not an Account Page");
        Assert.assertEquals(accountHeading.getText(),"Account","Account heading not expected");
        userNameInput.sendKeys(data.get("user_name"));
        passwordInput.sendKeys(data.get("password"));
        Assert.assertTrue(loginButton.isEnabled(), "Login button is not enabled");
        Assert.assertTrue(rememberMeCheckBox.isDisplayed(),"CheckBox not displayed");
        Assert.assertTrue(lostPasswordLink.isDisplayed(),"Lost password link not displayed");
        loginButton.click();
        Thread.sleep(2000);
        Assert.assertEquals(loginMessage.getText(),data.get("user_name"),"Is not correct Login message");
        logoutBtn.click();
    }

    public void loginRemember(Map<String, String> data) throws InterruptedException {
        Assert.assertEquals(driver.getTitle(),"Account – AskOmDch", "Not an Account Page");
        Assert.assertEquals(accountHeading.getText(),"Account","Account heading not expected");
        userNameInput.sendKeys(data.get("user_name"));
        passwordInput.sendKeys(data.get("password"));
        Assert.assertTrue(loginButton.isEnabled(), "Login button is not enabled");
        Assert.assertTrue(rememberMeCheckBox.isDisplayed(),"CheckBox not displayed");
        rememberMeCheckBox.click();
        Assert.assertTrue(lostPasswordLink.isDisplayed(),"Lost password link not displayed");
        loginButton.click();
        Thread.sleep(2000);
        Assert.assertEquals(loginMessage.getText(),data.get("user_name"),"Is not correct Login message");
        logoutBtn.click();
    }

    public void loginNegative(Map<String, String> data) throws InterruptedException {
        Assert.assertEquals(driver.getTitle(),"Account – AskOmDch", "Not an Account Page");
        Assert.assertEquals(accountHeading.getText(),"Account","Account heading not expected");
        userNameInput.sendKeys(data.get("wrong_user_name"));
        passwordInput.sendKeys(data.get("wrong_password"));
        Assert.assertTrue(loginButton.isEnabled(), "Login button is not enabled");
        Assert.assertTrue(rememberMeCheckBox.isDisplayed(),"CheckBox not displayed");
        rememberMeCheckBox.click();
        Assert.assertTrue(lostPasswordLink.isDisplayed(),"Lost password link not displayed");
        loginButton.click();
        Thread.sleep(2000);
        Assert.assertTrue(errorMessage.isDisplayed(),"Error message not displayed");
    }

    public void registerUserPositive(Map<String, String> data) throws InterruptedException {
        Assert.assertEquals(driver.getTitle(),"Account – AskOmDch", "Not an Account Page");
        Assert.assertEquals(accountHeading.getText(),"Account","Account heading not expected");
        Thread.sleep(2000);
        newRegisterUserNameInput.clear();
        newRegisterUserNameInput.sendKeys(data.get("new_user_name"));
        newRegisterEmailInput.clear();
        newRegisterEmailInput.sendKeys(data.get("new_email"));
        newPasswordInput.clear();
        newPasswordInput.sendKeys(data.get("new_password"));
        Assert.assertTrue(registerBtn.isEnabled(),"Register button not enabled");
        registerBtn.click();
        Thread.sleep(2000);
        Assert.assertEquals(loginMessage.getText(),data.get("new_user_name"),"Is not correct Login message");
        Assert.assertTrue(logoutBtn.isDisplayed(),"Logout button not displayed");
        logoutBtn.click();
    }

    public void registerUserNegative(Map<String, String> data) throws InterruptedException {
        Assert.assertEquals(driver.getTitle(),"Account – AskOmDch", "Not an Account Page");
        Assert.assertEquals(accountHeading.getText(),"Account","Account heading not expected");
        newRegisterUserNameInput.sendKeys(data.get("user_name"));
        newRegisterEmailInput.sendKeys(data.get("used_email"));
        newPasswordInput.sendKeys(data.get("password"));
        Assert.assertTrue(registerBtn.isEnabled(),"Register button not enabled");
        registerBtn.click();
        Thread.sleep(2000);
        Assert.assertEquals(errorMessage2.getText(),data.get("error_message"),"Error message not displayed");
    }

    public void loginWithOutLogOut(Map<String, String> data) throws InterruptedException {
        Assert.assertEquals(driver.getTitle(),"Account – AskOmDch", "Not an Account Page");
        Assert.assertEquals(accountHeading.getText(),"Account","Account heading not expected");
        userNameInput.sendKeys(data.get("user_name"));
        passwordInput.sendKeys(data.get("password"));
        Assert.assertTrue(loginButton.isEnabled(), "Login button is not enabled");
        Assert.assertTrue(rememberMeCheckBox.isDisplayed(),"CheckBox not displayed");
        rememberMeCheckBox.click();
        Assert.assertTrue(lostPasswordLink.isDisplayed(),"Lost password link not displayed");
        loginButton.click();
        Assert.assertEquals(loginMessage.getText(),data.get("user_name"),"Is not correct Login message");
    }
}
