import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RESTAssured {
    @Test
    public void doLogin(){
        RestAssured.baseURI="https://dmoney.roadtocareer.net";
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


    }
}
