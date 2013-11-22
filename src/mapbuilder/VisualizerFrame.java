/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapbuilder;

import javax.swing.*;
import java.awt.Graphics;

/**
 *
 * @author Eric
 */
public class VisualizerFrame extends javax.swing.JFrame {

    private static GameMap map;

    public VisualizerFrame(GameMap map) {
        this.map = map;
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(map.getX() * 10 + 20, map.getY() * 10 + 50);
    }

    public VisualizerFrame() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void displayMap(GameMap map) {
        if(!this.isVisible()){
            this.setVisible(true);
        }
        this.map = map;
        setSize(map.getX() * 10, map.getY() * 10 + 30);
        this.paint(this.getGraphics());
    }

    @Override
    public void paint(Graphics g) {
        if (map == null) {
            return;
        }
        for (int j = 0; j < map.getY(); j++) {
            for (int i = 0; i < map.getX(); i++) {
                g.setColor(map.getCell(i, j).tileColor());
                g.fillRect(i * 10, 30 + j * 10, 10, 10);
            }
        }
    }
}
