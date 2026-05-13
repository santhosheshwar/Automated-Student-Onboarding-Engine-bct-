package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {

    WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean searchById(String id) {

        driver.get("http://localhost:5173/search");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='number']"))
        );

        input.clear();
        input.sendKeys(id);

        driver.findElement(By.tagName("button")).click();

        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Name:')]"))
        );

        return result.isDisplayed();
    }
}
