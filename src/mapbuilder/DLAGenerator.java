/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapbuilder;

import java.util.Random;

/**
 *
 * @author Eric
 */
public class DLAGenerator implements IMapGenerator {

    int initFloors;
    double percentFill;
    Random rand;
    int numFloor;

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
    public GameMap generateMap(boolean visualizer, int x, int y) {
        numFloor = (int) (x * y * percentFill);

        GameMap retMap = new GameMap(x, y);
        retMap.fillMap(MapCellWall.getInstance());
        for (int i = 0; i < initFloors; i++) {
            retMap.setCell(rand.nextInt(x-2)+1, rand.nextInt(y-2)+1, MapCellFloor.getInstance());
        }
        System.out.println(numFloor);
        for (int i = 0; i < numFloor; i++) {
            doWalk(retMap);
        }

        return retMap;
    }

    private void doWalk(GameMap retMap) {
        int x;
        int y;
        do {
            x = rand.nextInt(retMap.getX()-2)+1;
            y = rand.nextInt(retMap.getY()-2)+1;
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
            x = Math.max(1, Math.min(x, retMap.getX()-2));
            y = Math.max(1, Math.min(y, retMap.getY()-2));
        }
        retMap.setCell(x, y, MapCellFloor.getInstance());
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
