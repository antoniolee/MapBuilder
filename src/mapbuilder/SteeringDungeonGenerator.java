/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapbuilder;

import java.awt.Color;
import java.util.Random;
import static java.lang.Math.floor;

/**
 *
 * @author Fernando and Ashwin
 */
public class SteeringDungeonGenerator {
    
    Random rand;
    Room[] rooms;
    boolean visual;
    Visualizer visualizer = new Visualizer();

    //empty constructor
    public SteeringDungeonGenerator() {
        rand = new Random();
    }

    //random constructor
    public SteeringDungeonGenerator(long seed) {
        rand = new Random(seed);
    }
    
    public void generateRooms(int numberOfRooms, int dungeonWidth, int dungeonHeight) {
        rooms = new Room[numberOfRooms];
        for (int i = 0; i < numberOfRooms; i++) {
            //makes a new room (x, y, width, height) and stiks it into the array
            rooms[i] = new Room(new Vector2(rand.nextInt(dungeonWidth / 3) + dungeonWidth / 4, rand.nextInt(dungeonHeight / 3) + dungeonHeight / 4));
            Room.roomList.add(rooms[i]);
        }
    }
    
    public GameMap generateMap(boolean visual, int width, int height, int numberOfRooms){
        //System.out.println("START");
        this.visual = visual;
        GameMap gameMap = new GameMap(width, height);
        //fills map with just walls
        gameMap.fillMap(MapCellWall.getInstance());
        generateRooms(numberOfRooms, width, height);
        //iterates through all the currently made rooms
        boolean roomsSettled = false;
        int count = 0;
        try{
        while (!roomsSettled) {
            roomsSettled = true;
            gameMap.fillMap(MapCellWall.getInstance());
            for (Room room : rooms) {
                room.Update();
                //iterates through the x & y coordinates (width and height of the room)
                for (int x = (int) room.position.x; x < room.position.x - 1 + room.width; x++) {
                    for (int y = (int) room.position.y; y < room.position.y - 1 + room.height; y++) //sets cell to a floor tile
                    {
                        if (x < 0 || x > width || y < 0 || y > height) {
                            continue;
                        }
                        if (x == (int) room.position.x || x == (int) room.position.x + room.width-1 || y == (int) room.position.y || y == (int) room.position.y + room.height-1)
                            gameMap.setCell(x, y, MapCellFloorColor.getInstance(Color.GRAY));
                        else
                            gameMap.setCell(x, y, MapCellFloor.getInstance());

                    }
                }
                if (!room.settled) {
                    roomsSettled = false;
                }
                
                
            }
            Thread.sleep(5);
            count++;
            //System.out.println(count);
            
            //displays visual along the way
            if (visual) {
                visualizer.displayMap(gameMap);
            }
        }
        } catch (InterruptedException ex) {
            //Logger.getLogger(MapBuilder.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Message printer interrupted");
        }
        
        //final visual
        if (visual) {
            visualizer.displayMap(gameMap);
        }
        return gameMap;
    }

    public GameMap makeCooridor(GameMap gameMap, int x1, int y1, int x2, int y2){
        //makes the maxes x1 and y1
        /*
        if( x2 > x1 ){
            int tempx = x1;
            x1 = x2;
            x2 = tempx;
        }
        if( y2 > y1 ){
            int tempy = y1;
            y1 = y2;
            y2 = tempy;
        }
        if(x1 == x2){
            //for( int i = y)
        }
        */
        int fidelity = 50;
        float deltaX = x2-x1;
        float deltaY = y2-y1;
        
        float stepAmountX = deltaX/fidelity;
        float stepAmountY = deltaY/fidelity;
        float tempX = x1, tempY = y1;
        float prevX = x1, prevY = y1;
        for( tempX = x1; tempX < x2; tempX += stepAmountX){
            //plot the floor
            gameMap.setCell((int)floor(tempX), (int)floor(tempY), MapCellFloor.getInstance());
            if(tempX != prevX ) gameMap.setCell((int)floor(tempX), (int)floor(tempY)-1, MapCellFloor.getInstance());
            prevX = (int)floor(tempX);
            prevY = (int)floor(tempY);
            tempY += stepAmountY;
        }
        return gameMap;
    }
}
