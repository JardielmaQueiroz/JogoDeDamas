/*
    Esta classe controla as movimentações do jogo de damas
*/

package jogodedamas.estrategia;

import checkers.model.Checker;
import checkers.model.Piece;
import java.awt.Point;
import java.util.HashMap;

/**
 *
 * @author Jardielma
 */
public class MovimentaPeca extends EstrategiaDeJogo{
    
    public boolean movimenta(Point from, Point to, Checker piece,boolean inicioJogada, EstrategiaDeJogo m){
        HashMap<Point, Piece> tabuleiro = m.pegaTotosPontosTabuleiro();

        if(piece.getSide() == piece.getSide().BLACK && this.inicioJogada){
            return false;
        }
        else{
            this.inicioJogada = false;
            //exibeMensagem("Chamando analisaJogadaRealizada");
            tabuleiro = m.pegaTotosPontosTabuleiro();
            
            if (piece.getSide() == piece.getSide().BLACK){
                if((from.getY()+1 == to.getY() && from.getX() != to.getX()) || from.getY()-1 == to.getY() && from.getX() != to.getX()){
                    return m.isAvailableTargetForMove(to);
                }
                else{
                    if((from.getX()- 2 == to.getX() && (from.getY()+ 2 == to.getY()))){
                        Point ponto = new Point((int) from.getX()-1, (int) from.getY()+1);
                        if(tabuleiro.get(ponto) != null){
                            Checker piececandidata = m.getPiece(ponto);
                            if((piececandidata.getSide() == piececandidata.getSide().RED) && piece.getSide() == piece.getSide().BLACK){
                                m.movePiece(from,to);
                                m.removePiece(ponto);
                                return true;
                            }
                        }
                    }  
                    else{
                        if((from.getX()+ 2 == to.getX() && (from.getY()+ 2 == to.getY()))){
                            Point ponto = new Point((int) from.getX()+1, (int) from.getY()+1);
                            if(tabuleiro.get(ponto) != null){
                                Checker piececandidata = m.getPiece(ponto);
                                if((piececandidata.getSide() == piececandidata.getSide().RED) && piece.getSide() == piece.getSide().BLACK){
                                    m.movePiece(from,to);
                                    m.removePiece(ponto);
                                    return true;
                                }
                            }
                        } 
                    }
                    return false;
                }
            }
            else{
                if((from.getY()-1 == to.getY() && from.getX() != to.getX()) || from.getY()+1 == to.getY() && from.getX() != to.getX()){
                    return m.isAvailableTargetForMove(to);
                }
                else{
                    if((from.getX()+2 == to.getX() && (from.getY()- 2 == to.getY()))){
                        Point ponto = new Point((int) from.getX()+1, (int) from.getY()- 1);
                        if (tabuleiro.get(ponto) != null){
                            Checker piececandidata = m.getPiece(ponto);
                            if((piececandidata.getSide() == piececandidata.getSide().BLACK) && piece.getSide() == piece.getSide().RED){
                                m.movePiece(from,to);
                                m.removePiece(ponto);
                                return true;
                            }
                        }
                    }
                    else{
                        if((from.getX()-2 == to.getX() && (from.getY()-2 == to.getY()))){
                            Point ponto = new Point((int) from.getX()-1, (int) from.getY()-1);
                            if (tabuleiro.get(ponto) != null){
                                Checker piececandidata = m.getPiece(ponto);
                                if((piececandidata.getSide() == piececandidata.getSide().BLACK) && piece.getSide() == piece.getSide().RED){
                                    m.movePiece(from,to);
                                    m.removePiece(ponto);
                                    return true;
                                }
                            }
                        }
                    }
                    return false;
                }
            }
            
        }
    }
} 
