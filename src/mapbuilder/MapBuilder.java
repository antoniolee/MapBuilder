/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mapbuilder;

/**
 *
 * @author Eric
 */
public class MapBuilder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        GameMap map = new GameMap(100,100);
        map.fillMap(MapCellWall.getInstance());
        map.fillMap(1,1,98,98, MapCellBlue.getInstance());
        map.fillMap(2,2,97,97, MapCellFloor.getInstance());
        Visualizer.displayMap(map);
        */
        
        DLAGenerator gen = new DLAGenerator(1, .4);
        GameMap map = gen.generateMap(true, 100, 100);
        for(int j = 0; j < map.getY(); j++){
            for(int i = 0; i < map.getX(); i++){
                System.out.print(map.getCell(i,j).asciiChar());
            }
            System.out.println();
        }
    }
}
