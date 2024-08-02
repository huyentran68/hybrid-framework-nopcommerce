package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.CustomerPageUI;

public class CustomerPageObject extends BasePage {
    WebDriver driver;
    public CustomerPageObject(WebDriver driver) {
        this.driver = driver;
    }
    public String getFirstNameTextBoxAttributeValue() {
        waitForElementVisible(driver, CustomerPageUI.FIRSTNAME_TEXTBOX_ATTRIBUTE_VALUE);
        return getElementAttribute(driver, CustomerPageUI.FIRSTNAME_TEXTBOX_ATTRIBUTE_VALUE,"value");
    }

    public String getLastNameTextBoxAttributeValue() {
        waitForElementVisible(driver, CustomerPageUI.LASTNAME_TEXTBOX_ATTRIBUTE_VALUE);
        return getElementAttribute(driver, CustomerPageUI.LASTNAME_TEXTBOX_ATTRIBUTE_VALUE,"value");
    }

    public String getEmailTextBoxAttributeValue() {
        waitForElementVisible(driver, CustomerPageUI.EMAIL_TEXTBOX_ATTRIBUTE_VALUE);
        return getElementAttribute(driver, CustomerPageUI.EMAIL_TEXTBOX_ATTRIBUTE_VALUE,"value");
    }
}
