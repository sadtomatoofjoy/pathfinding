
public class ExampleGameMaps {

  public static GameMap exampleMap1;

  public static GameMap exampleMap2;

  public static GameMap exampleMap3;

  static {
    int[][] map = new int[6][5];
    map[0][0] = 1;
    map[1][0] = 1;
    map[2][0] = 1;
    map[3][0] = 1;
    map[4][0] = 1;
    map[5][0] = 1;

    map[0][1] = 1;
    map[1][1] = 1;
    map[2][1] = 1;
    map[3][1] = 1;
    map[4][1] = 1;
    map[5][1] = 1;

    map[0][2] = 1;
    map[1][2] = 1;
    map[2][2] = 1;
    map[3][2] = 1;
    map[4][2] = 1;
    map[5][2] = 1;

    map[0][3] = 1;
    map[1][3] = 1;
    map[2][3] = -1;
    map[3][3] = -1;
    map[4][3] = -1;
    map[5][3] = 1;

    map[0][4] = 1;
    map[1][4] = 1;
    map[2][4] = 1;
    map[3][4] = 1;
    map[4][4] = 1;
    map[5][4] = 1;

    exampleMap1 = new GameMap(map, 3, 1, 4, 4);
    
    int[][] map2 = new int[6][5];
    map2[0][0] = 1;
    map2[1][0] = 1;
    map2[2][0] = 1;
    map2[3][0] = 1;
    map2[4][0] = 1;
    map2[5][0] = 1;

    map2[0][1] = 1;
    map2[1][1] = 1;
    map2[2][1] = 1;
    map2[3][1] = 1;
    map2[4][1] = 5;
    map2[5][1] = 1;

    map2[0][2] = 1;
    map2[1][2] = 1;
    map2[2][2] = 1;
    map2[3][2] = 1;
    map2[4][2] = 1;
    map2[5][2] = 1;

    map2[0][3] = 1;
    map2[1][3] = 1;
    map2[2][3] = -1;
    map2[3][3] = -1;
    map2[4][3] = -1;
    map2[5][3] = 5;

    map2[0][4] = 1;
    map2[1][4] = 1;
    map2[2][4] = 1;
    map2[3][4] = 1;
    map2[4][4] = 1;
    map2[5][4] = 1;

    exampleMap2 = new GameMap(map2, 3, 1, 4, 4);

    int[][] map3  = new int[20][20];
    for (int i = 0; i < map3.length; i++)
      for (int j = 0; j < map3[0].length; j++)
        map3[i][j] = 1;

    exampleMap3 = new GameMap(map3, 5, 5, 19, 19);  
  }
}