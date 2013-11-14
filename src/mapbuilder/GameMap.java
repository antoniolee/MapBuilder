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
public class GameMap {

    private int x;
    private int y;
    private IMapCell[][] cells;

    public GameMap(int x, int y) {
        this.x = x;
        this.y = y;
        cells = new IMapCell[x][y];
    }

    public GameMap(GameMap map) {
        this.x = map.getX();
        this.y = map.getY();
        cells = new IMapCell[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; i++) {
                setCell(i, j, map.getCell(i, j));
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public IMapCell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setCell(int x, int y, IMapCell cell) {
        cells[x][y] = cell;
    }

    public void fillMap(IMapCell cell) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                setCell(i, j, cell);
            }
        }
    }

    public boolean nextToCell(int x, int y, IMapCell cell) {
        int xL = x - 1;
        int xR = x + 1;
        int yL = y - 1;
        int yR = y + 1;

        
        
        if (xL >= 0 && getCell(xL, y).equals(cell)) {
            return true;
        }
        if (xR < getX() && getCell(xR, y).equals(cell)) {
            return true;
        }
        if (yL >= 0 && getCell(x, yL).equals(cell)) {
            return true;
        }
        if (yR < getY() && getCell(x, yR).equals(cell)) {
            return true;
        }
        return false;
    }
}
