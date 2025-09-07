package testRunner;

import com.github.javafaker.Faker;
import controller.userController;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import setUp.setup;
import setUp.userModel;
import utils.Utils;

public class userTestRunner extends setup {
    @Test(priority = 1,description = "user login")
    public void userLogin() throws ConfigurationException {
        userController userController = new userController(prop);
        userModel userModel= new userModel();
        userModel.setEmail("admin@roadtocareer.net");
        userModel.setPassword("1234");
       Response res =userController.doLogin(userModel);
        System.out.println(res.asString());
        JsonPath jsonobj =res.jsonPath();
        String token =jsonobj.get("token");
        Utils.setEnvVar("token",token);

    }
    @Test(priority = 2,description = "create user")
    public void userCreate() throws ConfigurationException {
         userController userController =new userController(prop);
         userModel userModel=new userModel();
         Faker faker= new Faker();
         String name=faker.name().firstName();
         String email="testuser"+ Utils.generateRandomNumber(1000,9999)+"@gmail.com";
         String password ="1234";
         String phn_Num ="0171"+ Utils.generateRandomNumber(1000000,9999999);
         String nid=String.valueOf(Utils.generateRandomNumber(111111111,999999999));
         String role="customer";
        userModel.setName(name);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhone_number(phn_Num);
        userModel.setNid(nid);
        userModel.setRole(role);
        Response res= userController.createUser(userModel);
        System.out.println(res.asString());
       JsonPath jsonobj = res.jsonPath();
       String id=jsonobj.get("user.id").toString();
       Utils.setEnvVar("userId",id);
    }
    @Test(priority = 3,description = "search user")
    public void searchUser(){
        userController userController=new userController(prop);
        Response res =userController.searchUser(prop.getProperty("userId"));
        System.out.println(res.asString());
       JsonPath jsonobj = res.jsonPath();
       String messageActual =jsonobj.get("message");
       String messageExpected ="User found";
        SoftAssert softassert = new SoftAssert();
        softassert.assertTrue(messageActual.contains(messageExpected));
        softassert.assertAll();
    }
    @Test(priority = 4,description = "user delete")
    public void deleteUser(){
        userController userController=new userController(prop);
       Response res= userController.deleteUser(prop.getProperty("userId"));
        System.out.println(res.asString());
        JsonPath jsonobj= res.jsonPath();
        String messageActual=jsonobj.get("message");
        String messageExpected ="User deleted successfully";
        Assert.assertTrue(messageActual.contains(messageExpected));

    }
}
