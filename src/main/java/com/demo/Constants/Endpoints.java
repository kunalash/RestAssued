package com.demo.Constants;

import com.demo.Utils.ConfigManager;

public class Endpoints {
    private static final String USER = ConfigManager.getInstance().getConfigValue("AuthUser");
    public static final String GET_MEASURE_TYPE = "/publicApi/portal/v1/users/"+USER+"/unitsOfMeasure";
    public static final String GET_ALL_SITES = "/publicApi/portal/v1/users/"+USER+"/sites?viewType=admin";
    public static final String CLIENT_CREDENTIALS = "/identity/client_credentials/token";

    private Endpoints(){}

}

