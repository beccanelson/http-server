package application;

import com.rnelson.server.utilities.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Header {
    private final String status;
    private final List<String> rows = new ArrayList<>();

    public Header(int status) {
        this.status = Response.status(status);
    }

    public void includeOptions(Set<String> methodOptions) {
        String optionsHeader = "Allow: " + String.join(",", methodOptions);
        this.rows.add(optionsHeader);
    }

    public void includeContentType(String contentType) {
        String contentTypeHeader = "Content-Type: " + contentType;
        this.rows.add(contentTypeHeader);
    }

    public void includeLocation(String location) {
        String locationHeader = "Location: " + location;
        this.rows.add(locationHeader);
    }

    public void includeBasicAuthorization() {
        this.rows.add("WWW-Authenticate: Basic");
    }

    private String getResponseAsString() {
        StringBuilder response = new StringBuilder();
        response.append(status);
        String crlf = "\r\n";
        if (rows.size() > 0) {
            response.append(crlf);
            response.append(String.join(",", rows));
        }
        response.append(crlf);
        response.append(crlf);
        return response.toString();
    }

    public byte[] getResponseHeader() {
        return getResponseAsString().getBytes();
    }
}
