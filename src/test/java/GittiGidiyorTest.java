import base.*;
import constants.*;
import org.junit.Before;
import org.junit.Test;

import pageobject.*;

public class GittiGidiyorTest extends AbstractTest {
    HomePage homePage;
    LoginPage loginPage;

    @Before
    public void setup()
    {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testHome() throws InterruptedException {
        homePage.moveToSignButton();
        homePage.clickSignLink();
        loginPage.login(Constants.TEST_USER, Constants.TEST_PASSWORD);
        Thread.sleep(6000);
        homePage.findProduct(Constants.COMPUTER);
        homePage.addBasket();
        homePage.deleteBasket();

    }
}
