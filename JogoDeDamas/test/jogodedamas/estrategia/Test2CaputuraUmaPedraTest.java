/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodedamas.estrategia;

import checkers.main.Checkers;
import checkers.model.Checker;
import checkers.model.CheckerSide;
import java.awt.Point;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import jogodedamas.screenshot.ScreenShot;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author felipe
 */
public class Test2CaputuraUmaPedraTest {

    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private String baseFileName = "";
    
    TesteEstrategiaJogo s;
    Checkers ck;

    public Test2CaputuraUmaPedraTest() {
        
        String current ="";
        try {
            current = new java.io.File(".").getCanonicalPath();
        } catch (IOException ex) {
            Logger.getLogger(Test2CaputuraUmaPedraTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        baseFileName = current + File.separator + "relatorio"  + File.separator;
        
        s = new TesteEstrategiaJogo(false);
        ck = new Checkers("teste", s);
    }

    
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
    

    @Before
    public void setUp() {
        
        String fileName = baseFileName+ "capturaumapedra.jpeg";
        File imageFile = new File(fileName);
        try {         
            Files.deleteIfExists(imageFile.toPath());
        } catch (IOException ex) {
            Logger.getLogger(Test2CaputuraUmaPedraTest.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
    }

    /**
     * Este teste executa toda uma execucao do jogo até que o jogado com peça vermelha ganhe.
     */    
    @Test
    public void testCapturaUmaPedra() throws IOException {      
        
        //red x,y x, y
        String jogadas = "7,6,6,5,6,3,7,4,8,7,7,6,5,2,6,3,5,6,4,5,7,4,5,6";
        int countaJogadas = 0;       
        String[] listas = jogadas.split(",");
        for(int i=0; i< listas.length;){
            Point from = new Point();
            Point to = new Point();
            from.x = Integer.valueOf(listas[i]);i++;
            from.y = Integer.valueOf(listas[i]);i++;
            to.x = Integer.valueOf(listas[i]);i++;
            to.y = Integer.valueOf(listas[i]);i++;            
            countaJogadas++;                    
           if(s.isValidToMove(from, to)){            
                s.movePiece(from, to);
                s.verificaGanhador();
            }           
        }             
        String content = outContent.toString();
        
        ScreenShot.makeScreenshot( ck, baseFileName+ "capturaumapedra.jpeg");                        
        
        Point test = new Point();
        test.x = 6;
        test.y = 5;        
        
       boolean existePeca = false;
       Checker check  = (Checker) s.board.getPiece(test);
        
       if(s.board.getPiece(test) != null && check.isSide(CheckerSide.RED)){
           existePeca = true;
       }        
       assertEquals( existePeca, false );     
        
        
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Test2CaputuraUmaPedraTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
}
