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
public class Visualizer {
    
    private static VisualizerFrame vf;
    
    public static void displayMap(GameMap map){
        if(vf == null){
            vf = new VisualizerFrame();
        }
        vf.displayMap(map);
    }
    
}
