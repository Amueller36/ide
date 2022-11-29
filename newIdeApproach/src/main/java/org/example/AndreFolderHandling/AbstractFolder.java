package org.example.AndreFolderHandling;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AbstractFolder implements isFolder {
    protected String pathToFolder;
    protected String name;

    //Subfolders

    public AbstractFolder(String pathToFolder) {
        this.pathToFolder = pathToFolder;
        //check if folder exists otherwise create one
        File f = new File(pathToFolder);
        name = f.getName();
        if (!f.exists()) {
            f.mkdir();
        }
    }

    @Override
    public ArrayList<String> getAllFilenamesAsString() {
        File f = new File(pathToFolder);
        if (f.list() == null) {
            System.out.println("The path "+ pathToFolder +"does not exist!");
        } else {
            return new ArrayList<>(Arrays.asList((f.list())));
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllDirectoriesAsString() {
        File f = new File(pathToFolder);
        if (f.list() == null) {
            System.out.println("The path "+ pathToFolder +" does not exist!");
        } else {
            List<String> directories = Arrays.stream(Objects.requireNonNull(f.list()))
                    .filter(file -> new File(f, file).isDirectory())
                    .collect(Collectors.toList());
            return new ArrayList<>(directories);
        }
        return null;
    }

    @Override
    public boolean createNewFile(String fileName) {
        try {
            File f = new File(pathToFolder + File.separator + fileName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean createNewFolder(String folderName) {
        try {
            File f = new File(pathToFolder + File.separator + folderName);
            return f.mkdir();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

