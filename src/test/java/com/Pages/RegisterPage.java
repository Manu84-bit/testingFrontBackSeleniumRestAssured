package com.Pages;

import com.Base.BasePage;
import com.model.MockUserModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class RegisterPage extends BasePage {

    private static By firstNameInput = By.xpath("//input[@name='customer.firstName']");
    private static By lastNameInput = By.xpath("//input[@name='customer.lastName']");
    private static By addressInput = By.xpath("//input[@name='customer.address.street']");
    private static By cityInput = By.xpath("//input[@name='customer.address.city']");
    private static By stateInput = By.xpath("//input[@name='customer.address.state']");
    private static By zipInput = By.xpath("//input[@name='customer.address.zipCode']");
    private static By phoneInput = By.xpath("//input[@name='customer.phoneNumber']");
    private static By ssnInput = By.xpath("//input[@name='customer.ssn']");
    private static By usernameInput = By.xpath("//input[@name='customer.username']");
    private static By passInput = By.xpath("//input[@name='customer.password']");
    private static By confirmPassInput = By.xpath("//input[@name='repeatedPassword']");

    private static By registerBtn = By.xpath("//input[@value='Register']");


    public RegisterPage(WebDriver driver, String URL) {
        super(driver, URL);
    }

    public static RegisterPage createRegisterPage(){
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver= new ChromeDriver(options);
        URL = "https://parabank.parasoft.com/parabank/register.htm";
        RegisterPage registerPage = new RegisterPage(driver, URL);
        return registerPage;
    }

    public void fillRegistrationForm(MockUserModel userData){
        enterTextInInput(userData.getFirstName(), firstNameInput);
        enterTextInInput(userData.getLastName(), lastNameInput);
        enterTextInInput(userData.getAddress(), addressInput);
        enterTextInInput(userData.getCity(), cityInput);
        enterTextInInput(userData.getState(), stateInput);
        enterTextInInput(userData.getZipInput(), zipInput);
        enterTextInInput(userData.getTelephone(), phoneInput);
        enterTextInInput(userData.getSsn(), ssnInput);

        enterTextInInput(userData.getUsername(), usernameInput);

        enterTextInInput(userData.getPassword(), passInput);
        enterTextInInput(userData.getPassword(), confirmPassInput);
    }

    public static By getFirstNameInput() {
        return firstNameInput;
    }

    public static By getRegisterBtn() {
        return registerBtn;
    }

    public static By getConfirmPassInput() {
        return confirmPassInput;
    }
}
