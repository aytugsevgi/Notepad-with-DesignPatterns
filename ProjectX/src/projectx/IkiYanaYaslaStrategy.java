/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

/**
 *
 * @author ASUS
 */
public class IkiYanaYaslaStrategy implements HizalaStrategy{

    @Override
    public void executeProcess(Style style, JTextPane textArea) {
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_JUSTIFIED);
    }
    
}
