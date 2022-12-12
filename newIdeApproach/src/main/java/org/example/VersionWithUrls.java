package org.example;

import org.example.test.FilenameAndUrl;

import java.util.ArrayList;

public record VersionWithUrls(String version,
        ArrayList<FilenameAndUrl> filenameWithUrls) {

}
