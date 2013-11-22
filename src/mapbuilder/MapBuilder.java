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
        
        long startTime = System.nanoTime();
        
        //If you want to run Eric's generator uncomment this
        DLAGenerator gen = new DLAGenerator(1, .4);
        GameMap map = gen.generateMap(true, 100, 100);
        DijkstraPathFinder path = new DijkstraPathFinder();
        System.out.println(path.isConnected(true, map));
        
        //If you want to run Fernando's generator uncomment this
        //SteeringDungeonGenerator gen = new SteeringDungeonGenerator();
        //GameMap map = gen.generateMap(true, 100, 70, 70);
        
        //If you want to run Marco's and Antonio's generator uncomment this
        //MazeGenerator gen = new MazeGenerator(100, 100);
        //GameMap map = gen.generateMap(true, 50 , 50);
        
        long endTime = System.nanoTime();
        long ellapsedTime = endTime - startTime;
        System.out.println("Time it took to complete dungeon "
                + ellapsedTime/1000000
                + " milliseconds or "
                + ellapsedTime/1000000000
                + " seconds.");
        

        
        //prints out map into ascii
        /*
        for(int j = 0; j < map.getY(); j++){
            for(int i = 0; i < map.getX(); i++){
                System.out.print(map.getCell(i,j).asciiChar());
            }
            System.out.println();
        }
        */
    }    
}
