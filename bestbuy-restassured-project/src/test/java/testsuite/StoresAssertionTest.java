package testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class StoresAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given().
                when().
                get("/stores").
                then().statusCode(200);
    }

    //1. Verify the if the total is equal to 1558
    @Test
    public void test01() {
        response.body("total", equalTo(1558));
    }
    @Test
   //2.Verify the if the stores of limit is equal to 10
    public void test02(){
        response.body("limit",equalTo(10));

    }
    @Test
    //3.Check the single ‘Name’ in the Array list ("Northtown")
    public void test03() {
        response.body("data.name", hasItem("Northtown"));
    }

        @Test
        //.4 Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
    public void test04(){
            response.body("data.name", hasItems("Roseville", "Burnsville", "Maplewood"));

        }

    //Verify the storied=7 inside storeservices of the third store of second services
    @Test

    public void verifyTheStoreIdInsideStoreService() {
        response.body("data[1].services[2].storeservices.storeId", equalTo(8));
    }

    // Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville
    @Test
    public void test06() {
        response.body("data[2].services[0].storeservices", hasKey("createdAt"));
    }

    //Verify the state = MN of forth store
    @Test
    public void test07() {
        response.body("data[3].state", equalTo("MN"));
    }

    // Verify the store name = Rochester of 9th store
    @Test
    public void verifyTheStoreName() {
        response.body("data[4].name", equalTo("St Cloud"));
    }



    // Verify the storeId = 11 for the 6th store
    @Test
    public void verifyTheStoreId() {
        response.body("data[3].id", equalTo(11));
    }

    // Verify the serviceId = 4 for the 7th store of forth service
    @Test
    public void test10() {
        response.body("data[6].services[3].storeservices.serviceId", equalTo(4));
    }
}













