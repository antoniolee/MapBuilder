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
public class MapCellYellow implements IMapCell {

    private static MapCellYellow instance;
    private MapCellYellow(){}
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
        return Color.YELLOW;
    }

    public static IMapCell getInstance() {
        if (instance == null) {
            instance = new MapCellYellow();
        }
        return instance;
    }

}
