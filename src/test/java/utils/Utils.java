package utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Utils {

    public static void setEnvVar(String key,String value) throws ConfigurationException {

        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty(key,value);
        config.save();

    }

    public static int  generateRandomNumber(int min,int  max){
       return (int) (Math.random()*(max-min)+min);

    }

    public static void main(String[] args) {
        System.out.println(generateRandomNumber(1000,5000));
    }
}
