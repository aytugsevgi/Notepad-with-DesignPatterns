/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public interface MenuStrategy {
    public void executeProcess(String fileName,JFileChooser fc,JTextPane textArea,FileReader fr, JFrame jframe);
}
