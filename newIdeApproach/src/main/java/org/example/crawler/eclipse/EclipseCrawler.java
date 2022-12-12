package org.example.crawler.eclipse;


import org.example.ChristianFolderHandling.*;
import org.example.crawler.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


public class EclipseCrawler implements VersionCrawler {

    private Mappings mappings;
    private ArrayList<String> editions = new ArrayList<>();
    private UrlRepository urlRepository;
    private UrlTool toolFolder;

    public EclipseCrawler(UrlRepository urlRepo) {
        this.mappings = new Mappings();
        mappings.os.put(OSTypes.WINDOWS, "win32");
        mappings.os.put(OSTypes.MAC, "macosx-cocoa");
        mappings.os.put(OSTypes.LINUX, "linux-gtk");
        mappings.extension.put(OSTypes.WINDOWS, "zip");
        mappings.extension.put(OSTypes.MAC, "dmg");
        mappings.extension.put(OSTypes.LINUX, "tar.gz");
        mappings.architecture.put("default", "x86_64");
        mappings.architecture.put("arm64", "aarch64");
        mappings.releases.add("R");
        mappings.releases.add("M1");
        mappings.releases.add("M2");
        mappings.releases.add("M3");
        mappings.releases.add("RC1");
        editions.add("java"); // TODO: Get Editions from Folders instead of hardcoding them
        this.urlRepository = urlRepo;
        toolFolder = urlRepo.getOrCreateChild(getToolName());
    }

    @Override
    public String getToolName() {
        return "Eclipse";
    }


    @Override
    public void doGetVersionUrls() throws IOException, InterruptedException {
        String eclipseUrl = "https://www.eclipse.org/downloads/packages/release";
        String responseBody = GeneralCrawler.doGetResponseBody(eclipseUrl);
        ArrayList<String> versions = GeneralCrawler.doGetRegexMatchesAsList("\\d{4}-\\d{2}", responseBody);
        ArrayList<String> downloadUrls = new ArrayList<String>();
        downloadUrls.add("https://ftp.snt.utwente.nl/pub/software/eclipse/technology/epp/downloads/release/${version}/${release}/eclipse-${edition}-${version}-${release}-${os}-${arch}.${ext}");
        downloadUrls.add("https://archive.eclipse.org/technology/epp/downloads/release/${version}/${release}/eclipse-${edition}-${version}-${release}-${os}-${arch}.${ext}");
        downloadUrls.add("https://ftp.osuosl.org/pub/eclipse/technology/epp/downloads/release/${version}/${release}/eclipse-${edition}-${version}-${release}-${os}-${arch}.${ext}");

        ArrayList<VersionWithUrls> versionWithUrls = new ArrayList<VersionWithUrls>();
        for (String version : versions) {
            for (String edition : editions) {
                Map<String, Set<String>> fileNamesWithUrls = GeneralCrawler.doGetWorkingDownloadurlsForGivenVersion(downloadUrls, version, mappings.releases, edition, this.mappings.os, mappings.architecture, mappings.extension);
                UrlEdition editionFolder = toolFolder.getOrCreateChild(edition);
                UrlVersion versionFolder = editionFolder.getOrCreateChild(version);
                VersionWithUrls versionInclusiveUrlsAsString = new VersionWithUrls(version, fileNamesWithUrls);
                //Iterate over fileNamesWithUrls and add them to the versionFolder
                for (Map.Entry<String, Set<String>> entry : fileNamesWithUrls.entrySet()) {
                    String fileName = entry.getKey();
                    Set<String> urls = entry.getValue();
                    UrlFile file = versionFolder.getOrCreateChild(fileName);
                    file.addToObjectsList(urls);
                    file.saveListFromObjectIntoFile();
                }
            }
        }

    }


//    public ArrayList<FilenameAndUrl> doGetUrlFileForVersion(String version, String release, String edition) {
//        ArrayList<String> downloadUrls = new ArrayList<String>();
//        downloadUrls.add("https://ftp.snt.utwente.nl/pub/software/eclipse/technology/epp/downloads/release/${version}/${release}/eclipse-${edition}-${version}-${release}-${os}-${arch}${ext}");
//        downloadUrls.add("https://archive.eclipse.org/technology/epp/downloads/release/${version}/${release}/eclipse-${edition}-${version}-${release}-${os}-${arch}${ext}");
//        downloadUrls.add("https://ftp.osuosl.org/pub/eclipse/technology/epp/downloads/release/${version}/${release}/eclipse-${edition}-${version}-${release}-${os}-${arch}${ext}");
//
//        ArrayList<String> urlsWindows_x64 = new ArrayList<String>();
//        ArrayList<String> urlsLinux_x64 = new ArrayList<String>();
//        ArrayList<String> urlsLinux_arm64 = new ArrayList<String>();
//        ArrayList<String> urlsMac_x64 = new ArrayList<String>();
//        ArrayList<String> urlsMac_arm64 = new ArrayList<String>();
//        // generate all possible combinations of URLs for the given version, release, and edition
//        return downloadUrls.stream()
//                // replace the version placeholder in the URL
//                .map(url -> url.replace("${version}", version))
//                // replace the release placeholder in the URL
//                .map(url -> url.replace("${release}", release))
//                // replace the edition placeholder in the URL
//                .map(url -> url.replace("${edition}", edition))
//                // create a stream of URLs for each OS and architecture
//                .flatMap(url -> mappings.os.entrySet().stream()
//                        // replace the OS placeholder in the URL
//                        .map(os -> url.replace("${os}", os.getValue()))
//                        // filter out URLs for the Windows OS
//                        .filter(this::isNotWindowsOs)
//                        // create a stream of URLs for each architecture
//                        .flatMap(url -> mappings.architecture.stream()
//                                // replace the architecture placeholder in the URL
//                                .map(arch -> url.replace("${arch}", arch))
//                                // replace the extension placeholder in the URL
//                                .map(url -> url.replace("${ext}", mappings.getExtensionByOs(os.getValue())))
//                        )
//                )
//
//    }


}
