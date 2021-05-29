package pageObjects;

import org.openqa.selenium.By;

public class Cart extends BasePageObject {

  public void confirmCheckoutPopUp() {
    getClickableElement(By.xpath("//button[normalize-space()='Checkout']")).click();
  }

  public void getToCart() {
    getClickableElement(By.xpath("//button[normalize-space()='View Bag (1)']")).click();
  }

  public String getProductName(){
    return getClickableElement(By.cssSelector("[data-automation=\"cart-item-product-name\"]")).getText();
  }

  public String getProductSize(){
    return getClickableElement(By.cssSelector("[data-automation=\"size-select\"]")).getText();
  }

  public void pressCheckOut() {
    getClickableElement(By.xpath("(//*[@class=\"norecs-container\"]//button)[2]")).click();
  }

  public void guestCheckout() {
    getClickableElement(By.id("qa-guest-checkout")).click();
  }

  public void clickOnTheCartIcon() {
    getClickableElement(By.cssSelector("[href=\"https://www.nike.com/cart\"]")).click();
  }
}
