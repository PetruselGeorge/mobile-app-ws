package com.appsdeveloper.app.ws.mobileappws.security;

import com.appsdeveloper.app.ws.mobileappws.context.SpringApplicationContext;


public class SecurityConstants {
    public static final long expirationTime = 864000000;
    public static final String tokenPrefix = "Bearer ";
    public static final String authorizationHeaderString = "Authorization";
    public static final String userIDheaderString = "UserID";
    public static final String signUpURL = "/users";
    public static final String processURL = "/users/login";

    public static String getTokenSecret() {
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("appProperties");
        return appProperties.getTokenSecret();
    }


}
