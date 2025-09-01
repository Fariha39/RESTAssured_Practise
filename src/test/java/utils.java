import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class utils {

    public static void setEnvVar(String key,String value) throws ConfigurationException {

        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config");
        config.setProperty(key,value);
        config.save();

    }
}
