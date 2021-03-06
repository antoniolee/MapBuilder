/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapbuilder;

import java.util.Random;
import java.awt.Color;

/**
 *
 * @author Eric
 */
public class DLAGenerator implements IMapGenerator {

    int initFloors;
    double percentFill;
    Random rand;
    int numFloor;
    boolean visual;

    public DLAGenerator() {
    }

    public DLAGenerator(int initFloors, double percentFill) {
        this.initFloors = Math.max(1, initFloors);
        this.percentFill = percentFill;
        rand = new Random();
    }

    public DLAGenerator(int initFloors, double percentFill, long seed) {
        this.initFloors = Math.max(1, initFloors);
        this.percentFill = percentFill;
        rand = new Random(seed);
    }

    @Override
    public GameMap generateMap(boolean visual, int x, int y) {
        this.visual = visual;
        numFloor = (int) (x * y * percentFill);

        GameMap retMap = new GameMap(x, y);
        retMap.fillMap(MapCellWall.getInstance());
        for (int i = 0; i < initFloors; i++) {
            retMap.setCell(rand.nextInt(x - 2) + 1, rand.nextInt(y - 2) + 1, MapCellFloor.getInstance());
        }
        //System.out.println(numFloor);
        for (int i = 0; i < numFloor; i++) {
            doWalk(retMap);
        }

        if (visual) {
            Visualizer.displayMap(retMap);
        }
        Pair<Integer, Integer> pair;
        do {
            pair = new Pair<Integer, Integer>(rand.nextInt(x - 2) + 1, rand.nextInt(y - 2) + 1);
            if (visual) {
                IMapCell temp = retMap.getCell(pair);
                retMap.setCell(pair, MapCellEntry.getInstance());
                Visualizer.displayMap(retMap);
                retMap.setCell(pair, temp);
            }
        } while (!retMap.getCell(pair).isPassable());
        retMap.setEntry(pair);
        retMap.setCell(pair, MapCellEntry.getInstance());

        do {
            pair = new Pair<Integer, Integer>(rand.nextInt(x - 2) + 1, rand.nextInt(y - 2) + 1);
            if (visual) {
                IMapCell temp = retMap.getCell(pair);
                retMap.setCell(pair, MapCellExit.getInstance());
                Visualizer.displayMap(retMap);
                retMap.setCell(pair, temp);
            }
        } while (!retMap.getCell(pair).isPassable() && !retMap.getEntry().equals(pair));
        retMap.setExit(pair);
        retMap.setCell(pair, MapCellExit.getInstance());

        return retMap;
    }

    private void doWalk(GameMap retMap) {
        int x;
        int y;
        do {
            x = rand.nextInt(retMap.getX() - 2) + 1;
            y = rand.nextInt(retMap.getY() - 2) + 1;
        } while (retMap.getCell(x, y).isPassable());
        while (!retMap.nextToCell(x, y, MapCellFloor.getInstance())) {
            switch (getDirection()) {
                case UP:
                    y--;
                    break;
                case DOWN:
                    y++;
                    break;
                case LEFT:
                    x--;
                    break;
                case RIGHT:
                    x++;
                    break;
            }
            x = Math.max(1, Math.min(x, retMap.getX() - 2));
            y = Math.max(1, Math.min(y, retMap.getY() - 2));
            if (false) {
                retMap.setCell(x, y, MapCellFloorColor.getInstance(Color.BLUE));
                Visualizer.displayMap(retMap);
                retMap.setCell(x, y, MapCellWall.getInstance());
            }
        }
        retMap.setCell(x, y, MapCellFloor.getInstance());
        if (visual) {
            Visualizer.displayMap(retMap);
        }
    }

    private enum direction {

        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

    private direction getDirection() {
        return direction.values()[rand.nextInt(direction.values().length)];
    }
}
