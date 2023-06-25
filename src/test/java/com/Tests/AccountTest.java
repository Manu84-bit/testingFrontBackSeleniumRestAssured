package com.Tests;

import static org.junit.jupiter.api.Assertions.*;

import com.Base.BasePage;
import com.Pages.AccountPage;
import com.Pages.HomePage;
import com.Pages.RegisterPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.extent.ExtentFactory;
import com.model.MockUserModel;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountTest {

	private static HomePage homePage;
	private static RegisterPage registerPage;
	private MockUserModel mockUserData = new MockUserModel(
			"Lionel",
			"Messi",
			"messi@mail2.com",
			"Miami",
			"Florida",
			"1111",
			"555523234",
			"1234",
			"4321",
			"messilionel"
	);
	private static ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark1.html");
	private static ExtentReports extent;
	private ExtentTest test;

	@BeforeAll
	public static void setUp() {
		extent = ExtentFactory.getInstance();
		extent.attachReporter(spark);

	}
	@Test
	@Tag("Nivel_1")
	@Tag("Forma_registro")
	@Order(1)
	void goToRegistrationFormTest() {
		homePage = HomePage.createHomePage();
		test = extent.createTest("Ingresar al formulario de registro");
		test.log(Status.INFO, "Inicio de primer test...");
		homePage.open();
		homePage.maximizePage();
		homePage.clickRegisterBtn();
		homePage.waitVisibilityBy(RegisterPage.getFirstNameInput());
		String registerPath = "/register";
		assertTrue(homePage.getCurrentURL().contains(registerPath));
		test.log(Status.PASS, "Ingreso a página de registro");
		System.out.println(homePage.getCurrentURL());
		test.log(Status.INFO, "Final del primer test...");
		homePage.close();

	}

	@Test
	@Tag("Nivel_2")
	@Tag("Registro_usuario")
	@Order(2)
	void registerNewUser(){
		test = extent.createTest("Registrar usuario");
		test.log(Status.INFO, "Inicio de test...");
		registerPage = RegisterPage.createRegisterPage();
		registerPage.open();
		registerPage.maximizePage();
		registerPage.fillRegistrationForm(mockUserData);
		test.log(Status.PASS, "Los datos ingresados en los campos son visibles para el usuario");

		registerPage.clickButton(RegisterPage.getRegisterBtn());
		test.log(Status.PASS,
				"Se envían en los datos del usuario");
		String expectedText = "Your account was created successfully. You are now logged in.";
		registerPage.waitVisibilityBy(By.xpath("//div[@id='rightPanel']//p"));
		WebElement resultEl = registerPage.getWebElementBY(By.xpath("//div[@id='rightPanel']//p"));
//
		assertTrue(registerPage.isTextPresentInElement(expectedText, resultEl));
		test.log(Status.PASS,"Se crea la cuenta y aparece texto de felicitación eperado");
		test.log(Status.INFO,"Fin del segundo test");

	}

	@Test
	@Tag("Nivel_3")
	@Tag("Abrir_cuenta")
	@Order(3)
	void openNewSavingsAccount() throws InterruptedException {
		test = extent.createTest("Abrir cuenta de ahorros");
		test.log(Status.INFO, "Inicio de test...");
		AccountPage accountPage = AccountPage.createAccountPage();
		accountPage.open();
		accountPage.maximizePage();
		test.log(Status.INFO, "Login a la cuenta...");
		accountPage.loginToAccount(mockUserData.getUsername(), mockUserData.getPassword());
		accountPage.clickToCreateNewAccount();
		accountPage.selectSavingsAccount();
		accountPage.confirmAccountCreation();
		test.log(Status.PASS,
				"Se envían en los datos de apertura de cuenta");

		String expectedText = "Congratulations, your account is now open.";
		accountPage.waitVisibilityBy(By.xpath("//div[@ng-if='showResult']//p"));
		WebElement resultEl = accountPage.getWebElementBY(By.xpath("//div[@ng-if='showResult']//p"));

		assertTrue(accountPage.isTextPresentInElement(expectedText, resultEl));
		test.log(Status.PASS,"Se abre la cuenta y aparece el número de cuenta.");
		test.log(Status.INFO,"Fin del tercer test");
		accountPage.close();
	}

	@Test
	@Tag("Nivel_4")
	@Tag("Resumen_cuenta")
	@Order(4)
	void accountOverview() {
		test = extent.createTest("Resumen de cuenta");
		test.log(Status.INFO, "Inicio de test...");
		AccountPage accountPage = AccountPage.createAccountPage();
		accountPage.open();
		accountPage.maximizePage();
		test.log(Status.INFO, "Login a la cuenta...");
		accountPage.loginToAccount(mockUserData.getUsername(), mockUserData.getPassword());
		accountPage.clickToOverviewAccount();

		String expectedText = "*Balance includes deposits that may be subject to holds";
		accountPage.waitVisibilityBy(By.xpath("//td[@colspan='3']"));
		WebElement resultEl = accountPage.getWebElementBY(By.xpath("//td[@colspan='3']"));

		assertTrue(accountPage.isTextPresentInElement(expectedText, resultEl));
		test.log(Status.PASS,
				"Se presenta el resumen de cuenta.");
		test.log(Status.INFO,"Fin del cuarto test.");
		accountPage.close();

	}

	@Test
	@Tag("Nivel_5")
	@Tag("Transferencia")
	@Order(5)
	void transferFunds() throws InterruptedException {
		test = extent.createTest("Transferencia de fondos");
		test.log(Status.INFO, "Inicio de test...");
		AccountPage accountPage = AccountPage.createAccountPage();
		accountPage.open();
		accountPage.maximizePage();
		test.log(Status.INFO, "Login a la cuenta...");
		accountPage.loginToAccount(mockUserData.getUsername(), mockUserData.getPassword());
		test.log(Status.INFO, "Menu de transferencia...");
		accountPage.clickToTransferFunds();
		accountPage.transferFromTo(500);

		String expectedText = "Transfer Complete!";
		Thread.sleep(2000);
		accountPage.waitVisibilityBy(By.xpath("//h1[@class='title']"));
		WebElement resultEl = accountPage.getWebElementBY(By.xpath("//h1[@class='title']"));
		System.out.println(resultEl.getText());

		assertTrue(accountPage.isTextPresentInElement(expectedText, resultEl));
		test.log(Status.PASS,
				"Se presenta el resumen de cuentas.");
		test.log(Status.INFO,"Fin del quinto test.");
		accountPage.close();

	}

	@Test
	@Tag("Nivel_6")
	@Tag("Actividad_mes")
	@Order(6)
	void monthlyActivity() throws InterruptedException {
		test = extent.createTest("Actividad de cuenta");
		test.log(Status.INFO, "Inicio de test...");
		AccountPage accountPage = AccountPage.createAccountPage();
		accountPage.open();
		accountPage.maximizePage();
		test.log(Status.INFO, "Login a la cuenta...");
		accountPage.loginToAccount(mockUserData.getUsername(), mockUserData.getPassword());
		accountPage.clickToOverviewAccount();

		String expectedText = "*Balance includes deposits that may be subject to holds";
		accountPage.waitVisibilityBy(By.xpath("//td[@colspan='3']"));
		WebElement resultEl = accountPage.getWebElementBY(By.xpath("//td[@colspan='3']"));
		assertTrue(accountPage.isTextPresentInElement(expectedText, resultEl));
		test.log(Status.PASS, "Se comprueba texto de balance");


		accountPage.clickButton(By.xpath("//tr[@class='ng-scope']//td//a"));
		test.log(Status.INFO, "Click a balance de cuenta...");

		String expectedText2 = "Account Details";
		accountPage.waitVisibilityBy(By.xpath("//h1[@class='title']"));
		WebElement resultEl2 = accountPage.getWebElementBY(By.xpath("//h1[@class='title']"));

		assertTrue(accountPage.isTextPresentInElement(expectedText2, resultEl2));

		test.log(Status.PASS, "Se comprueba texto de detalles de cuenta");
		test.log(Status.INFO, "Selección de periodo y tipo de actividad");
		accountPage.clickButton(By.xpath("//input[@type='submit']"));
		test.log(Status.PASS,
				"Se presenta el resumen de movimientos.");
		test.log(Status.INFO,"Fin del sexto test.");
		accountPage.close();
	}


	@AfterAll
	public static void finishTests(){
		extent.flush();
		registerPage.close();
	}
}
