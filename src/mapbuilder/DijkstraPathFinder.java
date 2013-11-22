/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapbuilder;

import java.awt.Color;
import java.util.*;

/**
 *
 * @author Eric
 */
public class DijkstraPathFinder implements IPathFinder {

    @Override
    public boolean isConnected(boolean visual, GameMap map) {
        if(map.getEntry() == null || map.getExit() == null){
            return false;
        }
        
        LinkedList<Pair<Integer, Integer>> open = new LinkedList<Pair<Integer, Integer>>();
        LinkedList<Pair<Integer, Integer>> closed = new LinkedList<Pair<Integer, Integer>>();

        open.add(map.getEntry());

        Pair<Integer, Integer> C;
        Pair<Integer, Integer> N;
        Pair<Integer, Integer> S;
        Pair<Integer, Integer> E;
        Pair<Integer, Integer> W;
        while (!open.isEmpty()) {
            C = open.pop();
            W = new Pair<Integer, Integer>(C.getFirst() - 1, C.getSecond());
            E = new Pair<Integer, Integer>(C.getFirst() + 1, C.getSecond());
            S = new Pair<Integer, Integer>(C.getFirst(), C.getSecond() + 1);
            N = new Pair<Integer, Integer>(C.getFirst(), C.getSecond() - 1);

            if (N.equals(map.getExit())) {
                return true;
            }
            if (S.equals(map.getExit())) {
                return true;
            }
            if (E.equals(map.getExit())) {
                return true;
            }
            if (W.equals(map.getExit())) {
                return true;
            }

            checkTile(map, N, open, closed);
            checkTile(map, S, open, closed);
            checkTile(map, E, open, closed);
            checkTile(map, W, open, closed);
            closed.add(C);
            if(visual){
                GameMap temp = new GameMap(map);
                for(Pair<Integer, Integer> pair : open){
                    temp.setCell(pair, MapCellFloorColor.getInstance(Color.BLUE));
                }
                for(Pair<Integer, Integer> pair : closed){
                    temp.setCell(pair, MapCellFloorColor.getInstance(Color.YELLOW));
                }
                temp.setCell(temp.getEntry(), MapCellEntry.getInstance());
                
                Visualizer.displayMap(temp);
            }
        }

        return false;
    }

    private void checkTile(GameMap map, Pair<Integer, Integer> tile, Collection<Pair<Integer, Integer>> open, Collection<Pair<Integer, Integer>> closed) {
        if (map.validTile(tile)) {
            if (map.getCell(tile).isPassable()) {
                if (!open.contains(tile) && !closed.contains(tile)) {
                    open.add(tile);
                }
            }
        }
    }
}
