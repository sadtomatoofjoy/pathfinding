import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;

public class DjikstraContext<T extends Tile> {

  public GameMap gamemap;

  public Set<T> closed = new HashSet<>();

  public PriorityQueue<T> openList;

  public DjikstraContext(GameMap gamemap) {
    this.gamemap = gamemap;
    openList = new PriorityQueue<>(compare());
    this.openList.add(createTile(gamemap.startX, gamemap.startY, gamemap.map[gamemap.startX][gamemap.startY], null));
  }

  protected Comparator<T> compare() {
    return (tile1, tile2) -> Double.compare(tile1.g, tile2.g);
  }

  protected T createTile(int x, int y, int weight, T parent) {
    return (T) new Tile(x, y, weight, parent);
  }

  public boolean step() {
    // has ended
    if (openList.isEmpty()) {
      return true;
    } else {
      // process the next Tile
      T currentTile = openList.poll();
      closed.add(currentTile);

      // add adjacent Tiles to open List
      addValidAdjacentNodesToOpen(currentTile);
      return false;
    }
  }

  protected void addValidAdjacentNodesToOpen(final T tile) {
    for (T neighbour : getNeighbors(tile)) {
      // check if tile has already been processed
      if (!closed.contains(neighbour)) {

        // check if tile is present in openList and if it is, check the cost of both
        // the existing one and the new one. replace if g is lower in the new
        Optional<T> existingTile = openList.stream().filter(tile::equals).findFirst();
        if (existingTile.isPresent() && existingTile.get().g > tile.g) {
          openList.remove(existingTile.get());
          openList.add(neighbour);
        } else if (!existingTile.isPresent()) {
          // add if doesn't exist
          openList.add(neighbour);
        }
      }
    }
  }

  private List<T> getNeighbors(T tile) {
    List<T> neighbors = new ArrayList<>();

    // take the tile to the left
    if (tile.x > 0 && gamemap.map[tile.x - 1][tile.y] != -1) {
      neighbors.add(createTile(tile.x - 1, tile.y, gamemap.map[tile.x - 1][tile.y], tile));
    }

    // take the tile to the right
    if (tile.x < gamemap.map.length - 1 && gamemap.map[tile.x + 1][tile.y] != -1) {
      neighbors.add(createTile(tile.x + 1, tile.y, gamemap.map[tile.x + 1][tile.y], tile));
    }

    // take the upper tile
    if (tile.y > 0 && gamemap.map[tile.x][tile.y - 1] != -1) {
      neighbors.add(createTile(tile.x, tile.y - 1, gamemap.map[tile.x][tile.y - 1], tile));
    }

    // take the lower tile
    if (tile.y < gamemap.map[0].length - 1 && gamemap.map[tile.x][tile.y + 1] != -1) {
      neighbors.add(createTile(tile.x, tile.y + 1, gamemap.map[tile.x][tile.y + 1], tile));
    }
    return neighbors;
  }

}
