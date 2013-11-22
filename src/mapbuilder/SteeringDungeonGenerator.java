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
 * @author Fernando
 */
public class SteeringDungeonGenerator {
    Random rand;
    Room[] rooms;
    boolean visual;
    
    //empty constructor
    public SteeringDungeonGenerator(){
        rand = new Random();
    }
    //random constructor
    public SteeringDungeonGenerator(long seed){
        rand = new Random(seed);
    }
    
    public void generateRooms(int numberOfRooms, int dungeonWidth, int dungeonHeight){
        rooms = new Room[numberOfRooms];
        for( int i = 0; i <numberOfRooms; i++){
            //makes a new room (x, y, width, height) and stiks it into the array
            rooms[i] = new Room(
                    rand.nextInt(dungeonWidth-10),
                    rand.nextInt(dungeonHeight-10), 
                    rand.nextInt(10), rand.nextInt(10));
        }
    }
    
    public GameMap generateMap(boolean visual, int width, int height, int numberOfRooms){
        this.visual = visual;
        GameMap gameMap = new GameMap(width, height);
        //fills map with just walls
        gameMap.fillMap(MapCellWall.getInstance());
        generateRooms(numberOfRooms, width, height);
        //iterates through all the currently made rooms
        for (Room room : rooms) {
            //iterates through the x & y coordinates (width and height of the room)
            for (int x = room.x; x < room.x + room.width; x++)
                for( int y = room.y; y < room.y + room.height; y++)
                    //sets cell to a floor tile
                    gameMap.setCell(x, y, MapCellFloor.getInstance());
            
           //displays visual along the way
           if (visual) Visualizer.displayMap(gameMap);
        }
        
        //final visual
        if (visual) {
            Visualizer.displayMap(gameMap);
        }
        return gameMap;
    }
}
