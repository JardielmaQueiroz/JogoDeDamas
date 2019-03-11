/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodedamas.estrategia;

import checkers.main.Checkers;
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
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author felipe
 */
public class Test4JogadaInvalidaTest {

    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private String baseFileName = "";
    
    TesteEstrategiaJogo s;
    Checkers ck;

    public Test4JogadaInvalidaTest() {
        
        String current ="";
        try {
            current = new java.io.File(".").getCanonicalPath();
        } catch (IOException ex) {
            Logger.getLogger(Test4JogadaInvalidaTest.class.getName()).log(Level.SEVERE, null, ex);
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
        
        String fileName = baseFileName+ "jogadainvalida.jpeg";
        File imageFile = new File(fileName);
        try {         
            Files.deleteIfExists(imageFile.toPath());
        } catch (IOException ex) {
            Logger.getLogger(Test4JogadaInvalidaTest.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
    }

    /**
     * Este teste executa toda uma execucao do jogo até que o jogado com peça vermelha ganhe.
     */    
    @Test
    public void testMovimentacaoDiagonal() throws IOException {      
        
        //red x,y x, y
        String jogadas = "5,8,1,4";
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
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Test4JogadaInvalidaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        ScreenShot.makeScreenshot( ck, baseFileName+ "jogadainvalida.jpeg");
                
        Point test = new Point();
        test.x = 5;
        test.y = 8;        
        
        boolean existePeca = false;
        if(s.board.getPiece(test) != null){
            existePeca = true;
        }        
        assertEquals( existePeca, true );        
        
        test.x = 1;
        test.y = 4;           
        assertEquals( s.board.getPiece(test), null);                
        
        
        
        
    }
    
    
}
