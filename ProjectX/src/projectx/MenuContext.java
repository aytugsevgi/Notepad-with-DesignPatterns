
package projectx;

import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextPane;


public class MenuContext {
    private MenuStrategy strategy;
    public void setMenuStrategy(MenuStrategy strategy){
        this.strategy = strategy;
    }
    public void selectMenuItem(String fileName,JFileChooser fc,JTextPane textArea,FileReader fr,JFrame jframe){
        strategy.executeProcess(fileName, fc, textArea, fr,jframe);
    }
            
}
