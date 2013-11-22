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
public class MapCoordinate {
    
    private int x;
    private int y;
    
    public MapCoordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int getx(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj.getClass() != MapCoordinate.class){return false;}
        MapCoordinate co = (MapCoordinate) obj;
        return co.getY() == this.getY() && co.getx() == this.getx();
    }
    
}
