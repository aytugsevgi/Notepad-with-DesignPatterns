/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;
import javax.swing.text.Highlighter;

public class DegistirStrategy implements MenuStrategy {
    private JFrame jframe2 = new JFrame();
    JPanel panel = new JPanel();
    private JTextField textDegistir= new JTextField();
    private JTextField textDegistir2 = new JTextField();
    JButton buttonDegistir = new JButton("Değiştir");
    ArrayList<String> list = new ArrayList<String>(); 
    ListIterator iterator;
    
    public DegistirStrategy(){
        /* frame oluşturma işlemleri */
        panel.setLayout(null);
        textDegistir.setBounds(10,10,120,20);
        textDegistir2.setBounds(140,10,120,20);
        buttonDegistir.setBounds(160,35,80,35);
        panel.add(buttonDegistir);
        panel.add(textDegistir);
        panel.add(textDegistir2);
        jframe2.add(panel);
        jframe2.setDefaultCloseOperation(HIDE_ON_CLOSE);
        jframe2.setLocationRelativeTo(null);
        jframe2.setSize(270,120);
        jframe2.setResizable(false);
        jframe2.setVisible(true);
        /* frame oluşturma işlemleri */
    }
    @Override
    public void executeProcess(String fileName, JFileChooser fc, JTextPane textPane, FileReader fr, JFrame jframe) {
        buttonDegistir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String degistirilenWord = textDegistir.getText();       //değiştirilmek istenen kelimeyi tutar 
                String yeniWord = textDegistir2.getText();              //yeni kelimeyi tutar.
                
                list.clear(); //run aşamasında 1'den fazla kez butona tıklanırsa eski listeyi temizler.
                String siradakiKelime = null;
                String allText = textPane.getText();
                String[] words = allText.split(",| |\\.|\\. |, |  ");
          
                for (int i=0;i<words.length;i++){
                    if (!words[i].equals("")){
                        list.add(words[i]); //her yazılan kelimeyi listede tutar.
                        
                    }
                }
                iterator = list.listIterator();  // listeyi iterator e atıyor.
                
                while(iterator.hasNext()){   //iterator deki kelimeler bitene kadar döngüye giriyor.
                    siradakiKelime = (String) iterator.next();      //sıradaki kelimeyi bir değişkende tutuyor
                    if(textDegistir.getText().equalsIgnoreCase(siradakiKelime)){    //Eğer aradığım kelime sıradakiKelimeye eşitse;
                        
                        allText = bul(allText,degistirilenWord,yeniWord);     // methoda gönderip alltexte değiştiriliyor.Bazı uç durumlar kontrol ediliyor.   
                        textPane.setText(allText); //textPanele değişmiş olan metnimizi gönderiyoruz.
                    }   
                }
            }
        });
            
    }

    public String bul(String allText,String degistirilenWord,String yeniWord){
        String[] temp = allText.split(" ");
        for(int i = 0;i<temp.length;i++){
            if((temp[i].endsWith(",") || temp[i].endsWith(".")) & temp[i].length()-1 == degistirilenWord.length() ){
                boolean esitmi = true;
                for(int k = 0;k<temp[i].length()-1;k++){
                    if (!((temp[i].charAt(k)+"").equalsIgnoreCase(degistirilenWord.charAt(k)+""))){
                        esitmi = false;
                    }
                }
                if(esitmi){
                    temp[i] = yeniWord + temp[i].charAt(temp[i].length()-1);
                }
            }
            if(temp[i].equalsIgnoreCase(degistirilenWord)){
                temp[i] = yeniWord;
            }
        }
        allText = "" ;
        for(int i =0;i<temp.length;i++){

            if(i<temp.length-1){
               allText += temp[i]+" " ; 
            }
            else{
                allText += temp[i];  //cümlenin sonuna boşluk bırakmaması için if else'e soktuk.
            }
        }
        return allText;
    }
    
}
