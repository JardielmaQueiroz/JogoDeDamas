/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodedamas;

import checkers.main.Checkers;
import javax.swing.SwingUtilities;
import jogodedamas.estrategia.EstrategiaDeJogo;

/**
 *
 * @author Felipe
 */
public class JogoDeDamas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Runnable r = new Runnable() {
           @Override
           public void run() {
               EstrategiaDeJogo s = new EstrategiaDeJogo();
                Checkers ck = new Checkers("Jogo" , s);        
           }
       };
       // which on is more standard?
       // EventQueue.invokeLater(r);
       SwingUtilities.invokeLater(r);
        
    }
    
}
