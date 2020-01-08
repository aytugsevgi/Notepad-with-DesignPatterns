/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.stage.FileChooser;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class SaveStrategy implements MenuStrategy {
    
    @Override
    public void executeProcess(String fileName,JFileChooser fc,JTextPane textPane,FileReader fr,JFrame jframe) {
        if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
           FileWriter fw = null; 
            try {
                fw = new FileWriter(fc.getSelectedFile().getAbsolutePath()+ ".txt");
                textPane.write(fw);
                fw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
}