package org.example.christianFolderHandling;

import java.nio.file.Path;

/**
 *
 * @param <P> Parent type
 * @param <C> Child type
 */
public abstract class UrlArtifact<P, C> {
	protected final Path path;

	public UrlArtifact(Path path) {
		this.path = path;
	}

	public Path getPath() {
		return this.path;
	}

}
