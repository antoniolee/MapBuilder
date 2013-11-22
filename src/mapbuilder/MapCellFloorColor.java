/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapbuilder;

import java.awt.Color;
import java.util.HashMap;

/**
 *
 * @author Eric
 */
public class MapCellFloorColor implements IMapCell {
    
    private static HashMap<Color, MapCellFloorColor> cells = new HashMap<Color, MapCellFloorColor>();
    private Color color;

    private MapCellFloorColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    @Override
    public char asciiChar() {
        return ' ';
    }

    @Override
    public Color tileColor() {
        return color;
    }

    public static IMapCell getInstance(Color color) {
        if (cells.get(color) == null) {
            cells.put(color, new MapCellFloorColor(color));
        }
        return cells.get(color);
    }

}
