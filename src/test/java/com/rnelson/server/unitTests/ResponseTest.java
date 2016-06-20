package com.rnelson.server.unitTests;

import com.rnelson.server.response.Response;
import com.rnelson.server.utilities.SharedUtilities;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ResponseTest {
    @Test
    public void statusCanBeAccessedFromStaticContext() throws Throwable {
        assertEquals(Response.status(200), "HTTP/1.1 200 OK");
        assertEquals(Response.status(0), null);
    }

    @Test
    public void getResponseStatusReturnsStatus() throws Throwable {
        Response response = new Response("GET", "/");
        assertEquals(response.responseStatus(), Response.status(200));
    }

    @Test
    public void getOptionsReturnsOptions() throws Throwable {
        Response response = new Response("OPTIONS" ,"/");
        assertEquals(response.getOptions(), "Allow: GET,HEAD");
    }

    @Test
    public void getHeaderReturns404ForInvalidRoute() throws Throwable {
        Response response = new Response("GET", "/foobar");
        byte[] headerBytes = response.getHeader();
        String header = new String(headerBytes, "UTF-8");
        assertEquals(Response.status(404) + "\r\n\r\n", header);
    }

    @Test
    public void getHeaderReturnsHeaderForValidRoute() throws Throwable {
        Response response = new Response("GET", "/");
        byte[] headerBytes = response.getHeader();
        String header = new String(headerBytes, "UTF-8");
        assertTrue(header.contains(Response.status(200)));
    }

    @Test
    public void imATeapot() throws Throwable {
        Response response = new Response("GET", "/coffee");
        byte[] headerBytes = response.getHeader();
        String header = new String(headerBytes, "UTF-8");
        assertTrue(header.contains(Response.status(418)));
        assertTrue(header.contains("I'm a teapot"));

        Response tea = new Response("GET", "/tea");
        byte[] teaBytes = tea.getHeader();
        String teaResponse = new String(teaBytes, "UTF-8");
        assertTrue(teaResponse.contains(Response.status(200)));
    }

    @Test
    public void MethodNotAllowed() throws Throwable {
        Response response = new Response("SOMETHING", "/file1");
        byte[] headerBytes = response.getHeader();
        String header = SharedUtilities.convertByteArrayToString(headerBytes);
        assertTrue(header.contains(Response.status(405)));
    }
}
