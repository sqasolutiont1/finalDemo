package pageObjects;

import com.paulhammant.ngwebdriver.NgWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject{
  public static WebDriver webDriver;
  public BasePageObject(){
    if (webDriver==null){
      webDriver = new BrowserSetup().setup();
    }
  }

  public void sendTextWithClear(By Locator, String text) {
    WebElement element = getClickableElement(Locator);
    element.clear();
    element.sendKeys(text);
  }

  public WebElement getClickableElement(By locator) {
    new NgWebDriver((JavascriptExecutor) webDriver).waitForAngularRequestsToFinish();
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return new FluentWait<>(webDriver)
        .withTimeout(Duration.ofSeconds(10))
        .pollingEvery(Duration.ofMillis(50))
        .ignoring(NoSuchElementException.class)
        .until(ExpectedConditions.elementToBeClickable(locator));
  }

  public void clickJSE(By locator) {
    WebElement element = getClickableElement(locator);
    JavascriptExecutor executor = (JavascriptExecutor) webDriver;
    executor.executeScript("arguments[0].click();", element);
  }

  public List<WebElement> getClickableElements(By locator) {
    return new FluentWait<>(webDriver)
        .withTimeout(Duration.ofSeconds(30))
        .pollingEvery(Duration.ofMillis(50))
        .ignoring(NoSuchElementException.class)
        .until(driver -> driver.findElements(locator));
  }

  public Boolean isAllImagesLoaded(WebElement element) {
    Object tmp = ((JavascriptExecutor) webDriver)
        .executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != " +
            "\"undefined\" && arguments[0].naturalWidth > 0", element);
    return true;
  }

  public Boolean waitForElementToBeDisplayed(By locator) {
    return new FluentWait<>(webDriver)
        .withTimeout(Duration.ofSeconds(30))
        .pollingEvery(Duration.ofMillis(50))
        .ignoring(NoSuchElementException.class)
        .until(driver -> driver.findElement(locator).isDisplayed());
  }

  public Boolean waitForElementToBeNotVisible(By locator) {
    return new FluentWait<>(webDriver)
        .withTimeout(Duration.ofSeconds(30))
        .pollingEvery(Duration.ofMillis(50))
        .ignoring(NoSuchElementException.class)
        .until(ExpectedConditions.invisibilityOf(webDriver.findElement(locator)));
  }

  public ExpectedCondition<Boolean> isElementNotVisible(By locator) {
    return new WebDriverWait(webDriver, 5)
        .until(webDriver -> ExpectedConditions.invisibilityOf(webDriver.findElement(locator)));
  }


  public void waitForAttributeValue(By locator, String attributeName, String value) {
    new FluentWait<>(webDriver)
        .withTimeout(Duration.ofSeconds(30))
        .pollingEvery(Duration.ofMillis(50))
        .ignoring(NoSuchElementException.class)
        .until(ExpectedConditions.attributeContains(locator, attributeName, value));
  }

  public void checkTheCheckBox(boolean isShouldBeChecked, By locator, String attributeName,
      String condition) {
    /**
     * 1. add verification if condition of the object was changed after the click action
     */
    WebElement checkBox = getClickableElement(locator);
    if (!checkBox.getAttribute(attributeName).contains(condition) && isShouldBeChecked) {
      checkBox.click();
    } else if (checkBox.getAttribute(attributeName).contains(condition) && !isShouldBeChecked) {
      checkBox.click();
    }
  }

  public void waitForPageToBeLoaded() {
    new NgWebDriver((JavascriptExecutor) webDriver).waitForAngularRequestsToFinish();
        /*final String javaScriptToLoadAngular =
                "var injector = window.angular.element('body').injector();" +
                        "var $http = injector.get('$http');" +
                        "return ($http.pendingRequests.length === 0)";

        ExpectedCondition<Boolean> pendingHttpCallsCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript(javaScriptToLoadAngular).equals(true);
            }
        };
        new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(50))
                .until(pendingHttpCallsCondition);*/
  }
}
