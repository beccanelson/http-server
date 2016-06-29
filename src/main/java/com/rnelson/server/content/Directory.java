package com.rnelson.server.content;

import com.rnelson.server.utilities.Response;
import com.rnelson.server.utilities.RouterList;

import java.io.File;
import java.util.Arrays;

public class Directory {
    private String directory = "public";

    public Directory(String directory) {
        this.directory = directory + "/";
    }

    public void handleAllFiles() {
        for (File file : getDirectoryListing()) {
            RouterList.statusCodesForRequests.put("GET /" + file.getName(), Response.status(200));
            RouterList.routeOptions.put("/" + file.getName(), Arrays.asList("GET", "PATCH"));
            FileHandler handler = new FileHandler(file);
            handler.addFileContentToPageContent();
            handler.addRequiredHeaderRowsForFile();
        }
    }

    private String generateListItem(String content) {
        return "<li>" + content + "</li>";
    }

    private String generateParagraph(String content) {
        return "<p>" + content + "</p>";
    }

    public String getDirectoryLinks() {
        File[] directoryListing = getDirectoryListing();

        StringBuilder directoryContents = new StringBuilder();
        directoryContents.append(generateParagraph(directory));
        directoryContents.append("<ul>");
        for (File file : directoryListing) {
            FileHandler fileHandler = new FileHandler(file);
            directoryContents.append(generateListItem(fileHandler.generateFileLink()));
        }
        directoryContents.append("</ul>");
        return directoryContents.toString();
    }

    private File[] getDirectoryListing() {
        File directory = new File("public/");
        return directory.listFiles();
    }
}