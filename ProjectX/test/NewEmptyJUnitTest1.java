/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import projectx.HizalaContext;
import projectx.IkiYanaYaslaStrategy;
import projectx.OrtaHizalaStrategy;
import projectx.SagaHizalaStrategy;
import projectx.SolaHizalaStrategy;

/**
 *
 * @author ASUS
 */
public class NewEmptyJUnitTest1 {
    StyleContext context = new StyleContext();
    HizalaContext hizaContext = new HizalaContext();
    JTextPane textPane = new JTextPane();
    JTextPane textPane2 = new JTextPane();
    Document doc = textPane.getDocument();
    Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
    @Test
    public void tearDown() {
        hizaContext.setHizalaStrategy(new SolaHizalaStrategy());
        hizaContext.selectHizaItem(style, textPane);
        Object sonuc = StyleConstants.getAlignment(style);
        assertEquals(sonuc.toString(), "0");
        
        hizaContext.setHizalaStrategy(new OrtaHizalaStrategy());
        hizaContext.selectHizaItem(style, textPane);
        sonuc = StyleConstants.getAlignment(style);
        assertEquals(sonuc.toString(), "1");
        
        hizaContext.setHizalaStrategy(new SagaHizalaStrategy());
        hizaContext.selectHizaItem(style, textPane);
        sonuc = StyleConstants.getAlignment(style);
        assertEquals(sonuc.toString(), "2");
        
        hizaContext.setHizalaStrategy(new IkiYanaYaslaStrategy());
        hizaContext.selectHizaItem(style, textPane);
        sonuc = StyleConstants.getAlignment(style);
        assertEquals(sonuc.toString(), "3");
    }
}
