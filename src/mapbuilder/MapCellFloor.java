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
public class MapCellFloor implements IMapCell {

    private MapCellFloor() {
    }
    private static MapCellFloor instance;

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
        return Color.WHITE;
    }

    public static IMapCell getInstance() {
        if (instance == null) {
            instance = new MapCellFloor();
        }
        return instance;
    }

}
