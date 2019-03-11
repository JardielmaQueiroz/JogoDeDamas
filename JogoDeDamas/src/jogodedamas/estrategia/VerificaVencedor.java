/*
    Classe responsavel por verifica quem é o vencedor e finalizar o jogo
 */
package jogodedamas.estrategia;

import checkers.model.Checker;
import java.awt.Point;

/**
 *
 * @author Jardielma
 */
public class VerificaVencedor {
    
    public boolean vencedor(Point to, Checker piece, EstrategiaDeJogo m){
        
        if(piece.getSide() == piece.getSide().RED && to.y == 1){
            m.exibeMensagemFim("Vencedor pedras vermelhas \n        Fim de jogo!!");
            return true; 
        }
        else{ 
            if(piece.getSide() == piece.getSide().BLACK && to.y == 8){
                m.exibeMensagemFim("Vencedor pedras pretas. \n      Fim de jogo!!");
                return true;
            }
        }
            
        return false;
    }
    
}
