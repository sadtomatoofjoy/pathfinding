import java.util.Comparator;

/*

*/
public class AstarContext extends DjikstraContext<AstarTile> {

  public AstarContext(GameMap gamemap) {
    super(gamemap);
  }

  public AstarTile solutionFound;

  @Override
  public boolean step() {
    // has ended
    if (solutionFound != null || openList.isEmpty()) {
      return true;
    } else {
      // process the next Tile
      AstarTile currentTile = openList.poll();
      closed.add(currentTile);

      // check if is ending
      if (currentTile.x == gamemap.endX && currentTile.y == gamemap.endY) {
        solutionFound = currentTile;
        return true;
      }
      // add adjacent Tiles to open List
      addValidAdjacentNodesToOpen(currentTile);
      return false;
    }
  }

  @Override
  protected Comparator<AstarTile> compare() {
    return (tile1, tile2) -> Double.compare(tile1.f, tile2.f);
  }

  @Override
  protected AstarTile createTile(int x, int y, int weight, AstarTile parent) {
    return new AstarTile(x, y, this.gamemap.endX, this.gamemap.endY, weight, parent,1.1);
  }

}