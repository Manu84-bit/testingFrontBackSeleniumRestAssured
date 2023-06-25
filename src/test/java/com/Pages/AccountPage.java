package com.Pages;

import com.Base.BasePage;
import com.model.MockUserModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class AccountPage extends BasePage {


    private static By usernameInput = By.xpath("//input[@name='username']");
    private static By passInput = By.xpath("//input[@name='password']");
    private static By LoginBtn = By.xpath("//input[@value='Log In']");

    private static By amountInput = By.xpath("//input[@id='amount']");
    private static By selectFrom = By.xpath("//select[@id='fromAccountId']");
    private static By selectTo = By.xpath("//select[@id='toAccountId']");

    private static By newAccountBtn = By.partialLinkText("Open New Account");
    private static By confirmAccountBtn =  By.xpath("//input[@type='submit']");

    private static By overviewBtn = By.partialLinkText("Accounts Overview");
    private static By transferBtn = By.partialLinkText("Transfer Funds");

    private static By selectAccountType = By.xpath("//select[@id='type']");
    private static By savingsOption = By.xpath("//option[@value='1']");


    public AccountPage(WebDriver driver, String URL) {
        super(driver, URL);
    }

    public static AccountPage createAccountPage(){
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver= new ChromeDriver(options);
        URL = "https://parabank.parasoft.com/parabank/index.htm";
        AccountPage registerPage = new AccountPage(driver, URL);

        return registerPage;
    }

    public void loginToAccount(String username, String password){
        enterTextInInput(username, usernameInput);
        enterTextInInput(password, passInput);
        clickButton(LoginBtn);
    }

    public void transferFromTo(int amount) throws InterruptedException {
        clickButton(selectFrom);
        clickButton(By.xpath("//select[@id='fromAccountId']//option[@value='39984']"));
        clickButton(selectTo);
        clickButton(By.xpath("//select[@id='toAccountId']//option[@value='27885']"));
        enterTextInInput(String.valueOf(amount), amountInput);
        clickButton(confirmAccountBtn);
    }

    public void clickToCreateNewAccount(){
        clickButton(newAccountBtn);
    }

    public void clickToOverviewAccount(){
        clickButton(overviewBtn);
    }
    public void clickToTransferFunds(){
        clickButton(transferBtn);
    }

    public void selectSavingsAccount(){
        clickButton(selectAccountType);
        clickButton(savingsOption);
    }

    public void confirmAccountCreation() throws InterruptedException {
        for(int i = 0 ; i< 2; i++){
            clickButton(confirmAccountBtn);
            Thread.sleep(2000);
        }
    }

}
