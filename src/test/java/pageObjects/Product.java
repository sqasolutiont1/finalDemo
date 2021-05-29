package pageObjects;

import org.openqa.selenium.By;

public class Product extends BasePageObject {

  public void selectSize(String size) {
    getClickableElement(By.xpath("//label[normalize-space()='" + size + "']")).click();
  }

  public void pressAddToBag() {
    getClickableElement(By.xpath("//button[@aria-label='Add to Bag']")).click();
  }

}
