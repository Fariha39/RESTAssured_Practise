package controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import setUp.userModel;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class userController {
    Properties prop;
    public  userController(Properties prop){
        this.prop=prop;
    }
    public Response doLogin(userModel userModel){
        RestAssured.baseURI=prop.getProperty("baseurl");
       return given().contentType("application/json")
                .body(userModel)
                .when().post("/user/login");
    }
    public Response createUser(userModel userModel){
        RestAssured.baseURI=prop.getProperty("baseurl");
        return given().contentType("application/json")
                .header("Authorization","bearer "+prop.getProperty("token"))
                .header("X-AUTH-SECRET-KEY",prop.getProperty("secretkey"))
                .body(userModel)
                .when().post("/user/create");
    }
    public Response searchUser(String userId){
        RestAssured.baseURI=prop.getProperty("baseurl");
      return  given().contentType("application/json")
                .header("Authorization","bearer "+prop.getProperty("token"))
                .header("X-AUTH-SECRET-KEY",prop.getProperty("secretkey"))
                .when().get("/user/search/id/"+userId);
    }
    public Response deleteUser(String userId){
        RestAssured.baseURI=prop.getProperty("baseurl");
        return given().contentType("application/json")
                .header("Authorization","bearer "+prop.getProperty("token"))
                .header("X-AUTH-SECRET-KEY",prop.getProperty("secretkey"))
                .when().delete("/user/delete/"+userId);
    }
    }

