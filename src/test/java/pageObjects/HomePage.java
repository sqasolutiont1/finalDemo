package pageObjects;

import org.openqa.selenium.By;

public class HomePage extends BasePageObject {
  public static String baseURL = "http://www.nike.com";
  public void navigateTo() {
    webDriver.navigate().to(baseURL);
    waitForPageToBeLoaded();
  }

  public void selectFromTopMenu(String menuName) {
    getClickableElement(By.cssSelector("a[aria-label='"+menuName+"']")).click();
  }

}
