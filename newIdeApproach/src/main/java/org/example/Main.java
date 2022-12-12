package org.example;


import org.example.crawler.aws.AWSCrawler;
import org.example.crawler.eclipse.EclipseCrawler;
import org.example.crawler.vscode.VSCodeCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws IOException, InterruptedException {

       // EclipseCrawler eclipseCrawler = new EclipseCrawler();
      //  eclipseCrawler.doGetVersionUrls();
//        VSCodeCrawler vsCodeCrawler = new VSCodeCrawler("test");
//        vsCodeCrawler.doGetVersionUrls();
        AWSCrawler awsCrawler = new AWSCrawler();
        awsCrawler.doGetVersionUrls();


    }
}