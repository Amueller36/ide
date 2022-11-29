package org.example.AndreFolderHandling;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface isFolder {

    /*
     * Returns all filenames in the folder as an ArrayList of Strings
     */
    public ArrayList<String> getAllFilenamesAsString();

    /*
     * Returns all Directories in the folder as an ArrayList of Strings
     */
    public ArrayList<String> getAllDirectoriesAsString();


    /*
    Tries to create a new file in current directory. Returns true if successful and false if not.
     */
    public boolean createNewFile(String fileName);

    /*
Tries to create a new Folder in current directory. Returns true if successful and false if not.
 */
    public boolean createNewFolder(String folderName);

}
