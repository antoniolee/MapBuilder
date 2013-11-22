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
            rooms[i] = new Room(new Vector2(rand.nextInt(dungeonWidth / 4) + dungeonWidth / 4, rand.nextInt(dungeonHeight / 4) + dungeonHeight / 4));
            Room.roomList.add(rooms[i]);
        }
    }
 
    public GameMap generateMap(boolean visual, int width, int height, int numberOfRooms) {
        System.out.println("START");
        this.visual = visual;
        GameMap gameMap = new GameMap(width, height);
        //fills map with just walls
        gameMap.fillMap(MapCellWall.getInstance());
        generateRooms(numberOfRooms, width, height);
        //iterates through all the currently made rooms
        boolean roomsSettled = false;
        int count = 0;
        while (!roomsSettled) {
            roomsSettled = true;
            gameMap.fillMap(MapCellWall.getInstance());
            for (Room room : rooms) {
                room.Update();
                //iterates through the x & y coordinates (width and height of the room)
                for (int x = (int) room.position.x; x < room.position.x + room.width; x++) {
                    for (int y = (int) room.position.y; y < room.position.y + room.height; y++) //sets cell to a floor tile
                    {
                        gameMap.setCell(x, y, MapCellFloor.getInstance());
                    }
                }
                if (count == 100) {
                    System.out.println("QUIT");
                    break;
                }
                if (!room.settled) {
                    roomsSettled = false;
                }
                //displays visual along the way
                if (visual) {
                    Visualizer.displayMap(gameMap);
                }
            }
            count++;
            System.out.println(count);
 
        }
        System.out.println("DONE");
        //final visual
        if (visual) {
            Visualizer.displayMap(gameMap);
        }
        return gameMap;
    }
}