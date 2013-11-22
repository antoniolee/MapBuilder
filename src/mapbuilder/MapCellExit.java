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
public class MapCellExit implements IMapCell {

    private MapCellExit() {
    }
    private static MapCellExit instance;

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
        return Color.RED;
    }

    public static IMapCell getInstance() {
        if (instance == null) {
            instance = new MapCellExit();
        }
        return instance;
    }

}
