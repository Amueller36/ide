package org.example.test;

import java.nio.file.Path;

public interface isChild {
    Path getParentPath();

    String getName();

    String getAllDirectoriesAsString();

    String getAllFilesAsString();

    public boolean createNewFile(String fileName);


    public boolean createNewFolder(String folderName);
}
