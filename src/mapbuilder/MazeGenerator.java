/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mapbuilder;

import java.util.Random;
import java.util.LinkedList;
import java.util.Iterator;



/**
 *
 * @author Marco and Antonio
 */
public class MazeGenerator  implements IMapGenerator{
    
    Random rand;
    boolean visual;
    Visualizer visualizer = new Visualizer();
    int floorX;
    int floorY;

    LinkedList<Pair> drillers = new LinkedList();
    
    public MazeGenerator(int x, int y) {
        this.floorX = x;
        this.floorY = y;
        rand = new Random();
    }

    @Override
    public GameMap generateMap(boolean visual, int floorX, int floorY) {
        
        this.visual = visual; 
        GameMap retMap = new GameMap(floorX,floorY);
        retMap.fillMap(MapCellWall.getInstance());
        boolean[][] maze = new boolean[floorX][floorY];
        
        for(int i = 0; i < floorX; i++)
            for(int j = 0; j < floorY; j++)
                maze[i][j] = false;     
        drillers.addLast(new Pair(floorX/2, floorY/2));
        while( drillers.size() > 0 ) {
            Iterator it = drillers.iterator();
            int index = 0;
            Pair m = drillers.get(index);
            while(index < drillers.size()) {
                boolean remove_driller = false;
                switch(rand.nextInt(4)) {
                    case 0:
                        m.setSecond((int)m.getSecond() - 2);
                        if((int)m.getSecond() < 0 ||
                           maze[(int)m.getFirst()][(int)m.getSecond()] ){
                            remove_driller = true;
                            break;
                        }
                        maze[(int)m.getFirst()][(int)m.getSecond()+1] = true;
                        retMap.setCell((int)m.getFirst(), (int)m.getSecond()+1, MapCellFloor.getInstance());
                        break;
                    case 1:
                        m.setSecond((int)m.getSecond() + 2);
                        if((int)m.getSecond() >= floorY ||
                           maze[(int)m.getFirst()][(int)m.getSecond()] ){
                            remove_driller = true;
                            break;
                        }
                        maze[(int)m.getFirst()][(int)m.getSecond()-1] = true;
                        retMap.setCell((int)m.getFirst(), (int)m.getSecond()-1, MapCellFloor.getInstance());
                        break;
                    case 2:
                        m.setFirst((int)m.getFirst() - 2);
                        if((int)m.getFirst() < 0 ||
                           maze[(int)m.getFirst()][(int)m.getSecond()] ){
                            remove_driller = true;
                            break;
                        }
                        maze[(int)m.getFirst()+1][(int)m.getSecond()] = true;
                        retMap.setCell((int)m.getFirst()+1, (int)m.getSecond(), MapCellFloor.getInstance());
                        break;
                    case 3:

                        m.setFirst((int)m.getFirst() + 2);
                        if((int)m.getFirst() >= floorX ||
                           maze[(int)m.getFirst()][(int)m.getSecond()] ){                      
                            remove_driller = true;
                            break;
                        }
                        maze[(int)m.getFirst()-1][(int)m.getSecond()] = true;
                        retMap.setCell((int)m.getFirst()-1, (int)m.getSecond(), MapCellFloor.getInstance());
                        break;
                }
                if(remove_driller) {
                    drillers.remove(m);
                    if(drillers.size() > 0)
                        m = drillers.getLast();
                }
                else {
                    drillers.addLast(new Pair((int)m.getFirst(), (int)m.getSecond()));
                    drillers.addLast(new Pair((int)m.getFirst(), (int)m.getSecond()));
                    //different maze generation
                    //for(int i = 0; i < 10; i ++)
                        //drillers.addFirst(new Pair((int)m.getFirst(), (int)m.getSecond()));
                    maze[(int)m.getFirst()][(int)m.getSecond()] = true;
                    retMap.setCell((int)m.getFirst(), (int)m.getSecond(), MapCellFloor.getInstance());
                    m = drillers.getFirst();
                }
                if (visual) {
                    visualizer.displayMap(retMap);
                }
            }
        }

        return retMap;
        
    }
    
}
