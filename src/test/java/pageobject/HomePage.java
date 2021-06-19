package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

public class HomePage extends PageObject {
    WebDriverWait wait = new WebDriverWait(driver, 20);
    @FindBy(xpath = "(//div[contains(@class,'gekhq4-4 egoSnI')])[1]")
    private WebElement signbutton;
    @FindBy(xpath = "//a[contains(@href,'https://www.gittigidiyor.com/uye-girisi')]")
    private WebElement signbuttonlink;
    @FindBy(css = "[data-cy='header-search-input']")
    private WebElement inputproduct;
    @FindBy(xpath = "//a//div[contains(text(),'Bilgisayar, Tablet / Dizüstü Bilgisayar (Laptop)')]")
    private WebElement chooseProduct;
    @FindBy(xpath = "//span[text()='BUL']")
    private WebElement findButton;
    @FindBy(xpath = "((//div[contains(@class,'cell-border')])[2]//div)[2]")
    private WebElement clickProduct;
    @FindBy(id = "add-to-basket")
    private WebElement addBasket;
    @FindBy(id = "sp-price-highPrice")
    private WebElement priceText;
    @FindBy(xpath = "//div[@class='total-price']//strong")
    private WebElement priceBasket;
    @FindBy(xpath = "(//a[@href='https://www.gittigidiyor.com/sepetim'])[1]")
    private WebElement openBasket;
    @FindBy(xpath = "//*[contains(text(),'Sepete Git')]")
    private WebElement goBasket;
    @FindBy(xpath = "(//i[@class='gg-icon gg-icon-bin-medium'])[1]")
    private WebElement deleteBasket;
    @FindBy(xpath = "//div[contains(text(),'Ürün Toplamı (0 Adet)')]")
    private WebElement deleteInfo;

    @FindBy(xpath = "//span[contains(text(),'Sepet')]")
    private WebElement basket;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void moveToSignButton() {
        Actions actions = new Actions(driver);
        actions.moveToElement(signbutton);
        signbutton.click();
    }

    public void clickSignLink() {
        signbuttonlink.click();
    }
    public void acceptCookies()
    {
        WebElement cookie=driver.findElement(By.xpath("//span[text()='Kapat']"));
        wait.until(ExpectedConditions.elementToBeClickable(cookie)).click();
    }
    public void findProduct(String keyword) {
         acceptCookies();
        wait.until(ExpectedConditions.elementToBeClickable(inputproduct));
        inputproduct.sendKeys(keyword);
        chooseProduct.click();
        wait.until(ExpectedConditions.elementToBeClickable(clickProduct));
       // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clickProduct);
        clickProduct.click();
    }


    public String basketPrice() {
        String basketPrice = priceBasket.getText();
        return basketPrice;
    }

    public void addBasket() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addBasket);
        wait.until(ExpectedConditions.elementToBeClickable(addBasket)).click();
        sleep(2000);
        if (goBasket.isDisplayed()) {
            wait.until(ExpectedConditions.elementToBeClickable(goBasket)).click();
        } else {
            wait.until(ExpectedConditions.elementToBeClickable((basket))).click();

        }
        String basketPrice = basketPrice();
        System.out.println("Ürünün sepetteki fiyatı: "+basketPrice);

    }

    public void deleteBasket() {
        int xpathCount = driver.findElements(By.xpath("//i[@class='gg-icon gg-icon-bin-medium']")).size();
        if(xpathCount>0) {
            for (int i = 0; i < xpathCount; i++) {
                try {
                    deleteBasket.click();
                }
                catch(org.openqa.selenium.StaleElementReferenceException ex)
                {
                    //ex.printStackTrace();
                }
            }
        }
        sleep(2000);
       if(deleteInfo.isDisplayed())
       {
            System.out.println("Sepetteki ürünler temizlendi");
       }
       else
       {
           System.out.println("Sepetteki ürünler temizlenmedi");
       }
    }

    public void sleep(int sleepMs)
    {
        try{
            Thread.sleep(sleepMs);
        }
        catch(InterruptedException ie){
        }
    }

}




