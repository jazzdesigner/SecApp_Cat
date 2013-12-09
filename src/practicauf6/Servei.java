
package practicauf6;

import java.util.Date;

/**
 * Classe abstracta que representa Servei
 * @author Jaime, Mario
 */
public abstract class Servei {
    private String codi = "SERV";
    private Date date;
    private SistemaSeguretat sisSeg;
    private int numCodi;

    public Servei(String codi, Date date, SistemaSeguretat sisSeg) {
        this.codi = codi + numCodi++;
        this.date = date;
        this.sisSeg = sisSeg;
    }
    
    
    /*Métodes de lògica de l'aplicació*/
    
    /**
     * Métode que calcula el preu del Servei
     * @return retorna el preu
     */
    public abstract double calcularPreu();

    
    /*Métodes accessors i lectors*/

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    /*Métodes d'objecte*/

    @Override
    public String toString() {
        return "\ncodi: " + codi + "\n date: " + date + "\nsisSeg: " + sisSeg;
    }
    
     public int compareTo(Servei o){
        if (Integer.parseInt(this.getCodi()) > Integer.parseInt(o.getCodi())) return 1;
        else if (Integer.parseInt(this.getCodi()) < Integer.parseInt(o.getCodi())) return -1;
        else return 0;
    }
    
    
}
