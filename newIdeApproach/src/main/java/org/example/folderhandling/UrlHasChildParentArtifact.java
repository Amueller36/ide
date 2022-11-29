package org.example.folderhandling;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class UrlHasChildParentArtifact<P extends UrlArtifact<?, ?>, C> extends UrlArtifact<P, C> {
    protected final P parent;

    protected final String name;

    protected Map<String, C> children;


    public UrlHasChildParentArtifact(P parent, String name) {
        super(parent.getPath().resolve(name));
        this.parent = parent;
        this.name = name;
        this.children = new HashMap<>();

    }

    public P getParent() {

        return this.parent;
    }

    public String getName() {

        return this.name;
    }

    /**
     * Die Methode dient dazu die tatsächlich vorhandenen Ordner und damit
     * bspw. auch Versionen zu erhalten, unabhängig von der ggf. bereits manipulierten
     * Kindstruktur des aktuellen Objekts.
     *
     * @return
     */
    public Set<String> getChildrenInDirectory() {
        System.out.println("CURRENT PATH :" + path.toString());
        try {
            return Stream.of(Objects.requireNonNull(new File(path.toString()).listFiles()))
                    .filter(file -> !file.isDirectory())
                    .map(File::getName)
                    .collect(Collectors.toSet());
        } catch (NullPointerException e) {
            System.out.println("No children in directory");
            return new HashSet<>();
        }
    }


    public int getChildCount() {

        return this.children.size();
    }

    public C getChild(String name) {

        return this.children.get(name);
    }

    public C getOrCreateChild(String name) {

        if (Files.exists(Path.of(path.toString() + "\\\\" + name))) {
            return this.children.get(name);
        } else {
            return newChild(name);
        }
    }

    protected abstract C newChild(String name);
}
