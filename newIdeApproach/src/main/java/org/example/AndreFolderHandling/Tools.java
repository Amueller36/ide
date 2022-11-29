package org.example.AndreFolderHandling;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Tools extends AbstractFolder {

    public Tools(String pathToFolder) {
        super(pathToFolder);
    }

    private ArrayList<Editions> editionsFolder = new ArrayList<>();

    @Override
    public boolean createNewFolder(String folderName) {
        try {
            File f = new File(pathToFolder + File.separator + folderName);
            f.mkdir();
            Editions edition = new Editions(f.getAbsolutePath());
            editionsFolder.add(edition);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<Editions> getAllEditionFolders() {
        return editionsFolder;
    }


    public Editions getOrCreateFolderByName(String folderName) {
        for (Editions editionFolder : editionsFolder) {
            if (editionFolder.name.equals(folderName)) {
                return editionFolder;
            }
        }
        if (createNewFolder(folderName)) {
            return getOrCreateFolderByName(folderName);
        }
        return null; //Todo: throw exception

    }

}
