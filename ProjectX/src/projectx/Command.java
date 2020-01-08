/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.UndoManager;

/**
 *
 * @author ASUS
 */
public interface Command {
    
    public void execute();
}
