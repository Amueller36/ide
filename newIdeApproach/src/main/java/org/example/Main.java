package org.example;


import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {


        VSCodeCrawler crawler = new VSCodeCrawler();
        try {
            ArrayList<String> urls=crawler.doGetVersionUrls();
            crawler.doCreateFoldersForVersions(urls);
           // crawler.createUrlsForVersions(urls);
        } catch (Exception e) {
            e.printStackTrace();
        }

       // UrlRepository andresRepo = new UrlRepository("C:\\Devon\\NewIdeMirros\\urlrepo");
//        ArrayList<String> filenames = andresRepo.getAllFilenamesAsString();
//        System.out.println(filenames);
//        ArrayList<String> directories = andresRepo.getAllDirectoriesAsString();
//        System.out.println(directories);
    }
}