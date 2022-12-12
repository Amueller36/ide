package org.example;


import org.example.ChristianFolderHandling.UrlRepository;
import org.example.crawler.aws.AWSCrawler;
import org.example.crawler.eclipse.EclipseCrawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws IOException, InterruptedException {
        Path pathToRepo = Paths.get("I:\\UrlRepoTest");
        UrlRepository urlRepoObj = new UrlRepository(pathToRepo);
        logger.info("UrlRepoObj created");
        urlRepoObj.getOrCreateChild("docker");
     //   EclipseCrawler eclipseCrawler = new EclipseCrawler(urlRepoObj);
      //  eclipseCrawler.doGetVersionUrls();


    }
}