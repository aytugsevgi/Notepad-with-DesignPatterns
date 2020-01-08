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
public class UndoCommand implements UndoableCommand {
    public UndoManager manager ;
    UndoableEditEvent editEvent;
    public UndoCommand(UndoManager manager,UndoableEditEvent editEvent){
        this.manager = manager;
        this.editEvent = editEvent;
    }
    
    @Override
    public void execute() {
       if(manager.canUndo()){
            manager.undo();
        }
        else{
            System.out.println("Geri alınacak öğe yok..");
            
        }
           
    }
    @Override
    public void doit(UndoableEditEvent editEvent) {
        manager.addEdit(editEvent.getEdit());
    }
    
}
