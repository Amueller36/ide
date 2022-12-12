package org.example;


import org.example.ChristianFolderHandling.UrlEditor;
import org.example.ChristianFolderHandling.UrlFile;
import org.example.ChristianFolderHandling.UrlRepository;
import org.example.crawler.VersionWithUrls;
import org.example.crawler.aws.AWSCrawler;
import org.example.crawler.eclipse.EclipseCrawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws IOException, InterruptedException {
        String pathToRepo ="I:\\UrlRepoTest";
        UrlEditor urlEditorObj = new UrlEditor(pathToRepo);
        EclipseCrawler eclipseCrawler = new EclipseCrawler(urlEditorObj);
        eclipseCrawler.doGetVersionUrls();



    }
}