/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodedamas.estrategia;

import checkers.main.Checkers;
import checkers.model.Checker;
import checkers.model.CheckerSide;
import checkers.model.Piece;
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
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author felipe
 */
public class Test1JogadaCompletaGanhaVermelhaTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private String baseFileName = "";

    TesteEstrategiaJogo s;
    Checkers ck;

    public Test1JogadaCompletaGanhaVermelhaTest() {
        String current = "";
        try {
            current = new java.io.File(".").getCanonicalPath();
        } catch (IOException ex) {
            Logger.getLogger(Test1JogadaCompletaGanhaVermelhaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        baseFileName = current + File.separator + "relatorio" + File.separator;
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
        String fileName = baseFileName + "capturaumapedra.jpeg";
        File imageFile = new File(fileName);
        try {
            Files.deleteIfExists(imageFile.toPath());
        } catch (IOException ex) {
            Logger.getLogger(Test1JogadaCompletaGanhaVermelhaTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        fileName = baseFileName + "ganhavermelhas.jpeg";
        imageFile = new File(fileName);
        try {
            Files.deleteIfExists(imageFile.toPath());
        } catch (IOException ex) {
            Logger.getLogger(Test1JogadaCompletaGanhaVermelhaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Este teste executa toda uma execucao do jogo até que o jogado com peça
     * vermelha ganhe.
     */    
    @Test
    public void testJogadaCompletaGanhaVermelhas() {
        //red x,y x, y
        String jogadas = "5,8,1,4,7,2,7,4,5,6,4,5,6,3,7,4,4,5,3,4,4,3,2,5,3,6,4,5,3,2,4,3,7,6,6,5,7,4,5,6,8,7,7,6,2,1,3,2,7,6,6,5,5,2,6,3,6,5,5,4,4,3,6,5,4,5,3,4,2,3,4,5,1,6,3,4,4,1,5,2,4,7,3,6,6,3,7,4,3,6,5,4,3,2,4,3,5,4,3,2,7,4,8,5,3,2,4,1";
        int contaJogadas = 0;
        String[] listas = jogadas.split(",");
        for (int i = 0; i < listas.length;) {
            Point from = new Point();
            Point to = new Point();
            from.x = Integer.valueOf(listas[i]);
            i++;
            from.y = Integer.valueOf(listas[i]);
            i++;
            to.x = Integer.valueOf(listas[i]);
            i++;
            to.y = Integer.valueOf(listas[i]);
            i++;
            contaJogadas++;
            
            if(s.isValidToMove(from, to)){            
                s.movePiece(from, to);
                s.verificaGanhador();
                
                ScreenShot.makeScreenshot(ck, baseFileName + "\\jogadavermelhaganha\\ganhavermelhas"+ contaJogadas +".jpeg");
            }
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Test1JogadaCompletaGanhaVermelhaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        ScreenShot.makeScreenshot(ck, baseFileName + "ganhavermelhas.jpeg");
        String content = outContent.toString();
         
        
        Point test = new Point();
        test.x = 4;
        test.y = 1;        
        boolean existePeca = false;
        Checker piece = (Checker) s.board.getPiece(test);
        if(piece != null){
            existePeca = true;
        }
        Assert.assertEquals(piece.isSide(CheckerSide.RED) , true);        
        Assert.assertEquals(existePeca, true);
        Assert.assertEquals(27, contaJogadas);
    }

}
