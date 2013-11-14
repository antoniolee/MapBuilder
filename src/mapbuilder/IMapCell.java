/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mapbuilder;
import java.awt.Color;

/**
 *
 * @author Eric
 */
public interface IMapCell {
    public boolean isPassable();
    public char asciiChar();
    public Color tileColor();
    
}
