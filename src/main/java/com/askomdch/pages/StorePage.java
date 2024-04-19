package com.askomdch.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.Map;

public class StorePage extends HomePage {

    public StorePage(WebDriver driver) {
        super(driver);
    }

        @FindBy(id = "woocommerce-product-search-field-0")
        protected WebElement searchFieldInput;
        @FindBy(xpath = "(//button[@type='submit'])[1]")
        protected WebElement searchBtn;
        @FindBy(xpath = "(//button[@type='submit'])[2]")
        protected WebElement filterBtn;
        @FindBy(xpath = "//button[@type='submit']")
        protected WebElement addToCart;
        @FindBy(xpath = "//div[@class='woocommerce-message']/a")
        protected WebElement verifyCartMessage;
        @FindBy(id = "ast-site-header-cart")
        protected WebElement cartBtn;

        public void storePageOrder(Map<String, String> data) throws InterruptedException {
            Assert.assertEquals(driver.getTitle(),"Products – AskOmDch", "Not an Products Page");
            searchFieldInput.sendKeys(data.get("product_name"));
            searchBtn.click();
//            moveSlider(driver,-110);
//            filterBtn.click();
            WebElement productName = driver.findElement(By.xpath("//*[@class='woocommerce-loop-product__title'][contains(text(),'"+data.get("product_name1")+"')]"));
            productName.click();
            addToCart.click();
            Assert.assertTrue(verifyCartMessage.isDisplayed(),"Product not added to cart");
            cartBtn.click();
    }

    public static void moveSlider(WebDriver driver, int xOffset) {
        WebElement slider = driver.findElement(By.xpath("(//*[@class = 'price_slider_wrapper']//span)[2][@style='left: 100%;']"));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(slider, xOffset, 0).build().perform();
    }
}
