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
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 *
 * @author ASUS
 */
public class SolaHizalaStrategy implements HizalaStrategy{
    
    @Override
    public void executeProcess(Style style, JTextPane textArea) {
        
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_LEFT);
    }
    
}
