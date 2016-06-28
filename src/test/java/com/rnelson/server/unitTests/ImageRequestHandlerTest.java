package com.rnelson.server.unitTests;

import com.rnelson.server.request.ImageRequestHandler;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ImageRequestHandlerTest {
    ImageRequestHandler jpegHandler = new ImageRequestHandler("GET /image.jpeg HTTP/1.1\r\n\r\n");
    ImageRequestHandler gifHandler = new ImageRequestHandler("GET /image.gif HTTP/1.1\r\n\r\n");

    @Test
    public void getContentTypeReturnsImageType() throws Throwable {
        assertEquals("jpeg", jpegHandler.getContentType());
    }
}