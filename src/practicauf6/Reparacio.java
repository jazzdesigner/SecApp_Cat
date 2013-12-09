
package practicauf6;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe que Representa reparació i extén Servei
 * @author Jaime, Mario
 */
public class Reparacio extends Servei {
    private int temps;
    private List<Sensor> llistaRepSensors = new ArrayList<>();
    

    public Reparacio(String codi, Date date, SistemaSeguretat sisSeg) {
        super(codi, date, sisSeg);
    }

    public Reparacio(int temps, String codi, Date date, SistemaSeguretat sisSeg) {
        super(codi, date, sisSeg);
        this.temps = temps;
    }

    
    /*Métodes de lògica de l'aplicació*/
    
    /**
     * Métode que calcula el preu
     * @return retorna el preu calculat (Per implementar)
     */
    @Override
    public double calcularPreu() {
        return 0;
    }

    /**
     * Métode que diu si hi ha sensors
     * @return retorna un booleà
     */
    public boolean hiHaSensors() {
        if (llistaRepSensors.isEmpty()) return false;
        return true;
    }
    
    /**
     * Métode que repara un sensor d'una llista donat un sensor
     * @param s sensor a reparar
     */
    public void repararSensor(Sensor s) {
        s.llistaReparacio.add(this);
        llistaRepSensors.remove(s);
    }
    
    /**
     * Métode que obté la llista dels sensors reparats
     * @return 
     */
    public List<Sensor> obtenirListaSensorRep() {
        return llistaRepSensors;
    }
    
    
    /*Métodes accessors i lectors*/
    
    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }
    
    
    /*Métodes d'objecte*/

    @Override
    public String toString() {
        return super.toString();
    }
}
