package org.example.crawler.aws;

import org.example.VersionCrawler;
import org.example.VersionWithUrls;
import org.example.crawler.Mappings;
import org.example.crawler.OSTypes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.crawler.GeneralCrawler.*;

public class AWSCrawler implements VersionCrawler {
    private Mappings mappings=new Mappings();
    public AWSCrawler() {
        mappings.os.put(OSTypes.WINDOWS,"AWSCLIV2");
        mappings.os.put(OSTypes.MAC,"AWSCLIV2");
        mappings.os.put(OSTypes.LINUX,"awscli-exe-linux-x86_64");
        mappings.extension.put(OSTypes.WINDOWS,"msi");
        mappings.extension.put(OSTypes.MAC,"pkg");
        mappings.extension.put(OSTypes.LINUX,"zip");
    }

    @Override
    public ArrayList<VersionWithUrls> doGetVersionUrls() throws IOException, InterruptedException {
        String downloadUrl = "https://awscli.amazonaws.com/${os}-${version}.${ext}";
        String versionUrl = "https://api.github.com/repos/aws/aws-cli/git/refs/tags";
        String versions = doGetResponseBody(versionUrl);
        ArrayList<String> versionList = doGetRegexMatchesAsList("refs/tags/[0-9]+.[0-9]*+.[0-9]", versions);
        versionList.replaceAll(s -> s.replace("refs/tags/", ""));
        System.out.println(versionList);
        versionList.forEach(version -> {
            doGetWorkingDownloadurlsForGivenVersion
                    (List.of(downloadUrl), version, mappings.releases, "", mappings.os, mappings.architecture, mappings.extension);
        });
        return null;
    }
}

