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
public class MapCellWall implements IMapCell {

    private static MapCellWall instance;

    protected MapCellWall() {
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public char asciiChar() {
        return '#';
    }

    @Override
    public Color tileColor() {
        return Color.BLACK;
    }

    public static IMapCell getInstance() {
        if (instance == null) {
            instance = new MapCellWall();
        }
        return instance;
    }

}
