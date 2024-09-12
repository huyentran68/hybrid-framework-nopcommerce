package com.nopcommerce.account;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_07_PageManager extends BaseTest {
    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");
    private String emailAddress;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private CustomerPageObject customerPage;
    @BeforeClass
    public void beforeClass(String browserName){
     driver = getBrowserDriver(browserName);
     homePage = PageGeneratorManager.getHomePage(driver);
    }
    @Test
    public  void User_01_Register_Emty_Data(){
        registerPage = homePage.clickToRegisterLink();
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getFirstNameErrorMessageText(),"First name is required.");
        Assert.assertEquals(registerPage.getLastNameErrorMessageText(),"Last name is required.");
        Assert.assertEquals(registerPage.getEmailErrorMessageText(),"Email is required.");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(),"Password is required.");

    }
    @Test
    public  void User_02_Register_Invalid_Email(){
        homePage =  registerPage.clickToNopCommerceLogo();
        registerPage = homePage.clickToRegisterLink();
        registerPage.enterToFirstNameTextBox("Automation");
        registerPage.enterToLastNameTextBox("FC");
        registerPage.enterToEmailTextBox("123@456#%*");
        registerPage.enterToPasswordTextBox("123456");
        registerPage.enterToConfirmPasswordTextBox("123456");
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getEmailErrorMessageText(),"Please enter a valid email address.");
    }
    @Test
    public  void User_03_Register_Success(){
        emailAddress = getEmailAddress();
        homePage = registerPage.clickToNopCommerceLogo();
        registerPage = homePage.clickToRegisterLink();
        registerPage.enterToFirstNameTextBox("Automation");
        registerPage.enterToLastNameTextBox("FC");
        registerPage.enterToEmailTextBox(emailAddress);
        registerPage.enterToPasswordTextBox("123456");
        registerPage.enterToConfirmPasswordTextBox("123456");
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getRegisterSuccessMessageText(),"Your registration completed");
        registerPage.clickToLogoutLink();
        System.out.println(emailAddress);
    }
    @Test
    public  void User_04_Register_Existing_Email(){
        homePage = registerPage.clickToNopCommerceLogo();
        registerPage = homePage.clickToRegisterLink();
        registerPage.enterToFirstNameTextBox("Automation");
        registerPage.enterToLastNameTextBox("FC");
        registerPage.enterToEmailTextBox(emailAddress);
        registerPage.enterToPasswordTextBox("123456");
        registerPage.enterToConfirmPasswordTextBox("123456");
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getEmailExistingMessageErrorText(), "The specified email already exists");

    }
    @Test
    public  void User_05_Register_Password_Less_Than_6_Chars(){
        homePage = registerPage.clickToNopCommerceLogo();
        registerPage = homePage.clickToRegisterLink();
        registerPage.enterToFirstNameTextBox("Automation");
        registerPage.enterToLastNameTextBox("FC");
        registerPage.enterToEmailTextBox(emailAddress);
        registerPage.enterToPasswordTextBox("123");
        registerPage.enterToConfirmPasswordTextBox("123");
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getPasswordValidationErrorText(),"<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");
    }

    @Test
    public void User_06_Register_Invalid_Confirm_Password() {
        homePage = registerPage.clickToNopCommerceLogo();
        registerPage = homePage.clickToRegisterLink();
        registerPage.enterToFirstNameTextBox("Automation");
        registerPage.enterToLastNameTextBox("FC");
        registerPage.enterToEmailTextBox(emailAddress);
        registerPage.enterToPasswordTextBox("123456");
        registerPage.enterToConfirmPasswordTextBox("654321");
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(), "The password and confirmation password do not match.");
    }
    @Test
    public void User_07_Login_Success(){
        homePage = registerPage.clickToNopCommerceLogo();
        loginPage = homePage.clickToLoginLink();
        loginPage.enterToEmailTextBox(emailAddress);
        loginPage.enterToPasswordTextBox("123456");
        homePage = loginPage.clickToLoginButton();
        customerPage = homePage.clickToMyAccountLink();
        Assert.assertEquals(customerPage.getFirstNameTextBoxAttributeValue(),"Automation");
        Assert.assertEquals(customerPage.getLastNameTextBoxAttributeValue(),"FC");
        Assert.assertEquals(customerPage.getEmailTextBoxAttributeValue(),emailAddress);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }


    public String getEmailAddress(){
        Random rand = new Random();
        return "automation" + rand.nextInt(9999) + "@mail.vn";
    }
}
