package org.example.folderhandling;
import static org.junit.Assert.*;


import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.example.folderhandling.*;
import org.junit.Test;

/**
 *
 */
public class UrlGetChildrenInDirectoryTest {

    @Test
    public void test() throws IOException {

//		Path pathToRepo = Paths
//				.get("C:\\projects\\Mirrors-IDE\\workspaces\\ide-urls\\url-updater\\src\\test\\resources\\urlsRepo");
        Path pathToRepo = Paths.get("C:\\Devon\\NewIdeMirros\\urlrepo");
//		Path pathToRepo = Paths
//				.get("C:\\projects\\Mirrors-IDE\\workspaces\\ide-urls\\url-updater\\src\\test\\resources\\testpath");

        // Erzeugen der Beispielobjekte, welche die Ordner repräsentieren
        // (wenn Ordnerstruktur bereits existiert, kann später eine bereits lokal implementierte
        // Methode zum Einlesen der Ordnerstruktur, mit entsprechender Erzeugung der
        // Objekte, genutzt werden).
        UrlRepository UrlRepoObj = new UrlRepository(pathToRepo);
        assertNotNull(UrlRepoObj.getPath());
        UrlTool UrlToolObj = new UrlTool(UrlRepoObj, "docker");
        assertNotNull(UrlToolObj.getPath());
        UrlTool UrlToolObj2 = new UrlTool(UrlRepoObj, "vscode");
        assertNotNull(UrlToolObj2.getPath());

        UrlEdition UrlEditionObj = new UrlEdition(UrlToolObj, "rancher");
        assertNotNull(UrlEditionObj.getPath());

        UrlVersion UrlVersionObj = new UrlVersion(UrlEditionObj, "1.6.1");
        assertNotNull(UrlVersionObj.getPath());
        UrlVersion UrlVersionObj2 = new UrlVersion(UrlEditionObj, "1.6.2");
        assertNotNull(UrlVersionObj2.getPath());

        // Erzeugen der Ordnerstruktur, basierend auf den zuvor erzeugten Objekten.
        UrlFile UrlFileObj = new UrlFile(UrlVersionObj, "linux.urls");
        Files.createDirectories(UrlVersionObj2.getPath());
        Files.createDirectories(UrlFileObj.parent.getPath());
        if (!Files.exists(UrlFileObj.getPath())) {
            Files.createFile(UrlFileObj.getPath());
        }

        // Nutzen der neuen Methode, um Ordnerinhalt anzuzeigen. Dabei wurden
        // entweder nur Ordner oder Dateien ausgegeben.
        // Begründung: Jede Version wird durch einen Ordner repräsentiert. Falls
        // lock-Dateien oder andere Konfigurationsdateien sich in dem selben Ordner wie
        // die Versionen befinden, so sollen diese entsprechend nicht ausgegeben werden.
        // Andererseits wurde Rücksicht darauf genommen, dass in einem Versionsordner selbst
        // lediglich Dateien enthalten sind (ggf. werden hier später lock-Dateien o.ä. ignoriert)
        UrlRepoObj.getChildrenInDirectory();
        UrlToolObj.getChildrenInDirectory();
        UrlEditionObj.getChildrenInDirectory();
        UrlVersionObj.getChildrenInDirectory();

    }
}
