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
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i < 0 || i >= this.x) {
                    continue;
                }
                if (j < 0 || j >= this.y) {
                    continue;
                }
                if (i == x && j == y) {
                    continue;
                }
                if (getCell(i, j).equals(cell)) {
                    return true;
                }
            }
        }
        return false;
    }
}
