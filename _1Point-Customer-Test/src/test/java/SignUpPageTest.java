
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SignUpPageTest {
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

        @Test(priority = 1)
        public void errorHandlingAll() {

                WebElement signUpBtn = driver.findElementByAccessibilityId(
                                "signup button");
                signUpBtn.click();

                WebElement nameFieldError = driver.findElement(
                                By.xpath(
                                                "//android.widget.TextView[@text='Please enter a valid first and last name']"));
                nameFieldError.isDisplayed();

                WebElement phoneNumberFieldError = driver.findElementByAccessibilityId("Telephone input");
                phoneNumberFieldError.isDisplayed();

                WebElement emailFieldError = driver.findElement(
                                By.xpath(
                                                "//android.widget.TextView[@text='Please enter a valid email address']"));
                emailFieldError.isDisplayed();

                WebElement passwordFieldError = driver.findElement(
                                By.xpath(
                                                "//android.widget.TextView[@text='Password must be at least 8 characters long']"));
                passwordFieldError.isDisplayed();
        }

        @Test(priority = 2)
        public void errorHandlingNameInvalid() {
                WebElement nameField = driver.findElementByAccessibilityId("name input");

                nameField.sendKeys("T A");

                WebElement signUpBtn = driver.findElementByAccessibilityId(
                                "signup button");
                signUpBtn.click();

                WebElement nameFieldError = driver.findElement(
                                By.xpath(
                                                "//android.widget.TextView[@text='Please enter a valid first and last name']"));
                nameFieldError.isDisplayed();
        }

        @Test(priority = 3)
        public void errorHandlingNameValid() {
                WebElement nameField = driver.findElementByAccessibilityId("name input");

                nameField.sendKeys("Test User");

                WebElement signUpBtn = driver.findElementByAccessibilityId(
                                "signup button");
                signUpBtn.click();

                int nameFieldErrors = driver.findElements(
                                By.xpath(
                                                "//android.widget.TextView[@text='Please enter a valid first and last name']"))
                                .size();

                assert nameFieldErrors == 0;
        }

        @Test(priority = 4)
        public void errorHandlingEmailInvalid() {
                WebElement emailField = driver.findElementByAccessibilityId("email input");

                emailField.sendKeys("test");

                WebElement signUpBtn = driver.findElementByAccessibilityId(
                                "signup button");
                signUpBtn.click();

                WebElement emailFieldError = driver.findElement(
                                By.xpath(
                                                "//android.widget.TextView[@text='Please enter a valid email address']"));
                emailFieldError.isDisplayed();
        }

        @Test(priority = 5)

        @AfterClass
        public void tearDown() {
                if (driver != null) {
                        driver.quit();
                }
        }
}