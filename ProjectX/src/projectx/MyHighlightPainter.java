/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import java.awt.Color;
import javax.swing.text.DefaultHighlighter;

/**
 *
 * @author ASUS
 */
class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {

  public MyHighlightPainter(Color color) {
    super(color);
  }
}
