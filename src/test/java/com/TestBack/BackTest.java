package com.TestBack;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Tag;
import org.testng.annotations.Test;
import org.testng.Assert;
import static io.restassured.RestAssured.given;
public class BackTest {


    private static final String BASE_URL = "https://parabank.parasoft.com/parabank";
    private static final String CUSTOMER_ID = "15320";
    private static final String NEW_ACCOUNT_TYPE = "1";
    private static final String FROM_ACCOUNT_ID = "24222";

    @Test
    @Tag("Backend_1")
    public void loginTest() {
        RestAssured.baseURI = BASE_URL;

        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", "messilionel")
                .formParam("password", "4321")
                .when()
                .post("/login.htm");

        Assert.assertEquals(response.getStatusCode(), 302);
    }

    @Test
    @Tag("Backend_2")
    public void RegisterGet() throws Exception {

        RestAssured.baseURI = BASE_URL;
        given()
                .when()
                .get("/register.htm")
                .then()
                .statusCode(200);
    }

    @Test
    @Tag("Backend_3")
    public void createAccountTest() {
        RestAssured.baseURI = "https://parabank.parasoft.com/parabank/services_proxy/bank";
       Response response = given()
                .auth().basic("messilionel", "4321")
                .contentType("application/json;charset=UTF-8")
               .pathParam("customerId", CUSTOMER_ID)
               .pathParam("newAccountType", NEW_ACCOUNT_TYPE)
               .pathParam("fromAccountId", FROM_ACCOUNT_ID)
               .when()
               .post("/createAccount?customerId={customerId}&newAccountType={newAccountType}&fromAccountId={fromAccountId}");

        Assert.assertEquals(response.getStatusCode(), 200);
    }


    @Test
    @Tag("Backend_4")
    public void getAccountOverviewTest() {
        RestAssured.baseURI = "https://parabank.parasoft.com/parabank/services/bank/customers/12212";

       Response response = given()
                .auth().basic("messilionel", "4321")
                .when()
                .get();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    @Tag("Backend_5")
    public void transferFundsTest() {
        RestAssured.baseURI = "https://parabank.parasoft.com/parabank/services_proxy/bank";
        Response response = given()
                .auth().basic("messilionel", "4321")
                .contentType("application/json;charset=UTF-8")
                .pathParam("fromAccountId", "18117")
                .pathParam("toAccountId", "23445")
                .pathParam("amount", "500")
                .when()
                .post("/transfer?fromAccountId={fromAccountId}&toAccountId={toAccountId}&amount={amount}");

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    @Tag("Backend_6")
    public void accountActivity() {
            RestAssured.baseURI ="https://parabank.parasoft.com/parabank/services_proxy/bank";

         Response response = given()
                    .auth().basic("messilionel", "4321")
                    .pathParam("accountId", "18117")
                    .when()
                    .get("/accounts/{accountId}/transactions/month/All/type/All");
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}