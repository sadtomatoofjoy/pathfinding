public class AstarTile extends Tile {

  // heuristic
  public double h;

  // g+f
  public double f;

  public Tile parent;

  public AstarTile(int x, int y, int endX, int endY, int weight, Tile parent, double tieBreaker) {
    super(x, y, weight, parent);
    this.h = tieBreaker * Utils.manhattanDistance(this.x, this.y, endX, endY);
    this.f = this.g + this.h;
  }
  
}