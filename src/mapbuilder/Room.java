/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mapbuilder;

/**
 *
 * @author Fernando
 */
public class Room {
    int x, y, width, height;
    int r, g, b;
    
    public Room(){
    }
    
    public Room(int width, int height){
      this.width = width;
      this.height = height;
      this.x = 0;
      this.y = 0;
   }
   public Room(int x, int y, int width, int height){
      this.width = width;
      this.height = height;
      this.x = x;
      this.y = y;
   }
}
