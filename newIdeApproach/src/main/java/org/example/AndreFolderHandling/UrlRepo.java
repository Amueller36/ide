package org.example.AndreFolderHandling;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UrlRepo extends AbstractFolder {
    public UrlRepo(String pathToFolder) {
        super(pathToFolder);
    }

    private ArrayList<Tools> toolFolders = new ArrayList<>();

    @Override
    public boolean createNewFolder(String folderName) {
        try {
            File f = new File(pathToFolder + File.separator + folderName);
            f.mkdir();
            Tools tools = new Tools(f.getAbsolutePath());
            toolFolders.add(tools);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Tools getOrCreateFolderByName(String folderName) {
        for (Tools tool : toolFolders) {
            if (tool.name.equals(folderName)) {
                return tool;
            }
        }
        if (createNewFolder(folderName)) {
            return getOrCreateFolderByName(folderName);
        }
        return null; //Todo: throw exception
    }
}
