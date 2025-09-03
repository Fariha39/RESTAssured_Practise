import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class userController {
    Properties prop;
    public  userController(Properties prop){
        this.prop=prop;
    }
    public Response doLogin(){
        RestAssured.baseURI=prop.getProperty("baseurl");
       return given().contentType("application/json")
                .body("{\n" +
                        "    \"email\":\"admin@roadtocareer.net\",\n" +
                        "    \"password\":\"1234\"\n" +
                        "}")
                .when().post("/user/login");
    }
}
