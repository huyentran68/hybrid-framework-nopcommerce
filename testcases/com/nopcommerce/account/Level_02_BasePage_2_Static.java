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

public class Level_02_BasePage_2_Static {
    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");
    private String emailAddress;
    private BasePage basePage = BasePage.getBasePage();
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
        basePage.openPageUrl(driver,"https://demo.nopcommerce.com/");
        basePage.clickToElement(driver,"//a[@class='ico-register']");
        basePage.clickToElement(driver,"//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver,"//span[@id='FirstName-error']"),"First name is required.");
        Assert.assertEquals(basePage.getElementText(driver,"//span[@id='LastName-error']"),"Last name is required.");
        Assert.assertEquals(basePage.getElementText(driver,"//span[@id='Email-error']"),"Email is required.");
        Assert.assertEquals(basePage.getElementText(driver,"//span[@id='ConfirmPassword-error']"),"Password is required.");
    }
    @Test
    public  void Register_02_Invalid_Email(){
        basePage.openPageUrl(driver,"https://demo.nopcommerce.com/");

        basePage.clickToElement(driver,"//a[@class='ico-register']");
        basePage.sendKeyToElement(driver,"//input[@id='FirstName']","Automation");
        basePage.sendKeyToElement(driver,"//input[@id='LastName']","FC");
        basePage.sendKeyToElement(driver,"//input[@id='Email']","123@456#%*");
        basePage.sendKeyToElement(driver,"//input[@id='Password']","123456");
        basePage.sendKeyToElement(driver,"//input[@id='ConfirmPassword']","123456");

        basePage.clickToElement(driver,"//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver,"//span[@id='Email-error']"),"Please enter a valid email address.");
    }
    @Test
    public  void Register_03_Success(){
        emailAddress = "automation" + generateFakeNumber() + "@mail.vn";
        basePage.openPageUrl(driver,"https://demo.nopcommerce.com/");

        basePage.clickToElement(driver,"//a[@class='ico-register']");
        basePage.sendKeyToElement(driver,"//input[@id='FirstName']","Automation");
        basePage.sendKeyToElement(driver,"//input[@id='LastName']","FC");
        basePage.sendKeyToElement(driver,"//input[@id='Email']",emailAddress);
        basePage.sendKeyToElement(driver,"//input[@id='Password']","123456");
        basePage.sendKeyToElement(driver,"//input[@id='ConfirmPassword']","123456");

        basePage.clickToElement(driver,"//button[@id='register-button']");
        Assert.assertEquals(basePage.getElementText(driver,"//div[@class='result']"),"Your registration completed");
        basePage.clickToElement(driver,"//a[@class='ico-logout']");
    }
    @Test
    public  void Register_04_Register_Existing_Email(){
        basePage.openPageUrl(driver,"https://demo.nopcommerce.com/");

        basePage.clickToElement(driver,"//a[@class='ico-register']");
        basePage.sendKeyToElement(driver,"//input[@id='FirstName']","Automation");
        basePage.sendKeyToElement(driver,"//input[@id='LastName']","FC");
        basePage.sendKeyToElement(driver,"//input[@id='Email']",emailAddress);
        basePage.sendKeyToElement(driver,"//input[@id='Password']","123456");
        basePage.sendKeyToElement(driver,"//input[@id='ConfirmPassword']","123456");

        basePage.clickToElement(driver,"//button[@id='register-button']");
        Assert.assertEquals(basePage.getElementText(driver,"//div[contains(@class,'message-error')]//li"), "The specified email already exists");

    }
    @Test
    public  void Register_05_Password_Less_Than_6_Chars(){
        basePage.openPageUrl(driver,"https://demo.nopcommerce.com/");

        basePage.clickToElement(driver,"//a[@class='ico-register']");
        basePage.sendKeyToElement(driver,"//input[@id='FirstName']","Automation");
        basePage.sendKeyToElement(driver,"//input[@id='LastName']","FC");
        basePage.sendKeyToElement(driver,"//input[@id='Email']",emailAddress);
        basePage.sendKeyToElement(driver,"//input[@id='Password']","123");
        basePage.sendKeyToElement(driver,"//input[@id='ConfirmPassword']","123");

        basePage.clickToElement(driver,"//button[@id='register-button']");
        Assert.assertEquals(basePage.getElementText(driver,"//span[contains(@class,'field-validation-error')]"),"<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");

    }

    @Test
    public void Register_06_Invalid_Confirm_Password() {
        basePage.openPageUrl(driver,"https://demo.nopcommerce.com/");

        basePage.clickToElement(driver,"//a[@class='ico-register']");
        basePage.sendKeyToElement(driver,"//input[@id='FirstName']","Automation");
        basePage.sendKeyToElement(driver,"//input[@id='LastName']","FC");
        basePage.sendKeyToElement(driver,"//input[@id='Email']",emailAddress);
        basePage.sendKeyToElement(driver,"//input[@id='Password']","123456");
        basePage.sendKeyToElement(driver,"//input[@id='ConfirmPassword']","654321");

        basePage.clickToElement(driver,"//button[@id='register-button']");
        Assert.assertEquals(basePage.getElementText(driver,"//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
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
