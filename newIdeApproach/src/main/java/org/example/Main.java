package org.example;


import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {


        VSCodeCrawler crawler = new VSCodeCrawler("C:\\Devon\\NewIdeMirros\\urlrepo");
        try {
            ArrayList<String> urls=crawler.doGetVersionUrls();
            crawler.doCreateFoldersForVersions(urls);
           // crawler.createUrlsForVersions(urls);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}