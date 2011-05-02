package Server;

import java.util.ArrayList;
import java.util.Iterator;

public class RoadNode implements Node {
	
	private byte owner;
	private boolean built;
	private ArrayList<TileNode> tileNeighbors;
	private ArrayList<BuildNode> buildNeighbors;
	private int i;
	private int j;
	
	public RoadNode(int i, int j) {
		tileNeighbors = new ArrayList<TileNode>(2);
		buildNeighbors = new ArrayList<BuildNode>(2);
		this.owner = 0;
		built = false;
		this.i = i;
		this.j = j;
	}
	
	public Iterator<TileNode> getTileNeighbors() {
		return tileNeighbors.iterator();
	}
	
	public Iterator<BuildNode> getBuildNeighbors() {
		return buildNeighbors.iterator();
	}
	
	// Methods on owner
	public void setOwner(byte owner) {
		this.owner = owner;
	}
	public byte owner() {
		return this.owner;
	}
	
	//Methods for building
	public void build() {
		this.built = true;
	}
	public boolean built() {
		return this.built;
	}
	
	// Used to add neighbors.
	public void addTileNeighbor(TileNode tileNeighbor) {
		tileNeighbors.add(tileNeighbor);
	}
	public void addBuildNeighbor(BuildNode buildNeighbor) {
		buildNeighbors.add(buildNeighbor);
	}
	
	// Used to check if the node has neighbors.
	public boolean hasTileNeighbor(TileNode tileNeighbor) {
		return tileNeighbors.contains(tileNeighbor);
	}
	public boolean hasBuildNeighbor(BuildNode buildNeighbor) {
		return buildNeighbors.contains(buildNeighbor);
	}
	
	public String toString() {
		return "(" + i + ", " + j + ")";
	}

}
