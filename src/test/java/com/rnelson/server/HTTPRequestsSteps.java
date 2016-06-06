package com.rnelson.server;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.*;
import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class HTTPRequestsSteps {
    private HttpURLConnection connection;
    private Integer port;

    @Given("^the server is running on port (\\d+)$")
    public void theServerIsRunningOnPort(final int port) throws Throwable {
        this.port = port;
        Thread server = new Thread(new ServerRunner(port));
        server.start();
    }

    @When("^I request \"([^\"]*)\" \"([^\"]*)\"$")
    public void iRequest(String method, String uri) throws Throwable {
        try {
            URL url = new URL("http://localhost:" + port + uri);
            connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod(method);
            connection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @When("^I POST \"([^\"]*)\" to \"([^\"]*)\"$")
    public void iPOSTTo(String parameter, String uri) throws Throwable {
        try {
            URL url = new URL("http://localhost:" + port + uri + "?parameter=" + parameter);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content type", "application/x-www-form-urlencoded");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Then("^the response status should be (\\d+)$")
    public void theResponseStatusShouldBe(Integer status) throws Throwable {
        try {
            Integer responseStatus = connection.getResponseCode();
            assertEquals(status, responseStatus);
        } catch (ConnectException e) {
            e.printStackTrace();
        }
    }

    private static String findMatch(String regex, String request) {
        String match = null;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(request);
        if (matcher.find()) {
            match = matcher.group().trim();
        }
        return match;
    }

    private String getFullResponse(BufferedReader in) throws IOException {
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        return response.toString();
    }

    public static String getResponseBody(String response) throws IOException {
        return findMatch("\r\n\r\n.*", response);
    }

    @And("^the response body should be empty$")
    public void theResponseBodyShouldBeEmpty() throws Throwable {
        try {
            InputStream connectionInput = connection.getInputStream();
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(connectionInput));
            String response = getFullResponse(in);
            String responseBody = getResponseBody(response);
            assertEquals(null, responseBody);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @And("^the response body should be \"([^\"]*)\"$")
    public void theResponseBodyShouldBe(String body) throws Throwable {
        try {
            InputStream connectionInput = connection.getInputStream();
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(connectionInput));
            String response = getFullResponse(in);
            String responseBody = getResponseBody(response);
            assertEquals(body, responseBody);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}