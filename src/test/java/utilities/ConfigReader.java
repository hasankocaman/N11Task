package utilities;


import java.io.*;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        String path = "configuration.properties";
        try {
            FileInputStream fis = new FileInputStream(path);
            properties = new Properties();
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static void writeProperty(String key, String value) {

        properties.setProperty("username", key);
        properties.setProperty("pasword", value);


    }

    public static void propertyWrite(String username, String value) {
        try {
            FileWriter fileWriter = new FileWriter("configuration.properties", true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.append("patient" + username + "=" + value + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeProperty(String rol, String username, String value) {
        String path = "configuration.properties";
        try {
            FileOutputStream fOut = new FileOutputStream(path);
            FileInputStream fis = new FileInputStream(path);
            properties.load(fis);
            properties.setProperty(rol + username, value);
            properties.store(fOut, rol + " olusturuldu");
            fOut.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

