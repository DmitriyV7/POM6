package com.askomdch.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.Map;

public class ContactUsPage extends HomePage {

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

        @FindBy(tagName = "h1")
        protected WebElement contactUsHeading;
        @FindBy(xpath = "//*[@class = 'wp-block-column is-vertically-aligned-center']/p[1]")
        protected WebElement emailVerify;
        @FindBy(xpath = "//*[@class = 'wp-block-column is-vertically-aligned-center']/p[2]")
        protected WebElement infoVerify;
        public void contactUsVerify(Map<String, String> data) throws InterruptedException {
            Assert.assertEquals(driver.getTitle(),"Contact Us – AskOmDch", "Not an Products Page");
            Assert.assertEquals(contactUsHeading.getText(),"Contact Us","Not correct heading");
            Assert.assertEquals(emailVerify.getText(), data.get("email_verify"));
            String trimInfoVerify = infoVerify.getText().trim();
            String compressedText = trimInfoVerify.replaceAll("\\s+", " ");
            String finalText = compressedText.replaceAll("(?m)^\\s*$", "");
            Assert.assertEquals(compressedText, data.get("info_verify"),"Information is Not correct");


    }
}
