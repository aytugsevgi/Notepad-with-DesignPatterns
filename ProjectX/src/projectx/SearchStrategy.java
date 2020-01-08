
package projectx;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;
import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class SearchStrategy implements MenuStrategy {
    static MyHighlightPainter myHighlightPainter = new MyHighlightPainter(
      Color.lightGray);
    String searchWord = null;
    ArrayList<String> list = new ArrayList<String>(); //tüm kelimeleri tutmak için yaratıldı.
    ListIterator iterator; //listede gezinmek için yaratıldı
    int bitSay = 0; //JTextpane de bit tutmak hesaplamak için oluşturuldu. 
    
    // ********* JFRAME OLUŞTURULMUŞTUR ***************
    
    protected JFrame jframe2 = new JFrame();
    JPanel panel = new JPanel();
    private JTextField textSearch = new JTextField();
    JButton buttonSearch = new JButton("Ara");
    
   
    public SearchStrategy(){
    panel.setLayout(null);
    textSearch.setBounds(10,10,120,20);
    buttonSearch.setBounds(32,35,80,35);
    panel.add(textSearch);
    panel.add(buttonSearch);
    jframe2.add(panel);
    jframe2.setDefaultCloseOperation(HIDE_ON_CLOSE);
    jframe2.setLocationRelativeTo(null);
    jframe2.setSize(145,120);
    jframe2.setResizable(false);
    jframe2.setVisible(true);
    
    // ********* JFRAME OLUŞTURULMUŞTUR ***************
    
    }
    @Override
    public void executeProcess(String fileName, JFileChooser fc, JTextPane textPane, FileReader fr, JFrame jframe) {

        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeHighlights(textPane); //yeni arama yapılınca eski aramanın highlightını kaldırır.
                bitSay=0; list.clear(); //yeni arama yapılınca eski listeyi sıfırlar.
                Highlighter hilite = textPane.getHighlighter();
                searchWord = textSearch.getText(); //arama kısmına yazılan kelimeyi tutar.
                String siradakiKelime = null;
                String allText = textPane.getText().toLowerCase();
                String[] words = allText.split(",| |\\.|\\. |, |  "); //textteki tüm kelimeleri ayırıp diziye atıyoruz.
          
                for (int i=0;i<words.length;i++){  //diziyi listeye atıyoruz.
                    if (!words[i].equals("")){
                        list.add(words[i]); //her yazılmış kelimeyi listede tutar.
                        
                    }
                }
                iterator = list.listIterator();  //listeyi iteratore atıyoruz.
                int searchWordLenght=0;
                while(iterator.hasNext()){  //kelime sayısı boyunca döngüde kalır.
                    siradakiKelime = (String) iterator.next();   //siradaki kelimeyi tutar.
                    if(searchWord.equalsIgnoreCase(siradakiKelime)){    //büyük küçük harf duyarlılığını önemsemez.Aranan kelimeye eşit mi ?
                        searchWordLenght = searchWord.length(); 
                        bitSay = allText.indexOf(searchWord,bitSay);  //aranan kelimenin textte gördüğü ilk offseti tutar."(aranan_kelime, nereden_aramaya_başlıyım)"
                        try {
                        hilite.addHighlight(bitSay, bitSay + searchWordLenght, myHighlightPainter); //bulunan kelinin tüm harflerini boyar.
                        bitSay+=1;
                        }
                        catch (BadLocationException ex) {
                        Logger.getLogger(SearchStrategy.class.getName()).log(Level.SEVERE, null, ex); //highlightta try catch zorunlu
                        }   
                    }
                    else{
                        bitSay+=siradakiKelime.length()+1;  //aranan kelime sıradaki kelime değilse siradaki kelime kadar bit ileri ötelenir
                    }
                    
                }
            }
            
        });
                    //yazı yazılmaya başlanınca highlight ortadan kalkar.
        textPane.addKeyListener(new KeyListener() {

                    @Override
                    public void keyTyped(KeyEvent e) {}
                        
                    @Override
                    public void keyReleased(KeyEvent e) {}
                        
                    @Override
                    public void keyPressed(KeyEvent e) {
                        removeHighlights(textPane);                
                    }
                });
    }
    
    
    
                //aranan kelimenin highlight ını kaldıran method.
     public static void removeHighlights(JTextPane textComp) {
        Highlighter hilite = textComp.getHighlighter();
        Highlighter.Highlight[] hilites = hilite.getHighlights();
        
        for (int i = 0; i < hilites.length; i++) {
            if (hilites[i].getPainter() instanceof MyHighlightPainter) {
            hilite.removeHighlight(hilites[i]);
      }
    }
  }
    
    
    
   
}
