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
            retMap.setCell(rand.nextInt(x), rand.nextInt(y), MapCellFloor.getInstance());
        }
System.out.println(numFloor);
        for (int i = 0; i < numFloor; i++) {
            doWalk(retMap);
        }

        return retMap;
    }

    private void doWalk(GameMap retMap) {
        System.out.println("walk");
        int x = rand.nextInt(retMap.getX());
        int y = rand.nextInt(retMap.getY());
        while (!retMap.getCell(x, y).isPassable()) {
            x = rand.nextInt(retMap.getX());
            y = rand.nextInt(retMap.getY());
        }
        //System.out.println("\tinit");
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
            x = Math.max(0, Math.min(x, retMap.getX()));
            y = Math.max(0, Math.min(y, retMap.getY()));
            System.out.println(x + " " + y);
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
