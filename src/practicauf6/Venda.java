
package practicauf6;

import java.util.Date;

/**
 * Classe que representa Venda i extén Servei
 * @author Jaime, Mario
 */
public class Venda extends Servei{

    public Venda(String codi, Date date, SistemaSeguretat sisSeg) {
        super(codi, date, sisSeg);
    }
    
    
    /*Métodes de lògica de l'aplicació*/
    //por implementar
    @Override
    public double calcularPreu() {
        return 0;
    }

    /*Métodes d'objecte*/

    @Override
    public String toString() {
        return super.toString();
    }
    
}
