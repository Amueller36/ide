package org.example.crawler;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.Main.logger;

public class GeneralCrawler {
    //Create a HttpClient object
    private static final HttpClient client = HttpClient.newBuilder().build();

    public static ArrayList<String> doGetRegexMatchesAsList(String regex, String input) {
        ArrayList<String> matches = new ArrayList<>();
        var matcher = Pattern.compile(regex).matcher(input);
        while (matcher.find()) {
            MatchResult result = matcher.toMatchResult();
            if (!matches.contains(result.group())) {
                matches.add(result.group());
            }
        }
        return matches;
    }

    public static String doGetResponseBody(String url) throws IOException, InterruptedException {
        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        String responseBody = client.send(request1, HttpResponse.BodyHandlers.ofString()).body();
        return responseBody;
    }

    private static Set<String> createUrlsForOsAndArch(Map<String, Set<String>> urlsByOsAndArch, String osAndArch) {
        if (urlsByOsAndArch.containsKey(osAndArch)) {
            return urlsByOsAndArch.get(osAndArch);
        } else {
            Set<String> urls = new HashSet<>();
            urlsByOsAndArch.put(osAndArch, urls);
            return urls;
        }
    }

    public static Map<String, Set<String>> doGetWorkingDownloadurlsForGivenVersion(List<String> downloadurls, String version, List<String> releases, String edition, HashMap<OSTypes, String> oses, HashMap<String, String> architectures, HashMap<OSTypes, String> extensions) {
        //2.Approach
        Map<String, Set<String>> urlsByOsAndArch = new HashMap<>();

        // Create a regular expression to match placeholders in the URL
        Pattern placeholderPattern = Pattern.compile("\\$\\{(\\w+)\\}");

        // Iterate over all URLs
        for (String url : downloadurls) {
            // Iterate over all possible combinations of the parameters
            for (String r : releases) {
                oses.forEach((oskey, o) -> {
                    architectures.forEach((archkey, arch) -> {
                        // Substitute the parameters in the URL
                        String combination = url;
                        Matcher matcher = placeholderPattern.matcher(combination);
                        StringBuilder sb = new StringBuilder();
                        int i = 0;
                        while (matcher.find()) {
                            String placeholder = matcher.group(1);
                            String replacement = null;
                            switch (placeholder) {
                                case "version":
                                    replacement = version;
                                    break;
                                case "release":
                                    replacement = r;
                                    break;
                                case "edition":
                                    replacement = edition;
                                    break;
                                case "os":
                                    replacement = o;
                                    break;
                                case "arch":
                                    replacement = arch;
                                    break;
                                case "ext":
                                    replacement = extensions.get(oskey);
                                    break;
                            }
                            sb.append(combination, i, matcher.start());
                            if (replacement != null) {
                                sb.append(replacement);
                            }
                            i = matcher.end();
                        }
                        sb.append(combination.substring(i));
                        combination = sb.toString();
                        if (doCheckIfDownloadUrlWorks(combination)) {
                            logger.info("Found working URL for ARCH " + o + arch + combination);
                            String osAndArch = oskey + "_" + archkey;
                            Set<String> urlsForOsAndArch = createUrlsForOsAndArch(urlsByOsAndArch, osAndArch);
                            urlsForOsAndArch.add(combination);
                        }
                    });
                });
            }
        }
        return urlsByOsAndArch;
    }


    public static boolean doCheckIfDownloadUrlWorks(String downloadUrl) {
        //Do Head request to check if the download url works and if the file is available for download
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(downloadUrl))
                .method("HEAD", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() >= 200 && response.statusCode() < 400) {
                return true;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    //TODO FIX this
//    private static ArrayList<VersionWithUrls> getVersionWithUrlsListFromLists(String
//                                                                                      version, List<String> windows_x64, List<String> linux_x64, List<String> mac_x64, List<String> mac_arm64) {
//        ArrayList<VersionWithUrls> versionWithUrls = new ArrayList<>();
//        FilenameAndUrl windowsx64filewithurls = new FilenameAndUrl("windows_x64", new ArrayList<>());
//        ArrayList<FilenameAndUrl> filenameAndUrls = new ArrayList<>();
//        for (String url : windows_x64) {
//            windowsx64filewithurls.urls().add(url);
//        }
//        filenameAndUrls.add(windowsx64filewithurls);
//        FilenameAndUrl linuxx64filewithurls = new FilenameAndUrl("linux_x64", new ArrayList<>());
//        for (String url : linux_x64) {
//            linuxx64filewithurls.urls().add(url);
//        }
//        filenameAndUrls.add(linuxx64filewithurls);
//        FilenameAndUrl macx64filewithurls = new FilenameAndUrl("mac_x64", new ArrayList<>());
//        for (String url : mac_x64) {
//            macx64filewithurls.urls().add(url);
//        }
//        filenameAndUrls.add(macx64filewithurls);
//        FilenameAndUrl macarm64filewithurls = new FilenameAndUrl("mac_arm64", new ArrayList<>());
//        for (String url : mac_arm64) {
//            macarm64filewithurls.urls().add(url);
//        }
//        filenameAndUrls.add(macarm64filewithurls);
//        versionWithUrls.add(new VersionWithUrls(version, filenameAndUrls));
//        return versionWithUrls;
//    }
}
