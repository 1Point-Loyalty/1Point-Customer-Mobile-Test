import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumTest {
    private AppiumDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Pixel7Pro");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "ca.one_point.customer");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appActivity", "ca.one_point.customer.MainActivity");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/"), capabilities);
    }

    @Test
    public void sampleTest() {
        WebElement nameField = driver.findElement(By.xpath("//android.widget.EditText[@text='Full Name']"));
        WebElement phoneNumberField = driver.findElement(By.xpath("//android.widget.EditText[@text='Phone Number']"));
        WebElement emailField = driver.findElement(By.xpath("//android.widget.EditText[@text='Email']"));
        WebElement paswswordField = driver.findElement(By.xpath("//android.widget.EditText[@text='Password']"));

        nameField.sendKeys("Test User");
        phoneNumberField.sendKeys("6475500123");
        emailField.sendKeys("test@gmail.com");
        paswswordField.sendKeys("password");

        WebElement loginBtn = driver.findElement(By.xpath("//android.widget.TextView[@text='Log in']"));

        loginBtn.click();
        WebElement shawermaPlusText = driver.findElement(
                By.xpath("//android.widget.TextView[@text='Shawerma Plus has joined 1Point!']"));

        shawermaPlusText.isDisplayed();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}