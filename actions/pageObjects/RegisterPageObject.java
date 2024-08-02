package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;
    public void clickToNopCommerceLogo() {
        waitForElementClickable(driver, RegisterPageUI.NOPCOMMERCE_LOGO);
        clickToElement(driver,RegisterPageUI.NOPCOMMERCE_LOGO);
    }
    public void clickToRegisterButton() {
        waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver,RegisterPageUI.REGISTER_BUTTON);
    }
    public String getFirstNameErrorMessageText() {
        waitForElementVisible(driver,RegisterPageUI.FIRSTNAME_ERROR_MSG);
        return getElementText(driver,RegisterPageUI.FIRSTNAME_ERROR_MSG);
    }

    public String getLastNameErrorMessageText() {
        waitForElementVisible(driver,RegisterPageUI.LASTNAME_ERROR_MSG);
        return getElementText(driver,RegisterPageUI.LASTNAME_ERROR_MSG);
    }

    public String getEmailErrorMessageText() {
        waitForElementVisible(driver,RegisterPageUI.EMAIL_ERROR_MSG);
        return getElementText(driver,RegisterPageUI.EMAIL_ERROR_MSG);
    }

    public String getConfirmPasswordErrorMessageText() {
        waitForElementVisible(driver,RegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
        return getElementText(driver,RegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
    }

    public void enterToEmailTextBox(String s) {
        waitForElementVisible(driver,RegisterPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver,RegisterPageUI.EMAIL_TEXTBOX,s);
    }

    public void enterToFirstNameTextBox(String automation) {
        waitForElementVisible(driver,RegisterPageUI.FIRSTNAME_TEXTBOX);
        sendKeyToElement(driver,RegisterPageUI.FIRSTNAME_TEXTBOX,automation);
    }

    public void enterToLastNameTextBox(String fc) {
        waitForElementVisible(driver,RegisterPageUI.LASTNAME_TEXTBOX);
        sendKeyToElement(driver,RegisterPageUI.LASTNAME_TEXTBOX,fc);
    }

    public void enterToPasswordTextBox(String number) {
        waitForElementVisible(driver,RegisterPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver,RegisterPageUI.PASSWORD_TEXTBOX,number);
    }

    public void enterToConfirmPasswordTextBox(String number) {
        waitForElementVisible(driver,RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeyToElement(driver,RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX,number);
    }

    public String getRegisterSuccessMessageText() {
        waitForElementVisible(driver,RegisterPageUI.REGISTRATION_COMPLETED_MSG);
        return getElementText(driver,RegisterPageUI.REGISTRATION_COMPLETED_MSG);
    }

    public void clickToLogoutLink() {
        waitForElementClickable(driver,RegisterPageUI.LOGOUT_BUTTON);
        clickToElement(driver, RegisterPageUI.LOGOUT_BUTTON);
    }

    public String getPasswordValidationErrorText() {
        waitForElementVisible(driver,RegisterPageUI.PASSWORD_ERROR_MSG);
        return getElementText(driver,RegisterPageUI.PASSWORD_ERROR_MSG);
    }

    public String getEmailExistingMessageErrorText() {
        waitForElementVisible(driver,RegisterPageUI.EMAIL_EXITING_ERROR_MSG);
        return getElementText(driver,RegisterPageUI.EMAIL_EXITING_ERROR_MSG);
    }


}
