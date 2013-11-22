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
public class MapCellEntry implements IMapCell {

    private MapCellEntry() {
    }
    private static MapCellEntry instance;

    @Override
    public boolean isPassable() {
        return true;
    }

    @Override
    public char asciiChar() {
        return 'O';
    }

    @Override
    public Color tileColor() {
        return Color.GREEN;
    }

    public static IMapCell getInstance() {
        if (instance == null) {
            instance = new MapCellEntry();
        }
        return instance;
    }

}
