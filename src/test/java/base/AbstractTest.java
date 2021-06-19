package base;


import constants.*;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public abstract class AbstractTest {
    public WebDriver driver;

    @Before
    public void setupDriver() {
        //create
        System.setProperty("webdriver.chrome.driver", "src//test//resources//chromedriver 2");
        driver = new ChromeDriver();
        driver.get(Constants.URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void quitDriver() {
        driver.close();
        driver.quit();
    }
}
