package com.rnelson.server.utilities;

import com.rnelson.server.response.Response;

import java.util.*;

public class Router {
    public static Map<String, List<String>> routeOptions = new HashMap<String, List<String>>();
    public static Map<String, String> statusCodesForRoutes = new HashMap<String, String>();


    public Router() {
        routeOptions.put("/", Arrays.asList("GET", "HEAD"));
        routeOptions.put("/echo", Arrays.asList("GET", "POST"));
        routeOptions.put("/form", Arrays.asList("POST", "PUT", "GET", "DELETE"));
        routeOptions.put("/method_options", Arrays.asList("GET", "HEAD", "POST", "OPTIONS", "PUT"));
        routeOptions.put("/method_options2", Arrays.asList("GET", "OPTIONS"));
        routeOptions.put("/coffee", Arrays.asList("GET"));
        routeOptions.put("/tea", Arrays.asList("GET"));
        routeOptions.put("/redirect", Arrays.asList("GET"));
        routeOptions.put("/parameters", Arrays.asList("GET"));

        statusCodesForRoutes.put("GET *", Response.status(200));
        statusCodesForRoutes.put("HEAD *", Response.status(200));
        statusCodesForRoutes.put("POST *", Response.status(200));
        statusCodesForRoutes.put("OPTIONS *", Response.status(200));
        statusCodesForRoutes.put("PUT *", Response.status(200));
        statusCodesForRoutes.put("DELETE *", Response.status(200));
        statusCodesForRoutes.put("GET /coffee", Response.status(418));
        statusCodesForRoutes.put("GET /redirect", Response.status(302));
        statusCodesForRoutes.put("GET /form", Response.status(200));
        statusCodesForRoutes.put("GET /parameters", Response.status(200));

    }
}
