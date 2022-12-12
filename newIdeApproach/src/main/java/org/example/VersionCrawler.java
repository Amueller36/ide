package org.example;

import java.io.IOException;
import java.util.ArrayList;

public interface VersionCrawler {
     ArrayList<VersionWithUrls> doGetVersionUrls() throws IOException, InterruptedException;
}
