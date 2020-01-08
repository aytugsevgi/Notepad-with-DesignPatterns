/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import javax.swing.text.SimpleAttributeSet;

/**
 *
 * @author ASUS
 */
public interface AdapterInterface {
    
    SimpleAttributeSet sas = new SimpleAttributeSet(); //stillendirme için oluşturuldu
    
    public void kalinYap();
    public void normalYap();
    public void boyutArttir();
    public void boyutAzalt();
}
