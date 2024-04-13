package com.askomdch.pages;
import com.google.common.base.Strings;
import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;

import org.testng.Assert;

import java.util.Map;

public class AboutPage extends HomePage {

    public AboutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "(//*[@class='wp-block-columns alignwide are-vertically-aligned-center'])[1]")
    protected WebElement ourTeamInfo1;
    @FindBy(xpath = "(//*[@class='wp-block-columns alignwide are-vertically-aligned-center'])[2]")
    protected WebElement ourTeamInfo2;


    public void verifyOurTeamMembers(Map<String, String> data) throws InterruptedException {
        Assert.assertTrue(ourTeamInfo1.isDisplayed(), "OurTeam info not displayed");
        Assert.assertTrue(ourTeamInfo2.isDisplayed(), "OurTeam info not displayed");
        scrollToText(driver, "OUR TEAM");
        teamNameVerification(data);

        Thread.sleep(3000);
    }

    public static void scrollToText(WebDriver driver, String searchText) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.find('" + searchText + "', 0, 0, 1)");
    }

    public void teamNameVerification(Map<String, String> data) {
        String[] expTeamMembers = data.get("team_members").split(",");
        for (int i = 0; i < 6; i++) {
            if (i < expTeamMembers.length) {
                String teamMember = expTeamMembers[i];
                String[] nameAndPosition = teamMember.split("_");
                String name = nameAndPosition[0];
                String position = nameAndPosition[1];
                String xpathName = "(//*[@class ='wp-block-columns alignwide are-vertically-aligned-center']//h4)[" + (i + 1) + "]";
                String xpathPosition = String.format("//p[contains(text(), '%s')]", position);
//                System.out.println(teamMember);
                WebElement nameElement = driver.findElement(By.xpath(xpathName));
                WebElement positionElement = driver.findElement(By.xpath(xpathPosition));
//                System.out.println(nameElement.getText());
//                System.out.println(positionElement.getText());
                Assert.assertEquals(nameElement.getText(), name, "Name mismatch for " + name);
                Assert.assertEquals(positionElement.getText(), position, "Position mismatch for " + position);
            } else {
                break;
            }
        }
    }
}



















