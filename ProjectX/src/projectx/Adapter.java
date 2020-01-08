/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author ASUS
 */
public class Adapter implements AdapterInterface {
    JTextPane textPane;
    public Adapter(JTextPane textPane){
        this.textPane = textPane;
    }
    @Override
    public void kalinYap(){
        StyleConstants.setBold(sas, true);
        String text = textPane.getText();
        textPane.getStyledDocument().setCharacterAttributes(0, text.length(), sas, false);    
    }
    public void normalYap(){
        StyleConstants.setBold(sas, false);
        String text = textPane.getText();
        textPane.getStyledDocument().setCharacterAttributes(0, text.length(), sas, false); 
    }
    public void boyutArttir(){
        int size = StyleConstants.getFontSize(sas);
        StyleConstants.setFontSize(sas, size+4);
        String text = textPane.getText();
        textPane.getStyledDocument().setCharacterAttributes(0,text.length(),sas,false);
    }
    public void boyutAzalt(){
        int size = StyleConstants.getFontSize(sas);
        StyleConstants.setFontSize(sas, size-4);
        String text = textPane.getText();
        textPane.getStyledDocument().setCharacterAttributes(0,text.length(),sas,false);
    }
}
