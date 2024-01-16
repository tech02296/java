package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2";
        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 20
    @Test
    public void Test1() {
        response.body("id.size()", equalTo(20));
    }

    //2. Verify the if the name of id =  2272678 is equal to ”Chandini Prajapat"
    @Test
    public void Test2() {
        response.body("[2].name", equalTo("Chandini Prajapat"));
    }

    //3. Check the single ‘Name’ in the Array list (Bhadrak Singh)
    @Test
    public void Test3() {
        response.body("[5].name", equalTo("Bhadrak Singh"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Tanirika Khan, Kailash Pillai
    //Chidambaram Talwar)
    @Test
    public void Test4() {
        response
                .body("[7].name", equalTo("Tanirika Khan"))
                .body("[6].name", equalTo("Kailash Pillai"))
                .body("[12].name", equalTo("Chidambaram Talwar"));
    }

    //5. Verify the emai of userid = 5471 is equal “prajapat_chandini@fisher.test”
    @Test
    public void Test5(){
        response.body("[2].email", equalTo("prajapat_chandini@fisher.test"));
    }

    //6. Verify the status is “inactive” of user name is “Esha Gupta”
    @Test
    public void Test6(){
        response.body("[10].status", equalTo("inactive"));
    }

    //7. Verify the Gender = female of user name is “Gobinda Singh JD”
    @Test
    public void Test7(){
        response.body("[9].gender", equalTo("female"));
    }

}

