import java.util.Random;

/* this is a class to represent the map */
public class GameMap {

  public int[][] map;

  public int startX;

  public int startY;

  public int endX;

  public int endY;

  public GameMap(int[][] map, int startX, int startY, int endX, int endY) {
    this.map = map;
    this.startX = startX;
    this.startY = startY;
    this.endX = endX;
    this.endY = endY;
  }
  
  public GameMap(int size, int possibleWeights, boolean ending) {
    map = new int[size][size];
    Random rand = new Random();
    startX = rand.nextInt(size);
    startY = rand.nextInt(size);
    if(ending) {
      endX = rand.nextInt(size);
      endY = rand.nextInt(size);
      map[endX][endY] = 1;
    }
    map[startX][startY] = 0;
    for (int i = 0; i < size; i++) {
      for (int y = 0; y < size; y++) {
        if (!((i == startX && y == startY) || (ending && i == endX && y == endY))) {
          map[i][y] = rand.nextDouble() <= 0.75 ? rand.nextInt(possibleWeights) + 1 : -1;
        }
      }
    }
  }
}