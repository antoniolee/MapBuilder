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
public class MapCellBlue implements IMapCell {

    private static MapCellBlue instance;
    private MapCellBlue(){}
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
        return Color.BLUE;
    }

    public static IMapCell getInstance() {
        if (instance == null) {
            instance = new MapCellBlue();
        }
        return instance;
    }

}
