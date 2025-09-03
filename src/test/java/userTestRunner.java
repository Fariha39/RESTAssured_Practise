import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.Test;

import java.util.Properties;

public class userTestRunner extends setup{
    @Test
    public void userLogin() throws ConfigurationException {
        userController userController = new userController(prop);
       Response res =userController.doLogin();
        System.out.println(res.asString());
        JsonPath jsonobj =res.jsonPath();
        String token =jsonobj.get("token");
        utils.setEnvVar("token",token);

    }
}
