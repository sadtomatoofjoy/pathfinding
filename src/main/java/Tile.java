import java.util.ArrayList;
import java.util.List;

public class Tile {

  public int x;

  public int y;

  public int weight = 1;

  // cost
  public double g;

  public Tile parent;

  public Tile(int x, int y, int weight, Tile parent) {
    this.weight = weight;
    this.x = x;
    this.y = y;
    this.parent = parent;
    this.g = (parent != null ? parent.g : 0) + weight;
  }

  // the path taken to get to this tile
  public List<Tile> getPath() {
    List<Tile> path = parent == null ? new ArrayList<Tile>() : parent.getPath();
    path.add(this);
    return path;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + x;
    result = prime * result + y;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    Tile other = (Tile) obj;
    return this.x == other.x && this.y == other.y;
  }

}