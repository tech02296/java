package testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testbase.TestBase;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest extends TestBase {
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
    //1. Extract the limit
    @Test
    public void test01(){
        int limit = response.extract().path("limit");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //2.Extract the total
    @Test
    public void test02(){
        int total = response.extract().path("total");

        System.out.println("The value of Total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th store
    @Test
    public void test03(){
        String storeName = response.extract().path("data[4].name");
        System.out.println("storeName"+ storeName);
        System.out.println("------------------End of Test---------------------------");

    }
    //4. Extract the names of all the store
    @Test
    public void test04(){
        List<String>name = response.extract().path("data.name");
        System.out.println("allTheStore" + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the storeId of all the store
    @Test
    public void test05(){
        List<Integer>id = response.extract().path("data.id");
        System.out.println("storeId" + id);
        System.out.println("------------------End of Test---------------------------");
    }
    //6. Print the size of the data list
    @Test
    public void test06(){
        List<?>data = response.extract().path("data");
        System.out.println("dataList" + data);
        System.out.println("------------------End of Test---------------------------");
    }

   //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test07(){
       List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'St Cloud'}");
       // List<String>valueOfTheStore = response.extract().path("data.findall{it.name=='St Cloud'}");
        System.out.println("storeValue:" + values);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void test08() {

        List<String> addressOfRochester = response.extract().path("data.findAll{it.name=='Rochester'}.address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Address of  StoreName : " + addressOfRochester);
        System.out.println("------------------End of Test---------------------------");
    }
    //9. Get all the services of 8th store
    @Test
    public void test09(){
        List<String> allServices = response.extract().path("data[7].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the services of 8th store : " + allServices);
        System.out.println("------------------End of Test---------------------------");
    }
    @Test

    //10. Get storeServices of the store where service name = Windows Store
    public void test10(){
        List<String> storeService = response.extract().path("data.services.findAll{it.name == 'Windows Store'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Store Services : " + storeService);
        System.out.println("------------------End of Test---------------------------");
    }
     //11. Get all the storeId of all the store
    @Test
    public void test11(){
        List<Integer>storeIDAll = response.extract().path("data.services.storeservices.storeId");
        System.out.println("Stores Ids : " + storeIDAll);
        System.out.println("------------------End of Test---------------------------");
    }
    //12. Get id of all the store
    @Test
    public void test12(){
        List<Integer>allId = response.extract().path("data.id");
        System.out.println("The Stores id" + allId);
        System.out.println("------------------End of Test---------------------------");
    }
   // 13. Find the store names Where state = ND
    @Test
    public void test13(){
        List<String>storeName = response.extract().path("data.findAll{it.state =='ND'}.name");
        System.out.println("The Store Name " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }
    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test14(){
        List<Integer> dataSize = response.extract().path("data*.services*.findAll{it.name == 'Rochester'}.data");
        int size =  dataSize.size();
        System.out.println("----------------------StartingTest---------------");
        System.out.println("The Size of the dataList is : " +size );
        System.out.println("--------------------End of Test---------------------");
    }
    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test15(){
        List<String>storeNameWindows = response.extract().path("data.services*.findAll{it.name =='Windows Store'}.createdAt");
        System.out.println("storeName" + storeNameWindows);
        System.out.println("--------------------End of Test---------------------");
    }
    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test16(){
        List<Object> storeName = response.extract().path("data.findAll{it.name =='Fargo'}.services.name");
        System.out.println("all services " + storeName);
        System.out.println("--------------------End of Test---------------------");
    }
    //17. Find the zip of all the store
    @Test
    public void test17(){
        List<Integer>allZip = response.extract().path("data.zip");
        System.out.println("allStore"+allZip);
        System.out.println("--------------------End of Test---------------------");
    }
    //18. Find the zip of store name = Roseville
    @Test
    public void test18(){
        String zipcode = response.extract().path("data[0].zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Zip Number of All Store : " + zipcode);
        System.out.println("------------------End of Test---------------------------");
    }
    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test19 (){
        List<HashMap<String, ?>> storeservices = response.extract().path("data*.services*.findAll{it.name == 'Magnolia Home Theater'}.storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("storeName" + storeservices);
        System.out.println("------------------End of Test---------------------------");
    }
    //20. Find the lat of all the stores
    @Test
    public void test20(){
        List<Integer> latStore = response.extract().path("data.lat");
        System.out.println("latOfStore" + latStore);
        System.out.println("------------------End of Test---------------------------");
    }


    }











