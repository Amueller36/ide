package org.example;

import java.io.IOException;
import java.util.ArrayList;

public interface VersionCrawler {
     ArrayList<String> doGetVersionUrls() throws IOException, InterruptedException;
}
