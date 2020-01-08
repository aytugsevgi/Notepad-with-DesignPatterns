
package projectx;

import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class OpenStrategy implements MenuStrategy  {
    @Override
    public void executeProcess(String fileName, JFileChooser fc, JTextPane textArea, FileReader fr,JFrame jframe) {
        try {
            fr = new FileReader(fileName);
            JTextPane textGecici = new JTextPane();
            textGecici.read(fr,null);
            fr.close();
            textArea.setText(textGecici.getText());
            jframe.setTitle(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
