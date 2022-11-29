package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.AndreFolderHandling.Editions;
import org.example.AndreFolderHandling.Tools;
import org.example.AndreFolderHandling.UrlRepo;
import org.example.folderhandling.*;
import org.example.vscode.GithubApiJsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


public class VSCodeCrawler implements VersionCrawler {
    private ObjectMapper mapper = new ObjectMapper();
    HttpClient client = HttpClient.newBuilder().build();

    @Override
    public ArrayList<String> doGetVersionUrls() throws IOException, InterruptedException {
        ArrayList<String> versionUrls = new ArrayList<String>();
        //Get each available VSCode Version from Maven as String and add it to versionUrls.

        //Get available VSCode Versions from Github
        String githubAPIUrl = "https://api.github.com/repos/microsoft/vscode/releases";
        String githubAPIUrl2 = "https://api.github.com/repos/microsoft/vscode/git/refs/tags";
        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create(githubAPIUrl2))
                .GET()
                .build();
        String jsonString = client.send(request1, BodyHandlers.ofString()).body();
        //Map Json String to Test2
        GithubApiJsonObject[] myObjects = mapper.readValue(jsonString, GithubApiJsonObject[].class);
        Arrays.stream(myObjects).filter(githubApiJsonObject -> githubApiJsonObject.ref.contains("refs/tags"))
                .filter(x -> x.ref.matches("refs/tags/[0-9]+.[0-9]*+.[0-9]+")) //Filter out all non-version tags
                .forEach(githubApiJsonObject -> versionUrls.add(githubApiJsonObject.ref.replace("refs/tags/", "")));

        System.out.println(versionUrls);
        return versionUrls;

    }

    public void doCreateFoldersForVersions(ArrayList<String> versionUrls) {
        //Create folders for each version
        String pathToRepo = "C:\\Devon\\NewIdeMirros\\urlrepo";
        UrlRepo urlRepo = new UrlRepo(pathToRepo);
        Tools toolsFolder = urlRepo.getOrCreateFolderByName("vscode");
        Editions editionsFolder = toolsFolder.getOrCreateFolderByName("vscode");
        ArrayList<String> versionsInEditionFolder = editionsFolder.getAllDirectoriesAsString();
        for (String version : versionUrls) {
            if (!versionsInEditionFolder.contains(version)) {
                editionsFolder.createNewFolder(version);
            }
        }
        System.out.println("Folders for Versions created");
    }

    public void createUrlsForVersions(ArrayList<String> versionUrls) {
        //Create urls for each version
        Path pathToRepo = Paths.get("C:\\Devon\\NewIdeMirros\\urlrepo");
        UrlRepo urlRepo = new UrlRepo(pathToRepo.toString());
        Tools toolsFolder = urlRepo.getOrCreateFolderByName("vscode");
        Editions editionsFolder = toolsFolder.getOrCreateFolderByName("vscode");

    }


}
