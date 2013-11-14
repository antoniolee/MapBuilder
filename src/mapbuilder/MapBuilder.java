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
        DLAGenerator gen = new DLAGenerator(1, .4);
        GameMap map = gen.generateMap(false, 30, 30);
        for(int i = 0; i < map.getX(); i++){
            for(int j = 0; j < map.getY(); j++){
                System.out.print(map.getCell(i,j).asciiChar());
            }
            System.out.println();
        }
    }
    
}
