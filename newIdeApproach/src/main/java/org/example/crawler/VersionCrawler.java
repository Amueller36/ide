package org.example.crawler;

import java.io.IOException;
import java.util.ArrayList;

public interface VersionCrawler {
     void doGetVersionUrls() throws IOException, InterruptedException;
     String getToolName();

}
