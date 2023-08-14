package com.demo.Utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ConfigManager {
    private static ConfigManager configManager;
    private static final Properties props =  new Properties();

    private ConfigManager() throws IOException {
        FileInputStream fs =  new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\Configuration.properties");
        props.load(fs);

    }

    public static ConfigManager getInstance() {
        try {
            if (configManager == null){
                synchronized (ConfigManager.class){
                    configManager = new ConfigManager();
                }
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return configManager;
    }

    public String getConfigValue(String key){
        return props.getProperty(key);
    }

}
