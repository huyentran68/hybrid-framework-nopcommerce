package com.nopcommerce.account;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_02_BasePage_2_Inheritance extends BasePage  {
    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");
    private String emailAddress;
    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
        emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @Test
    public  void Register_01_Emty_Data(){
        openPageUrl(driver,"https://demo.nopcommerce.com/");
        clickToElement(driver,"//a[@class='ico-register']");
        clickToElement(driver,"//button[@id='register-button']");

        Assert.assertEquals(getElementText(driver,"//span[@id='FirstName-error']"),"First name is required.");
        Assert.assertEquals(getElementText(driver,"//span[@id='LastName-error']"),"Last name is required.");
        Assert.assertEquals(getElementText(driver,"//span[@id='Email-error']"),"Email is required.");
        Assert.assertEquals(getElementText(driver,"//span[@id='ConfirmPassword-error']"),"Password is required.");
    }
    @Test
    public  void Register_02_Invalid_Email(){
        openPageUrl(driver,"https://demo.nopcommerce.com/");

        clickToElement(driver,"//a[@class='ico-register']");
        sendKeyToElement(driver,"//input[@id='FirstName']","Automation");
        sendKeyToElement(driver,"//input[@id='LastName']","FC");
        sendKeyToElement(driver,"//input[@id='Email']","123@456#%*");
        sendKeyToElement(driver,"//input[@id='Password']","123456");
        sendKeyToElement(driver,"//input[@id='ConfirmPassword']","123456");

        clickToElement(driver,"//button[@id='register-button']");

        Assert.assertEquals(getElementText(driver,"//span[@id='Email-error']"),"Please enter a valid email address.");
    }
    @Test
    public  void Register_03_Success(){
        emailAddress = "automation" + generateFakeNumber() + "@mail.vn";
        openPageUrl(driver,"https://demo.nopcommerce.com/");

        clickToElement(driver,"//a[@class='ico-register']");
        sendKeyToElement(driver,"//input[@id='FirstName']","Automation");
        sendKeyToElement(driver,"//input[@id='LastName']","FC");
        sendKeyToElement(driver,"//input[@id='Email']",emailAddress);
        sendKeyToElement(driver,"//input[@id='Password']","123456");
        sendKeyToElement(driver,"//input[@id='ConfirmPassword']","123456");

        clickToElement(driver,"//button[@id='register-button']");
        Assert.assertEquals(getElementText(driver,"//div[@class='result']"),"Your registration completed");
        clickToElement(driver,"//a[@class='ico-logout']");
    }
    @Test
    public  void Register_04_Register_Existing_Email(){
        openPageUrl(driver,"https://demo.nopcommerce.com/");

        clickToElement(driver,"//a[@class='ico-register']");
        sendKeyToElement(driver,"//input[@id='FirstName']","Automation");
        sendKeyToElement(driver,"//input[@id='LastName']","FC");
        sendKeyToElement(driver,"//input[@id='Email']",emailAddress);
        sendKeyToElement(driver,"//input[@id='Password']","123456");
        sendKeyToElement(driver,"//input[@id='ConfirmPassword']","123456");

        clickToElement(driver,"//button[@id='register-button']");
        Assert.assertEquals(getElementText(driver,"//div[contains(@class,'message-error')]//li"), "The specified email already exists");

    }
    @Test
    public  void Register_05_Password_Less_Than_6_Chars(){
        openPageUrl(driver,"https://demo.nopcommerce.com/");

        clickToElement(driver,"//a[@class='ico-register']");
        sendKeyToElement(driver,"//input[@id='FirstName']","Automation");
        sendKeyToElement(driver,"//input[@id='LastName']","FC");
        sendKeyToElement(driver,"//input[@id='Email']",emailAddress);
        sendKeyToElement(driver,"//input[@id='Password']","123");
        sendKeyToElement(driver,"//input[@id='ConfirmPassword']","123");

        clickToElement(driver,"//button[@id='register-button']");
        Assert.assertEquals(getElementText(driver,"//span[contains(@class,'field-validation-error')]"),"<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");

    }

    @Test
    public void Register_06_Invalid_Confirm_Password() {
        openPageUrl(driver,"https://demo.nopcommerce.com/");

        clickToElement(driver,"//a[@class='ico-register']");
        sendKeyToElement(driver,"//input[@id='FirstName']","Automation");
        sendKeyToElement(driver,"//input[@id='LastName']","FC");
        sendKeyToElement(driver,"//input[@id='Email']",emailAddress);
        sendKeyToElement(driver,"//input[@id='Password']","123456");
        sendKeyToElement(driver,"//input[@id='ConfirmPassword']","654321");

        clickToElement(driver,"//button[@id='register-button']");
        Assert.assertEquals(getElementText(driver,"//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    public int generateFakeNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }
}
