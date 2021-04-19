package com.example.PP_lessonplan_tests;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class AuthTests {
    private WebDriver driver;
    private String login = "jak.kowal@poczta.xd";
    private String password = "password";
    private String badLogin = "jan.kowal@poczta.xd";
    private String badPassword = "badpassword";

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void loginBadCredentials() {
        driver.get("http://derilius.pl/#/login");
        driver.findElement(By.name("username")).sendKeys(badLogin);
        driver.findElement(By.name("password")).sendKeys(badPassword);
        driver.findElement(By.name("login")).click();
        assertTrue(driver.findElement(By.name("password")).isDisplayed());
    }

    @Test
    public void loginCorrectCredentials() {
        driver.get("http://derilius.pl/#/login");
        driver.findElement(By.name("username")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
        Boolean verifyLogin = driver.findElement(By.name("my_plan")).isDisplayed();
        assertTrue(verifyLogin);
    }

    @Test
    public void logout() {
        login();
        driver.findElement(By.name("logout")).click();
        assertTrue(driver.findElement(By.name("password")).isDisplayed());
    }

    public void login() {
        driver.get("http://derilius.pl/#/login");
        driver.findElement(By.name("username")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
    }
}
