import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import processing.core.PApplet;

public class GraphicalAstar extends PApplet {

  DjikstraContext<?> djikstra;
  static int rectsize = 40;
  static int mapSize = 25;
  static int textSize = 15;
  static int possibleweights = 1;
  boolean continuous;
  boolean step;

  public static void main(String[] args) {
    PApplet.main("GraphicalAstar");
  }

  @Override
  public void settings() {
    size(500, 300);
  }

  @Override
  public void setup() {
    size(500, 300);
    surface.setResizable(true);
    surface.setSize(mapSize * rectsize + 200, mapSize * rectsize);
    djikstra = new AstarContext(new GameMap(mapSize, possibleweights, true));
    ellipseMode(CORNER);
  }

  @Override
  public void draw() {
    clear();
    drawGameMap();

    drawClosedAndOpen();
    textSize(15);
    fill(0, 200, 0);
    ellipse(djikstra.gamemap.startX * rectsize, djikstra.gamemap.startY * rectsize, rectsize,rectsize);
    drawNumbers();
    

    drawPath();

    if (continuous || step) {
      djikstra.step();
      step = false;
    }

    drawPathIfMousePressed();

    String type = djikstra instanceof AstarContext ? "Astar" : "Dijkstra";
    textSize(40);
    fill(200);
    text(type, rectsize * mapSize, 60);
    List<Tile> queueTiles= djikstra.openList.stream().limit(3).collect(Collectors.toList());
    text("Open list", rectsize * mapSize, 200);
    textSize(30);
    int offset = 0;
    for(Tile t : queueTiles) {
      if(t instanceof AstarTile)
        text("x: " + t.x + " y: " + t.y + " f: " + ((AstarTile)t).f, rectsize * mapSize, 230 + offset);
      else
        text("x: " + t.x + " y: " + t.y + " cost: " + t.g, rectsize * mapSize, 230 + offset);
      offset += 35;
    }
  }

  private void drawNumbers() {
    // draw numbers
    for (int i = 0; i < djikstra.gamemap.map.length; i++) {
      for (int y = 0; y < djikstra.gamemap.map[0].length; y++) {
        fill(0);
        text(djikstra.gamemap.map[i][y], i * rectsize + rectsize / 2, y * rectsize + rectsize / 2);
      }
    }
  }

  private void drawPath() {
    // draw astar path
    if (djikstra instanceof AstarContext) {
      AstarContext astar = (AstarContext) djikstra;
      if (astar.solutionFound != null) {
        for (Tile t : astar.solutionFound.getPath()) {
          fill(200, 0, 0);
          rect(t.x * rectsize, t.y * rectsize, rectsize, rectsize);
        }
        textSize(40);
        fill(200);
        text("F: " + astar.solutionFound.f, rectsize * mapSize, 100);
      }
    }
  }

  private void drawPathIfMousePressed() {
    if (mousePressed) {
      Optional<Tile> selected = (Optional<Tile>) djikstra.closed.stream()
          .filter(tile -> isInsideTile(mouseX, mouseY, tile)).findAny();
      if (selected.isPresent()) {
        Tile sel = selected.get();
        for (Tile t : sel.getPath()) {
          fill(200, 0, 0);
          rect(t.x * rectsize, t.y * rectsize, rectsize, rectsize);
        }
        textSize(40);
        fill(200);
        text("Cost: " + sel.g, rectsize * mapSize, 140);
      }
    }
  }

  private void drawClosedAndOpen() {
    for (Tile t : djikstra.openList) {
      fill(255);
      strokeWeight(5);
      stroke(0, 150, 150);
      rect(t.x * rectsize, t.y * rectsize, rectsize, rectsize);
    }
    for (Tile t : djikstra.closed) {
      fill(0, 150, 150);
      rect(t.x * rectsize, t.y * rectsize, rectsize, rectsize);
    }
  }

  private void drawGameMap() {
    for (int i = 0; i < djikstra.gamemap.map.length; i++) {
      for (int y = 0; y < djikstra.gamemap.map[0].length; y++) {
        fill(255);
        stroke(0);
        strokeWeight(1);
        rect(i * rectsize, y * rectsize, rectsize, rectsize);
        if (djikstra.gamemap.map[i][y] == -1) {
          fill(100, 100, 100);
          rect(i * rectsize, y * rectsize, rectsize, rectsize);
        }
      }
    }
    if (djikstra instanceof AstarContext) {
      fill(250, 0, 0);
      rect(djikstra.gamemap.endX * rectsize, djikstra.gamemap.endY * rectsize, rectsize, rectsize);
    }
  }

  @Override
  public void keyReleased() {

    // if right
    if (key == CODED && keyCode == RIGHT) {
      step = true;
    }
    // djikstra
    if (key == 'd') {
      djikstra = new DjikstraContext<>(djikstra.gamemap);
    }
    if (key == 'a') {
      djikstra = new AstarContext(djikstra.gamemap);
    }

    // toggle continuous
    if (key == ' ') {
      continuous = !continuous;
    }
    
    if(key == '1') {
      if (djikstra instanceof AstarContext) {
        djikstra = new AstarContext(ExampleGameMaps.exampleMap1);
      } else {
        djikstra = new DjikstraContext<>(ExampleGameMaps.exampleMap1);
      }
      textSize = 40;
      rectsize = 100;
      mapSize = 6;
    }else if(key == '2') {
      if (djikstra instanceof AstarContext) {
        djikstra = new AstarContext(ExampleGameMaps.exampleMap2);
      } else {
        djikstra = new DjikstraContext<>(ExampleGameMaps.exampleMap2);
      }
      textSize = 40;
      rectsize = 100;
      mapSize = 6;
    }

    if(key =='3') {
      if (djikstra instanceof AstarContext) {
        djikstra = new AstarContext(ExampleGameMaps.exampleMap3);
      } else {
        djikstra = new DjikstraContext<>(ExampleGameMaps.exampleMap3);
      }
      textSize = 15;
      rectsize = 40;
      mapSize = 30;
    }

    if(key =='4') {
      textSize = 15;
      rectsize = 40;
      mapSize = 30;
      if (djikstra instanceof AstarContext) {
        djikstra = new AstarContext(new GameMap(mapSize, possibleweights, true));
      } else {
        djikstra = new DjikstraContext<>(new GameMap(mapSize, possibleweights, false));
      }
    }

    // reset
    if (key == 'r') {
      if (djikstra instanceof AstarContext) {
        djikstra = new AstarContext(djikstra.gamemap);
      } else {
        djikstra = new DjikstraContext<>(djikstra.gamemap);
      }
    }
    
  }

  private boolean isInsideTile(int x, int y, Tile t) {
    return x > t.x * rectsize && x < t.x * rectsize + rectsize && y > t.y * rectsize && y < t.y * rectsize + rectsize;
  }

}
