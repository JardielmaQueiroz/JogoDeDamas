/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodedamas.estrategia;

/**
 *
 * @author felip
 */
public class TesteEstrategiaJogo extends EstrategiaDeJogo {

    
    public TesteEstrategiaJogo(boolean computer){        
        super(computer);
    }
    
    public TesteEstrategiaJogo(){
        super(true);
    }
    
    @Override
    public void exibeMensagem(String mensagem) {
        System.out.println(mensagem);
    }
    
     @Override
    public void exibeMensagemFim(String mensagem) {
        System.out.println(mensagem);
    }
    
    
}
