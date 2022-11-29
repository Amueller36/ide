package org.example.folderhandling;
public abstract class UrlHasParentArtifact<P extends UrlArtifact<?, ?>, C> extends UrlArtifact<UrlArtifact<?, ?>, C> {
	protected final P parent;
	protected final String name;

	public UrlHasParentArtifact(P parent, String name) {
		super(parent.getPath().resolve(name));
		this.parent = parent;
		this.name = name;
	}
	
}
