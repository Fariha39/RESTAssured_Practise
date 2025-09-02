import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class RESTAssured {
    Properties prop;
    public  RESTAssured() throws IOException {
         prop =new Properties();
        FileInputStream fs = new FileInputStream("./src/test/resources/config.properties");
        prop.load(fs);


    }
    @Test
    public void doLogin() throws ConfigurationException {
        RestAssured.baseURI=prop.getProperty("baseurl");
       Response res= given().contentType("application/json")
                .body("{\n" +
                        "    \"email\":\"Admin@roadtocaReer.net\",\n" +
                        "    \"password\":\"1234\"\n" +
                        "}")
                .when().post("/user/login");
       // System.out.println(res.asString());
        JsonPath jsonObj =res.jsonPath();
        String token =jsonObj.get("token");
        System.out.println(token);
 utils.setEnvVar("token",token);

    }
@Test
    public  void searchUser(){
        RestAssured.baseURI=prop.getProperty("baseurl"); //config file a baseurl set kore just get korbo
        Response res =given().contentType("application/json")
                .header("Authorization","bearer "+prop.getProperty("token")) //bearer er pore space dite hobe nahole token expired dekhabe
                .when().get("/user/search/id/28594");
        //System.out.println(prop.getProperty("token"));
    System.out.println(res.asString());
    JsonPath jsonobj =res.jsonPath();
    String id =jsonobj.get("user.id").toString(); //config file sob data by default as string hisebei store hoi
    System.out.println(id);


    }
}
