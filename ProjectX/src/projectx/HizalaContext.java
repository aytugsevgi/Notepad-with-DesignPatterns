/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.Style;

/**
 *
 * @author ASUS
 */
public class HizalaContext {
    private HizalaStrategy strategy;
    public void setHizalaStrategy(HizalaStrategy strategy){
        this.strategy = strategy;
    }
    public void selectHizaItem(Style style, JTextPane textArea){
        strategy.executeProcess(style, textArea);
    }
}
