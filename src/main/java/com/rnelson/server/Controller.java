package com.rnelson.server;

import java.io.File;
import java.util.Map;
import java.util.Set;

public interface Controller {

    byte[] get();

    byte[] head();

    byte[] post();

    byte[] put();

    byte[] patch();

    byte[] options();

    byte[] delete();

    byte[] redirect();

    void sendRequestData(Map<String,String> data);

    void sendMethodOptions(Set<String> methodOptions);

    void sendFile(File file);

    void isAuthorized(Boolean isAuthorized);
}
