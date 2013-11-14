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
        DLAGenerator gen = new DLAGenerator(1, .5);
        GameMap map = gen.generateMap(false, 60, 30);
        for(int j = 0; j < map.getY(); j++){
            for(int i = 0; i < map.getX(); i++){
                System.out.print(map.getCell(i,j).asciiChar());
            }
            System.out.println();
        }
    }
    
}
