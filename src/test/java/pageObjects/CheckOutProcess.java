package pageObjects;

import org.openqa.selenium.By;

public class CheckOutProcess extends BasePageObject {

  public void fillOutFirstName(String firstName) {
    getClickableElement(By.id("firstName")).sendKeys(firstName);
  }

  public void fillOutLastName(String lastName) {
    getClickableElement(By.id("lastName")).sendKeys(lastName);
  }

  public void addAddress(String address) {
    getClickableElement(By.id("search-address-input")).sendKeys(address);
    getClickableElement(By.xpath(("(//*[@data-suggestion-type=\"Address\"])[1]"))).click();
  }

  public void fillOutEmail(String email) {
    getClickableElement(By.id("email")).sendKeys(email);
  }

  public void fillOutPhoneNumber(String phoneNumber) {
    getClickableElement(By.id("phoneNumber")).sendKeys(phoneNumber);

  }

  public void PressSave() {
    waitForElementToBeDisplayed(By.id("postalCode"));
    getClickableElement(By.xpath("//button[normalize-space()='Save & Continue']")).click();
  }
}
