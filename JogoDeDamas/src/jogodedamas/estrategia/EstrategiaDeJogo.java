/*
    Esta classe controla as regras a serem implementadas no jogo de damas conforme
    definido na especificacao do trabalho.
*/

package jogodedamas.estrategia;

import checkers.model.Checker;
import checkers.model.GameStrategyCheckers;
import checkers.model.Piece;
import java.awt.Point;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Felipe
 */
public class EstrategiaDeJogo extends GameStrategyCheckers{
    
    public  static boolean inicioJogada = true;
    
    public EstrategiaDeJogo(){        
        super();
    }
    
     public EstrategiaDeJogo(boolean computer){        
        super(computer);
    }
     
    //pega uma peca dado um ponto no tabuleiro
    public Checker getPiece(Point point) {
        return (Checker) board.getPiece(point);
    }

    //remove uma pega dado um ponto no tabuleiro
    public void removePiece(Point from) {
        board.removePiece(from);
    }

    //dado uma peca move em um determinado ponto
    public void putPiece(Point to, Piece piece) {
        board.putPiece(to, piece);
    }

    //Dado um dois pontos, um de origem e outro de destino move a peça no tabuleiro
    @Override
    public void movePiece(Point from, Point to) {
        
        //exibeMensagem("Chamando movePiece");
        final Piece piece = getPiece(from);//pega uma peca dado um ponto
        if (piece != null) {
            removePiece(from);//remove uma peca de uma posicao e coloca na outra posicao
            putPiece(to, piece);//coloca a peca na nova posicao
        }
        
    }

    //Retorna todos os pontos do tabuleiro
    public HashMap<Point, Piece> pegaTotosPontosTabuleiro() {
        return (HashMap<Point, Piece>) board.getPoint2Piece();
    }

    //Este método é o responsavel pela jogado do computador.
    @Override
    public void movePieceComputer() {
        //exibeMensagem("Chamando movePieceComputer");
    }

    //Este método é o responsavel por analisar se a jogada é uma jogada válida.
    @Override
    public boolean analisaJogadaRealizada(Point from, Point to, Checker piece) {
        //exibeMensagem("Chamando analisaJogadaRealizada");        
        MovimentaPeca p = new MovimentaPeca();
        if (p.movimenta(from,to,piece,this.inicioJogada, this)){
           this.inicioJogada = false; 
        }
        if(this.inicioJogada == true){
            exibeMensagem("O jogador de peças vermelhas que deve iniciar o jogo!!");
        }
        this.verificaGanhador(to,piece);
        return p.movimenta(from,to,piece,this.inicioJogada,this);
        
    }
    
    @Override
    public boolean isAvailableTargetForMove(Point to){
        //Pega o tabuleiro.
        HashMap<Point, Piece> tabuleiro = pegaTotosPontosTabuleiro();
        return tabuleiro.get(to) == null;
    }
    //Este método é o responsavel por verificar se já existe um ganhador.    
    public boolean verificaGanhador(Point to,Checker piece) {        
        //exibeMensagem("verificaGanhador");
        
        VerificaVencedor g = new VerificaVencedor();
        return g.vencedor(to,piece,this);
         
    }

    
}
