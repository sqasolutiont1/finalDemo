package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.Cart;
import pageObjects.CheckOutProcess;
import pageObjects.HomePage;
import pageObjects.NewReleases;
import pageObjects.Product;
import pageObjects.dictionary.MainMenuItems;

public class Test_Nike{
  HomePage homePage = new HomePage();
  NewReleases newReleases = new NewReleases();
  Product product = new Product();
  CheckOutProcess checkOutProcess = new CheckOutProcess();
  Cart cart = new Cart();

  @DataProvider(name = "test1")
  public Object[][] createData1() {
    return new Object[][] {
        { "Nike Air Max 270","6" },
        { "Air Jordan 1 Mid", "M 9 / W 10.5"},
    };
  }


  @Test(dataProvider = "test1")
  public void getIn(String productName, String productSize ) {
    homePage.navigateTo();
    homePage.selectFromTopMenu(MainMenuItems.NewReleases.getItem());
    newReleases.pickTheTheProduct(productName);
    product.selectSize(productSize);
    product.pressAddToBag();
    cart.getToCart();
    //checkOut.clickOnTheCartIcon();
    Assert.assertEquals(cart.getProductName(),productName,"Wrong product name");
    Assert.assertEquals(cart.getProductSize(),productSize,"Wrong product size");
    cart.pressCheckOut();
    checkOutProcess.fillOutFirstName("sdgerg");
    checkOutProcess.fillOutLastName("sdgerg");
    checkOutProcess.fillOutPhoneNumber("2314234323");
    checkOutProcess.fillOutEmail("sdgerg@hotmail.com");
    checkOutProcess.addAddress("GENERAL DELIVERY");
    checkOutProcess.PressSave();
  }
}
