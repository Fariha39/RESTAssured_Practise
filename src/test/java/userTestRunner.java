import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.Test;

import java.util.Properties;

public class userTestRunner extends setup{
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
        utils.setEnvVar("token",token);

    }
}
