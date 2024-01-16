package testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given().
                when().
                get("/products").
                then().statusCode(200);

    }

    //1.Extract the limit
    @Test
    public void test01() {
        int limit = response.extract().path("limit");
        System.out.println("The value of limit is : " + limit);
    }

    //2. Extract the total
    @Test
    public void test02() {
        int total = response.extract().path("total");
        System.out.println("The value of total is : " + total);

    }

    //3. Extract the name of 5th product
    @Test
    public void test03() {
        String productNameFifth = response.extract().path("data.name[4]");
        System.out.println("Fifth Product Name is : " + productNameFifth);

    }

    //4.Extract the names of all the products
    @Test
    public void test04() {
        List<String> allProductName = response.extract().path("data.name");
        System.out.println("Extract the Name of Products " + allProductName);

    }

    //5.Extract the productId of all the products
    @Test
    public void test05() {
        List<String> allProductName = response.extract().path("data.id");
        System.out.println("Extract the ID of Products " + allProductName);

    }

    //6. Print the size of the data list
    @Test
    public void test06() {
        List<Object> allDataSize = response.extract().path("data");
        System.out.println("Size of Total Size: " + allDataSize.size());
    }

    //7.Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test07() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("All values: " + values);

    }

    //8.Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test08() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("Model Name of Energizer - N Cell E90 Batteries (2-Pack): " + values);
    }

    //9.Get all the categories of 8th products
    @Test
    public void test09() {
        List<String> allCat = response.extract().path("data[7].categories");
        System.out.println("Get All Categories: " + allCat);

    }
    //10. Get categories of the store where product id = 150115
    @Test
    public void test10() {
        List<String> allCat = response.extract().path("data[3].categories");
        System.out.println("Get categories of the store where product id = 150115: " + allCat);

    }

    //  11. Get all the descriptions of all the products
    @Test
    public void test11() {
        List<HashMap<String, ?>> desc = response.extract().path("data.description");
        System.out.println("Get All Descriptions: " + desc);

    }

    //12. Get id of all the all categories of all the products
    @Test
    public void test12() {
        List<HashMap<String, ?>> ids = response.extract().path("data.categories.id");
        System.out.println("Get All Ids: " + ids);
    }


    //13. Find the product names Where type = HardGood
    @Test
    public void test13() {
        List<HashMap<String, ?>> productName = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println("Get All Product Name: " + productName);

    }

    //14. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test //left
    public void test14() {
        List<?> totalService = response.extract().path("data.find{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("Get All Cat Total : " + totalService.size());

    }

    //35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test15() {
        List<String> createdAt = response.extract().path("data.findAll{it.price < 5.49}.createdAt");
        System.out.println("The createdAt for all products whose price < 5.49 : " + createdAt);

    }

    //16. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test16() {
        List<HashMap<String, ?>> allCat = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("Name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)” : " + allCat);
    }


    //17. Find the manufacturer of all the products
    @Test
    public void test17() {
        List<String> allManufacturer = response.extract().path("data.manufacturer");
        System.out.println("Get All Manufacturer Name : " + allManufacturer);
    }



    //18. Find the image of products whose manufacturer is = Energizer
    @Test
    public void test18() {
        List<HashMap<String, ?>> img = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("The image of products whose manufacturer is = Energizer: " + img);

    }

    //19. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test19() {
        List<String> createdAt = response.extract().path("data.findAll{it.price > 5.49}.createdAt");
        System.out.println("Get All Cat Total : " + createdAt);

    }

    //20. Find the uri of all the products
    @Test
    public void test20() {
        List<HashMap<String, ?>> url = response.extract().path("data.url");
        System.out.println("Finding the uri of all the products : " + url);

    }
}






