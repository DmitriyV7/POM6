package com.askomdch.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.Map;

public class CartPage extends HomePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@class='product-name'][@data-title]")
    protected WebElement cartProductName;
    @FindBy(xpath = "//input[@type='number']")
    protected WebElement quantityInput;
    @FindBy(xpath = "//button[@name='update_cart']")
    protected WebElement updateCartBtn;
    @FindBy(xpath = "//button[@name='apply_coupon']")
    protected WebElement applyCouponBtn;
    @FindBy(xpath = "//div[@class='wc-proceed-to-checkout']/a")
    protected WebElement proceedToCheckoutBtn;
    @FindBy(id = "billing_first_name")
    protected WebElement billingFirstNameInput;
    @FindBy(id = "shipping_first_name")
    protected WebElement shippingFirstNameInput;
    @FindBy(id = "billing_last_name")
    protected WebElement billingLastNameInput;
    @FindBy(id = "shipping_last_name")
    protected WebElement shippingLastNameInput;
    @FindBy(id = "billing_company")
    protected WebElement billingCompanyNameInput;
    @FindBy(id = "shipping_company")
    protected WebElement shippingCompanyNameInput;
    @FindBy(id = "billing_country")
    protected WebElement billingCountrySelect;
    @FindBy(id = "shipping_country")
    protected WebElement shippingCountrySelect;
    @FindBy(id = "billing_address_1")
    protected WebElement billingAddress1Input;
    @FindBy(id = "shipping_address_1")
    protected WebElement shippingAddress1Input;
    @FindBy(id = "billing_address_2")
    protected WebElement billingAddress2Input;
    @FindBy(id = "shipping_address_2")
    protected WebElement shippingAddress2Input;
    @FindBy(id = "billing_city")
    protected WebElement billingCityInput;
    @FindBy(id = "shipping_city")
    protected WebElement shippingCityInput;
    @FindBy(id = "billing_state")
    protected WebElement billingStateSelect;
    @FindBy(id = "shipping_state")
    protected WebElement shippingStateSelect;
    @FindBy(id = "billing_postcode")
    protected WebElement billingPostcodeInput;
    @FindBy(id = "shipping_postcode")
    protected WebElement shippingPostcodeInput;
    @FindBy(id = "billing_phone")
    protected WebElement billingPhoneInput;
    @FindBy(id = "billing_email")
    protected WebElement billingEmailInput;
    @FindBy(id = "createaccount")
    protected WebElement createAccountCheckBox;
    @FindBy(id = "payment_method_bacs")
    protected WebElement bankTransferMethodRadioBtn;
    @FindBy(id = "payment_method_cod")
    protected WebElement cashMethodRadioBtn;
    @FindBy(id = "place_order")
    protected WebElement placeOrderBtn;
    @FindBy(xpath = "//*[@class='woocommerce-notice woocommerce-notice--success woocommerce-thankyou-order-received']")
    protected WebElement orderConfirmationMessage;
    @FindBy(id = "account_username")
    protected WebElement accountUsernameInput;
    @FindBy(id = "account_password")
    protected WebElement accountPasswordInput;
    @FindBy(id = "ship-to-different-address-checkbox")
    protected WebElement shipDifferentAddressCheckbox;


    public void cartPageVerification(Map<String, String> data) throws InterruptedException {
        Assert.assertEquals(driver.getTitle(),"Cart – AskOmDch", "Not a Cart Page");
        Assert.assertEquals(cartProductName.getText(),data.get("product_name1"),"Product name different, wrong order");
        quantityInput.clear();
        quantityInput.sendKeys(data.get("quantity"));
        Assert.assertTrue(applyCouponBtn.isDisplayed(),"Apply coupon Button not displayed");
        updateCartBtn.click();
        Thread.sleep(2000);
        if(!proceedToCheckoutBtn.isEnabled()){
            Thread.sleep(2000);
        }else {
        Assert.assertTrue(proceedToCheckoutBtn.isDisplayed(),"Proceed to Checkout  Button not displayed");
        proceedToCheckoutBtn.click();
        }
        billingFirstNameInput.clear();
        billingFirstNameInput.sendKeys(data.get("first_name"));
        billingLastNameInput.clear();
        billingLastNameInput.sendKeys(data.get("last_name"));
        billingCompanyNameInput.clear();
        billingCompanyNameInput.sendKeys(data.get("company_name"));
        Select countrySelect = new Select(billingCountrySelect);
        countrySelect.selectByVisibleText(data.get("country"));
        billingAddress1Input.clear();
        billingAddress1Input.sendKeys(data.get("street_address"));
        billingAddress2Input.clear();
        billingAddress2Input.sendKeys(data.get("apt"));
        billingCityInput.clear();
        billingCityInput.sendKeys(data.get("city"));
        if(data.get("country").equals("United States (US)")){
            Select stateSelect = new Select(billingStateSelect);
            stateSelect.selectByVisibleText(data.get("state"));
        }else {
            billingStateSelect.sendKeys(data.get("state"));
        }
        billingPostcodeInput.clear();
        billingPostcodeInput.sendKeys(data.get("postcode"));
        billingPhoneInput.clear();
        billingPhoneInput.sendKeys(data.get("phone_number"));
        billingEmailInput.clear();
        billingEmailInput.sendKeys(data.get("email"));
        Thread.sleep(1000);
    }

    public void asGuest(Map<String, String> data) throws InterruptedException {
        cashMethodRadioBtn.click();
        placeOrderBtn.click();
        Thread.sleep(3000);
        Assert.assertEquals(orderConfirmationMessage.getText(),data.get("order_confirmation"),"Check your order again,not conformed");
        homePageLink.click();
    }

    public void asRegisteredUser(Map<String, String> data) throws InterruptedException {
        placeOrderBtn.click();
        Thread.sleep(3000);
        Assert.assertEquals(orderConfirmationMessage.getText(),data.get("order_confirmation"),"Check your order again,not conformed");
        homePageLink.click();
    }

    public void createAccount(Map<String, String> data) throws InterruptedException {
        billingEmailInput.clear();
        billingEmailInput.sendKeys(data.get("new_email"));
        createAccountCheckBox.click();
        accountUsernameInput.sendKeys(data.get("new_user_name"));
        accountPasswordInput.sendKeys(data.get("new_password"));
        cashMethodRadioBtn.click();
        placeOrderBtn.click();
        Thread.sleep(3000);
        if(!orderConfirmationMessage.isDisplayed()){
            Thread.sleep(2000);
        }else {
        Assert.assertEquals(orderConfirmationMessage.getText(),data.get("order_confirmation"),"Check your order again,not conformed");
        }
        homePageLink.click();
        accountLink.click();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.logoutBtn.click();
    }

    public void shipDifferentAddress(Map<String, String> data) throws InterruptedException {
        shipDifferentAddressCheckbox.click();
        shippingFirstNameInput.sendKeys(data.get("shipping_first_name"));
        shippingLastNameInput.sendKeys(data.get("shipping_last_name"));
        shippingCompanyNameInput.sendKeys(data.get("shipping_company_name"));
        Select countrySelect = new Select(shippingCountrySelect);
        countrySelect.selectByVisibleText(data.get("shipping_country"));
        shippingAddress1Input.sendKeys(data.get("shipping_street_address"));
        shippingAddress2Input.sendKeys(data.get("shipping_apt"));
        shippingCityInput.sendKeys(data.get("shipping_city"));
        if(data.get("shipping_country").equals("United States (US)")){
            Select stateSelect = new Select(shippingStateSelect);
            stateSelect.selectByVisibleText(data.get("shipping_state"));
        }else {
            shippingStateSelect.sendKeys(data.get("shipping_state"));
        }
        shippingPostcodeInput.sendKeys(data.get("shipping_postcode"));
        if(!placeOrderBtn.isEnabled()){
            Thread.sleep(2000);
        }else {
        placeOrderBtn.click();
        }
        Assert.assertEquals(orderConfirmationMessage.getText(),data.get("order_confirmation"),"Check your order again,not conformed");
        homePageLink.click();
        accountLink.click();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.logoutBtn.click();
    }
}
