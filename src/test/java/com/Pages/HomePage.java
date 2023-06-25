package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HomePage extends BasePage {


    private By registerLink = By.partialLinkText("Register");


    public HomePage(WebDriver driver, String URL) {
        super(driver, URL);
    }

    public static HomePage createHomePage(){
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver= new ChromeDriver(options);
        URL = "https://parabank.parasoft.com/parabank/index.htm";
        HomePage homePage = new HomePage(driver, URL);
        return homePage;
    }

    public void clickRegisterBtn(){
        clickButton(registerLink);
    }


}



