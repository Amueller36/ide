package org.example.AndreFolderHandling;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Editions extends AbstractFolder {
    public Editions(String pathToFolder) {
        super(pathToFolder);
    }

    private ArrayList<Versions> versionFolders = new ArrayList<>();

    @Override
    public boolean createNewFolder(String folderName) {
        try {
            File f = new File(pathToFolder + File.separator + folderName);
            f.mkdir();
            Versions versionsFolder = new Versions(f.getAbsolutePath());
            versionFolders.add(versionsFolder);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<Versions> getAllVersionFolders() {
        return versionFolders;
    }


    public Versions getOrCreateFolderByName(String folderName) {
        for (Versions version : versionFolders) {
            if (version.name.equals(folderName)) {
                return version;
            }
        }
        if (createNewFolder(folderName)) {
            return getOrCreateFolderByName(folderName);
        }
        return null; //Todo: throw exception
    }
}

