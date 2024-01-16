package testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testbase.TestBase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductsAssertionTest extends TestBase {
    static ValidatableResponse response;
    @BeforeClass
    public static void inIT() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                . when()
                .get("/products")
                .then().statusCode(200);
    }



    //1. Verify the if the total is equal to 51957
    @Test
    public void test01(){
        response.body("total", equalTo(51957));
    }
    //2.Verify the if the stores of limit is equal to 10
    @Test
    public void test02(){
        response.body("limit", equalTo(10));
    }
    //3. Check the single ‘Name’ in the Array list (Duracell - AAA Batteries (4-Pack))
    @Test
    public void Test03(){
        response.body("data.name",hasItem("Duracell - AAA Batteries (4-Pack)"));
    }
    //4. Check the multiple ‘Names’ in the ArrayList (Duracell - AA 1.5V CopperTop Batteries (4-Pack), Duracell - AA Batteries (8-Pack), Energizer - MAX Batteries AA (4-Pack))
    @Test
    public void Test04(){
        response.body("data.name", hasItems());
    }
    //5.Verify the productId=150115 inside categories of the third name is “Household Batteries”
    @Test
    public void Test05(){
        response.body("data.find{it.id == 150115}.categories[2].name", equalTo("Household Batteries"));
    }
    //6.Verify the categories second name = “Housewares” of productID = 333179
    @Test
    public void Test06(){
        response.body("data.find{it.id == 333179}.categories[1].name",equalTo("Housewares"));
    }
    //7.Verify the price = 4.99 of forth product
    @Test
    public void test07() {
        response.body("data[3].price", equalTo(4.99f));
    }

    //8.Verify the Product name = Duracell - D Batteries (4-Pack) of 6th product
    @Test
    public void test08() {
        response.body("data[5].name", equalTo("Duracell - D Batteries (4-Pack)"));
    }

    //9.Verify the ProductId = 333179 for the 9th product
    @Test
    public void test09() {
        response.body("data[8].id", equalTo(333179));
    }

    //10.Verify the productId = 346575 have 5 categories
    @Test
    public void test10() {
        response.body("data.find{it.id == 346575}.categories.size()", equalTo(5));
    }
}

