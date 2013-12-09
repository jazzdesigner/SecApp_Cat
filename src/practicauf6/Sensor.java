
package practicauf6;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstracta que representa Sensor
 * @author Jaime, Mario
 */
public abstract class Sensor implements Comparable<Sensor>{
    private String codi;
    private double preu;
    List<Reparacio> llistaReparacio = new ArrayList<>();
    
    public Sensor(String codi, double preu) {
        this.codi = codi;
        this.preu = preu;
    }

    
    /*Métodes accessors*/

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }
    
    
    
    /*Métodes d'Objecte*/
    @Override
    public String toString() {
        return "\nSensor: " + "codi: " + codi + "\npreu: " + preu;
    }
    
    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(obj==null) return false;
        if(!(obj instanceof Client)) return false;
        if (getCodi().equals(((Sensor)obj).getCodi())) return true;
                
        return false;
    }
    
     public int compareTo(Sensor o){
        if (Integer.parseInt(this.getCodi()) > Integer.parseInt(o.getCodi())) return 1;
        else if (Integer.parseInt(this.getCodi()) < Integer.parseInt(o.getCodi())) return -1;
        else return 0;
    }
    
}
