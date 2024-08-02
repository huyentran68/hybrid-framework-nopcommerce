package commons;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BasePage {
    public static BasePage getBasePage(){
        return new BasePage();
    }
    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000L);
        } catch (InterruptedException var4) {
            throw new RuntimeException(var4);
        }
    }
    //web browser
    public  void openPageUrl(WebDriver driver, String pageUrl){
        driver.get(pageUrl);
    }
    public  String getTitle(WebDriver driver){
        return  driver.getTitle();
    }
    public String getCurrentUrl(WebDriver driver){
       return driver.getCurrentUrl();
    }
    public  String  getPageSource(WebDriver driver){
       return driver.getPageSource();
    }
    public void backToPage(WebDriver driver){
        driver.navigate().back();
    }
    public void forwardToPage(WebDriver driver){
        driver.navigate().forward();
    }
    public void refreshToPage(WebDriver driver){
        driver.navigate().refresh();
    }
    public Alert waitAlertPresence(WebDriver driver){
       return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.alertIsPresent());
    }
    public void acceptAlert(WebDriver driver){
        waitAlertPresence(driver).accept();
    }
    public void cancelAlert(WebDriver driver){
        waitAlertPresence(driver).dismiss();
    }
    public String getTextAlert(WebDriver driver){
        return waitAlertPresence(driver).getText();
    }
    public void sendKeyAlert(WebDriver driver, String keysToSend){
        waitAlertPresence(driver).sendKeys(keysToSend);
    }
    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }
    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    public By  getByXpath(String locator){
       return By.xpath(locator);
    }
    public WebElement getWebElement(WebDriver driver, String locator){
      return driver.findElement(getByXpath(locator));
    }
    public List<WebElement> getListWebElement(WebDriver driver, String locator){
       return driver.findElements(getByXpath(locator));
    }
    public void clickToElement(WebDriver driver, String locator){
        getWebElement(driver,locator).click();
    }
    public void sendKeyToElement(WebDriver driver, String locator,String valueToSend){
        getWebElement(driver,locator).clear();
        getWebElement(driver,locator).sendKeys(valueToSend);
    }
    public String getElementText(WebDriver driver, String locator){
       return getWebElement(driver,locator).getText();
    }
    public  void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemValue){
        new Select(getWebElement(driver,locator)).selectByVisibleText(itemValue);
    }
    public String getFirstSelectedTextInDefaultDropdown(WebDriver driver, String locator){
        return new Select(getWebElement(driver,locator)).getFirstSelectedOption().getText();
    }
    public Boolean isDefaultDropdownMultiple(WebDriver driver, String locator){
        return new Select(getWebElement(driver,locator)).isMultiple();
    }
    public void selectItemDropdown(WebDriver driver, String parentLocator, String childLocator, String itemTextExpected) {
        getWebElement(driver,parentLocator).click();
        this.sleepInSecond(1);
        List<WebElement> allItems = new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childLocator)));
        Iterator var5 = allItems.iterator();
        while(var5.hasNext()) {
            WebElement item = (WebElement)var5.next();
            if (item.getText().equals(itemTextExpected)) {
                item.click();
                break;
            }
        }

    }
    public String getElementAttribute(WebDriver driver, String locator, String attributeName){
        return getWebElement(driver,locator).getAttribute(attributeName);
    }
    public String getElementCssValue(WebDriver driver, String locator, String cssPropertyName){
        return getWebElement(driver,locator).getCssValue(cssPropertyName);
    }
    public String convertRGBAToHexaColor(WebDriver driver, String locator, String cssPropertyName){
        return Color.fromString(getElementCssValue(driver,locator,"background-color")).asHex();
    }
    public int getListElementsSize(WebDriver driver, String locator){
        return getListWebElement(driver,locator).size();
    }

    /**
     * Apply for Checkbox and Radio button
     * @param driver
     * @param locator
     */
    public void checkToElement(WebDriver driver, String locator){
        if (!getWebElement(driver,locator).isSelected()){
            getWebElement(driver,locator).click();
        }
    }

    /**
     * Only apply for Checkbox
     * @param driver
     * @param locator
     */
    public void uncheckToElement(WebDriver driver, String locator){
        if (getWebElement(driver,locator).isSelected()){
            getWebElement(driver,locator).click();
        }
    }
    public Boolean isElementDisplay(WebDriver driver, String locator){
        return getWebElement(driver,locator).isDisplayed();
    }
    public Boolean isElementSelected(WebDriver driver, String locator){
        return getWebElement(driver,locator).isSelected();
    }
    public Boolean isElementEnable(WebDriver driver, String locator){
        return getWebElement(driver,locator).isEnabled();
    }
    public void switchToIframe(WebDriver driver, String locator){
        new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getByXpath(locator)));
    }
    public void switchToDefaultContent(WebDriver driver){
        driver.switchTo().defaultContent();
    }
    public void hoverToElement(WebDriver driver, String locator){
         new Actions(driver).moveToElement(getWebElement(driver,locator)).perform();
    }
    public void doubleClickToElement(WebDriver driver, String locator){
        new Actions(driver).doubleClick(getWebElement(driver,locator)).perform();
    }
    public void rightClickToElement(WebDriver driver, String locator){
        new Actions(driver).contextClick(getWebElement(driver,locator)).perform();
    }
    public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator){
        new Actions(driver).dragAndDrop(getWebElement(driver,sourceLocator),getWebElement(driver,targetLocator)).perform();
    }
    public void sendKeyBoardToElement(WebDriver driver, String locator, Keys key){
        new Actions(driver).sendKeys(getWebElement(driver, locator),key).perform();
    }
    public void highlightElement(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
        sleepInSecond(3);
    }

    public void scrollToElementOnTopByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void scrollToElementOnDownByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    public void scrollToBottomPageByJS(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getWebElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
    }

    public String getAttributeInDOMByJS(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete " +
                        "&& typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getWebElement(driver, locator));
    }
    public void waitForElementVisible(WebDriver driver, String locator){
        new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }
    public void waitForListElementVisible(WebDriver driver, String locator){
        new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfAllElements(getListWebElement(driver, locator)));
    }
    public void waitForElementInVisible(WebDriver driver, String locator){
        new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }
    public void waitForListElementInVisible(WebDriver driver, String locator){
        new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locator)));
    }
    public void waitForElementClickable(WebDriver driver, String locator){
        new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(getWebElement(driver,locator)));
    }


}
