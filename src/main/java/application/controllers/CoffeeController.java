package application.controllers;

import com.rnelson.server.Controller;
import com.rnelson.server.Header;
import com.rnelson.server.utilities.SharedUtilities;

public class CoffeeController implements Controller {

    @Override
    public byte[] get() {
        byte[] body = ("I'm a teapot").getBytes();
        Header header = new Header(418);
        byte[] responseHeader = header.getResponseHeader();
        return SharedUtilities.addByteArrays(responseHeader, body);
    }

    @Override
    public byte[] head() {
        return new byte[0];
    }

    @Override
    public byte[] post() {
        return new byte[0];
    }

    @Override
    public byte[] put() {
        return new byte[0];
    }

    @Override
    public byte[] patch() {
        return new byte[0];
    }

    @Override
    public byte[] options() {
        return new byte[0];
    }

    @Override
    public void sendRequestBody(String body) {

    }
}

