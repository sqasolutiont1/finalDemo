package pageObjects;

import org.openqa.selenium.By;

public class NewReleases extends BasePageObject {

  public void pickTheTheProduct(String productName) {
    getClickableElement(By.xpath(
        "//*[@class=\"product-card__link-overlay\"]/../*[contains(normalize-space(),'" + productName
            + "')]/../../..")).click();
  }
}
