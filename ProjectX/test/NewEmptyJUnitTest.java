/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import projectx.DegistirStrategy;

/**
 *
 * @author ASUS
 */
public class NewEmptyJUnitTest {
    
       
    @Test
    public void testBulDegistir() {
        DegistirStrategy degistir = new DegistirStrategy();
        String allText = "merhaba bu benim eski cümlem";
        String degistirilenWord = "eski"; String yeniWord = "yeni";
        allText = degistir.bul(allText, degistirilenWord, yeniWord);
        
        Assert.assertEquals("merhaba bu benim yeni cümlem",allText);
    }

    
}
