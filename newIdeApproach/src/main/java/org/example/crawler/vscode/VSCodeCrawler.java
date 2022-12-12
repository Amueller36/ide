//package org.example.crawler.vscode;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.example.AndreFolderHandling.Editions;
//import org.example.AndreFolderHandling.Tools;
//import org.example.AndreFolderHandling.UrlRepo;
//import org.example.AndreFolderHandling.Versions;
//import org.example.crawler.*;
//
//import java.io.IOException;
//import java.net.http.HttpClient;
//
//import java.util.*;
//
//
//public class VSCodeCrawler implements VersionCrawler {
//    private ObjectMapper mapper = new ObjectMapper();
//    private HttpClient client = HttpClient.newBuilder().build();
//
//    private UrlRepo urlRepo;
//    private Tools toolsFolder;
//    private Editions editionsFolder;
//
//    private Mappings mappings = new Mappings();
//    private ArrayList<Versions> versionsFolder;
//
//    public VSCodeCrawler(String pathToRepo) {
//        urlRepo = new UrlRepo(pathToRepo);
//        this.toolsFolder = urlRepo.getOrCreateFolderByName("vscode");
//        this.editionsFolder = toolsFolder.getOrCreateFolderByName("vscode");
//        this.versionsFolder = editionsFolder.getAllVersionFolders();
//        mappings.os.put(OSTypes.WINDOWS, "win32-x64-archive");
//        mappings.os.put(OSTypes.MAC, "darwin");
//        mappings.os.put(OSTypes.LINUX, "linux-x64");
//
//    }
//
//    @Override
//    public void doGetVersionUrls() throws IOException, InterruptedException {
//        //Get each available VSCode Version from Maven as String and add it to versionUrls.
//        String jsonString = GeneralCrawler.doGetResponseBody("https://api.github.com/repos/microsoft/vscode/git/refs/tags");
//        ArrayList<String> versions = GeneralCrawler.doGetRegexMatchesAsList("refs/tags/[0-9]+.[0-9]*+.[0-9]", jsonString);
//        versions.replaceAll(s -> s.replace("refs/tags/", ""));
//        ;
//        String downloadurl = "https://update.code.visualstudio.com/${version}/${os}/stable";
//        versions.forEach(version -> {
//            Map<String, Set<String>> versionWithUrlFiles = GeneralCrawler.doGetWorkingDownloadurlsForGivenVersion
//                    (List.of(downloadurl), version, List.of(""), "", mappings.os, mappings.architecture, mappings.extension);
//
//        });
//
//
//        //TODO: Call other methods to create folders and files for each version.
//
//    }
//
//    public void doCreateFoldersForVersions(ArrayList<String> versionUrls) {
//        //Create folders for each version
//        ArrayList<String> versionsInEditionFolder = editionsFolder.getAllDirectoriesAsString();
//        for (String version : versionUrls) {
//            if (!versionsInEditionFolder.contains(version)) {
//                editionsFolder.createNewFolder(version);
//            }
//        }
//        System.out.println("Folders for Versions created");
//    }
//
//    public void createUrlsForVersions(ArrayList<String> versionUrls) {
//        //Create urls for each version
//        //vscode has windows, linux and macos
//        for (String version : versionUrls) {
//            Versions versionFolder = editionsFolder.getOrCreateFolderByName(version);
////            versionFolder.createNewFile("windows_x64.txt", //TODO: Enter URL here
////                    "https://update.code.visualstudio.com/"+ version "/win32-x64-archive/stable");
////            versionFolder.createNewFile("linux_x64","https://update.code.visualstudio.com/"+ version "/linux-x64/stable");
////            versionFolder.createNewFile("mac_x64","https://update.code.visualstudio.com/"+ version "/darwin/stable");
//            // versionFolder.createNewFile("mac_arm64","https://update.code.visualstudio.com/"+ version "/darwin/stable"); TODO: Not sure which one here is the right Mac Vaersion
//
//        }
//
//
//    }
//
//
//}
