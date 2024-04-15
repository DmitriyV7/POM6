package com.askomdch.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {
    protected WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }
    @FindBy(linkText = "Home")
    protected WebElement homePageLink;
    @FindBy(xpath = "//*[@id='menu-item-1227']/a")
    protected WebElement storeLink;
    @FindBy(id = "menu-item-1228")
    protected WebElement menLink;
    @FindBy(id = "menu-item-1229")
    protected WebElement womenLink;
    @FindBy(id = "menu-item-1230")
    protected WebElement accessoriesLink;
    @FindBy(linkText = "Account")
    protected WebElement accountLink;
    @FindBy(linkText = "About")
    protected WebElement aboutLink;
    @FindBy(id = "menu-item-1233")
    protected WebElement contactUsLink;
    @FindBy(id = "ast-site-header-cart")
    protected WebElement cartBtn;



    public void setAccountLink() throws InterruptedException {
        Thread.sleep(1000);
//        Assert.assertEquals(driver.getTitle(),"AskOmDch – Become a Selenium automation expert!","Title not correct ,not found or not correct page");
        accountLink.click();

    }
    public void setAboutLink(){
        aboutLink.click();
        Assert.assertEquals(driver.getTitle(),"About – AskOmDch","Title not correct ,not found or not correct page");

    }
    public void setStoreLink(){
        storeLink.click();
        Assert.assertEquals(driver.getTitle(),"Products – AskOmDch","Title not correct ,not found or not correct page");
    }
}
