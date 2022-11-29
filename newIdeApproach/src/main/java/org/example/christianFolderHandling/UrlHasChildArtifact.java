package org.example.christianFolderHandling;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class UrlHasChildArtifact<P ,C> extends UrlArtifact<P,C> {
	protected Map<String, C> children;


	public UrlHasChildArtifact(Path path) {
		super(path);
		this.children = new HashMap<>();
	}


	public int getChildCount() {

	    return this.children.size();
	}

	public C getChild(String name) {

	    return this.children.get(name);
	}

	public C getOrCreateChild(String name) {
	   if(Files.exists(Path.of(path.toString()+"\\\\" +name))) {
		   return this.children.get(name);
	   }
	   else {
		   return newChild(name);
	   }
	}

	/**
	 * Returns an LinkedList of directories (or for ulrVersion of files) that are inside the
	 * directory given by the current objects path.
	 *
	 * @return
	 */
	public ArrayList<String> getChildrenInDirectory() {
		File[] directories = new File(path.toString()).listFiles(File::isDirectory);
		int l = directories.length;
		System.out.println(l);
		ArrayList<String> listOfChildrenInDir = new ArrayList<>();
		for (int i=0; i<l; i++) {
			listOfChildrenInDir.add(directories[i].toPath().getFileName().toString());
			System.out.println(listOfChildrenInDir.get(i));
		}
		return listOfChildrenInDir;

	}

	protected abstract C newChild(String name);
}
