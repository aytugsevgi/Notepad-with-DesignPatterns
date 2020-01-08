
package projectx;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.length;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.text.Font.font;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class ProjectX  {

    private JFrame jframe = new JFrame();
    private JDialog jdialog = new JDialog();
    private JFileChooser fc = new JFileChooser();
    private SimpleAttributeSet sas;
     
    FileReader fr = null;
    
    
    UndoManager manager = new UndoManager(); //undocommand e parametre göndermek için oluşturuldu.
    UndoableEditEvent editEvent; // belgede yapılan değişiklikleri algılayıp kaydetmek için oluşturuldu
    UndoableCommand undoablecommand = new UndoCommand(manager,editEvent);
    
    
    StyleContext context = new StyleContext();  //textPane stillendirilmesiyle ile ilgili nesneler
    StyledDocument document = new DefaultStyledDocument(context); // "" ""
    JTextPane textPane = new JTextPane(document);
    
    Document doc = textPane.getDocument();
    //üstteki 3 satır sağa sola hizalamak için oluşturulmuştur.
    Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
    
    MenuContext menuContext = new MenuContext();  //menuStrateji pattern context
    HizalaContext hizaContext = new HizalaContext(); //hizalaStrateji pattern context
    Adapter adapter;
    
    
    public ProjectX(){
        /* JFrame OLUŞTURMA İLE İLGİLİ İŞLEMLER */
        JScrollPane scrollPane = new JScrollPane(textPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height*2/3;
        int width = screenSize.width *2/3;
        jframe.setPreferredSize(new Dimension(width, height));
        sas = new SimpleAttributeSet(); // textpanel stillendirilmesiyle ilgili nesne
        FileFilter txtFilter = new FileNameExtensionFilter("Plain text", "txt");  //add filter for txt file
        fc.setFileFilter(txtFilter);
        jframe.add(scrollPane);
        // Menu oluşturma.
        JMenuBar menuBar = new JMenuBar();
        jframe.setJMenuBar(menuBar);
        JMenu file = new JMenu("Dosya");
        JMenu ara = new JMenu("Ara veya Değiştir");
        JMenu edit = new JMenu("Düzenle");
        JMenu hizala = new JMenu("Hizala");
        JMenu yazitipi = new JMenu("Yazı Tipi");
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(ara);
        menuBar.add(hizala);
        menuBar.add(yazitipi);
        yazitipi.add(kalinyap);
        yazitipi.add(normalyap);
        yazitipi.add(boyutarttir);
        yazitipi.add(boyutazalt);
        hizala.add(solahizala);
        hizala.add(ortahizala);
        hizala.add(sagahizala);
        hizala.add(ikiyanahizala);
        edit.add(Undo);
        ara.add(Search);
        ara.add(Degistir);
        //Menu items ekleme
        file.add(Open);
        file.add(Save);
        file.addSeparator();
        file.add(Exit);
        jframe.setTitle("Yeni Metin Belgesi");
        jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true); 
        /*  **************************************************  */
        
        
         doc.addUndoableEditListener(new UndoableEditListener() {
        public void undoableEditHappened(UndoableEditEvent evt) {
            undoablecommand.doit(evt);
             // belgedeki her işlem kaydedilir.
        }
        });
    }
    
    
    //add out action
    Action kalinyap = new AbstractAction("Kalın Font") { //Fontu kalınlaştırır
        @Override
        public void actionPerformed(ActionEvent e) {
            adapter = new Adapter(textPane);
            adapter.kalinYap();
    }};
    Action normalyap = new AbstractAction("Normal Font") {  //Fontu normal yapar
        @Override
        public void actionPerformed(ActionEvent e) {
            adapter = new Adapter(textPane);
            adapter.normalYap();
    }};
    Action boyutarttir = new AbstractAction("Yazı Boyutu Arttır") { //Yazı boyutunu arttırır (+4)
        @Override
        public void actionPerformed(ActionEvent e) {
            adapter = new Adapter(textPane);
            adapter.boyutArttir();
    }};
    Action boyutazalt = new AbstractAction("Yazı Boyutu Azalt") { // Yazı boyutunu azaltır (-4)
        @Override
        public void actionPerformed(ActionEvent e) {
            adapter = new Adapter(textPane);
            adapter.boyutAzalt();
    }};
    Action solahizala = new AbstractAction("Sola Hizala") { //metni sola yaslar
        @Override
        public void actionPerformed(ActionEvent e) {
            //strategy pattern called
             hizaContext.setHizalaStrategy(new SolaHizalaStrategy());
             hizaContext.selectHizaItem(style, textPane);}};
    Action ortahizala = new AbstractAction("Ortala") {    //metni ortalar 
        @Override
        public void actionPerformed(ActionEvent e) {
            //strategy pattern called
            hizaContext.setHizalaStrategy(new OrtaHizalaStrategy());
            hizaContext.selectHizaItem(style, textPane);}};
    Action sagahizala = new AbstractAction("Sağa Hizala") {  //metni sağa yaslar
        @Override
        public void actionPerformed(ActionEvent e) {        
            //strategy pattern called
            hizaContext.setHizalaStrategy(new SagaHizalaStrategy());
            hizaContext.selectHizaItem(style, textPane);}};
    Action ikiyanahizala = new AbstractAction("İki Yana Yasla") {
        @Override
        
        public void actionPerformed(ActionEvent e) {
          hizaContext.setHizalaStrategy(new IkiYanaYaslaStrategy());  //metni iki yana yaslar
          hizaContext.selectHizaItem(style, textPane);
    } 

    };
    
    Action Undo = new AbstractAction("Geri Al") {       //command patterni kullanarak undo işlemi yapar.
        @Override
        public void actionPerformed(ActionEvent e) {
            undoablecommand.execute();
              //command den implement olan undoCommand classından execute fonk. çağırıyoruz.

    }   
    };
    
    Action Search = new AbstractAction("Kelime Ara") {  //Metindeki kelimeyi arar.Yeni strategy nesnesi oluşturur parametre olarak contexte gönderir.
        @Override
        public void actionPerformed(ActionEvent e) {
            menuContext.setMenuStrategy(new SearchStrategy());
            menuContext.selectMenuItem(null,null,textPane,null,jframe);}};
    Action Degistir = new AbstractAction("Kelime Değiştir") { // arama işlemi gibidir ekstra olarak kelime değiştir strategy pattern kullanılır.
        @Override
        public void actionPerformed(ActionEvent e) {
            menuContext.setMenuStrategy(new DegistirStrategy());
            menuContext.selectMenuItem(null,null,textPane,null,jframe);}};
    Action Open = new AbstractAction("Dosya Aç") {
        @Override
        public void actionPerformed(ActionEvent e) {
            //menu context ile gerekli strateji nesnesini yaratma.
           if (fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
               menuContext.setMenuStrategy(new OpenStrategy());
               menuContext.selectMenuItem(fc.getSelectedFile().getAbsolutePath(),fc, textPane, fr,jframe);}}};
    //saveaction
    Action Save = new AbstractAction("Kaydet") {
        @Override
        public void actionPerformed(ActionEvent e) {
            //menu context ile gerekli strateji nesnesini yaratma.
            menuContext.setMenuStrategy(new SaveStrategy());
            menuContext.selectMenuItem(null, fc, textPane, fr,jframe);}};
    Action Exit = new AbstractAction("Çıkış") {
        @Override
        public void actionPerformed(ActionEvent e) {
            //menu context ile gerekli strateji nesnesini yaratma.
            menuContext.setMenuStrategy(new ExitStrategy());
            menuContext.selectMenuItem(null, fc, textPane, fr,jframe);}};
    
    
   
    public static void main(String[] args) {
        
        new ProjectX();
        
    }
    
    
}
