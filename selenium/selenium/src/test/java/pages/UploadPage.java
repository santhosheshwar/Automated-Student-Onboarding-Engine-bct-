package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UploadPage {

    WebDriver driver;

    public UploadPage(WebDriver driver) {
        this.driver = driver;
    }

    public void uploadFile(String filePath) {

        driver.get("http://localhost:5173/upload");

        try { Thread.sleep(2000); } catch (Exception e) {}

        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(filePath);
    }

    public void clickUpload() {
        driver.findElement(By.xpath("//button[text()='Upload']")).click();
    }
}