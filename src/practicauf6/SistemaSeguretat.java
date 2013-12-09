package practicauf6;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe que representa SistemaSeguretat
 * @author Jaime, Mario
 */
public class SistemaSeguretat implements Comparable<SistemaSeguretat>{
    private String codi;
    private final double preuLlum = 300;
    private final double preuSirena = 500;
    /*derivat*/ private double preuCentral;
    private boolean venut;
    private List<Sensor> llistaSensors = new ArrayList<>();
    

    public SistemaSeguretat(String codi, double preuCentral) {
        this.codi = codi;
        /*derivat*/ this.preuCentral = preuCentral;
    }
    
    public SistemaSeguretat(String codi, double preuCentral, boolean venut) {
        this.codi = codi;
        /*derivat*/ this.preuCentral = preuCentral;
        this.venut = venut;
    }
    
    
    /*Métodes de lógica de l'aplicació*/
    
    /**
     * Métode que permet ventre el sistema de Sistema de Seguretat
     */
    public void vendreSistema(){
        this.venut=true;
    }
    
    /**
     * Métode que permet obtenir la llista de Sistema de Seguretat
     * @return retorna la llista de sensors
     */
    public List<Sensor> obtenirLlistaSensors() {
        return llistaSensors;
    }
    
    /**
     * Métode que permet afegir un Sistema de Seguretat
     * @param s sistema de seguretat a afegir
     */
    public void afegirSensor(Sensor s) {
        llistaSensors.add(s);
    }
    
    
    /*Métodes accessors i lectors*/

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    /*derivat*/public double getPreuCentral() {
        
        return preuCentral;
    }

    /*derivat*/public void setPreuCentral(double preuCentral) {
        this.preuCentral = preuCentral;
    }

    public boolean isVenut() {
        return venut;
    }

    public void setVenut(boolean venut) {
        this.venut = venut;
    }
    
    
    /*Métodes d'objecte*/

    @Override
    public String toString() {
        return "\nCodiSist: " + codi + "\npreuLlum: " + preuLlum + "\n preuSirena: "
                + preuSirena + "\n preuCentral: " + preuCentral + "\n venut: " + venut +
                "\nllistaSensors: " + llistaSensors;
    }

   
    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(obj==null) return false;
        if(!(obj instanceof SistemaSeguretat)) return false;
        if (getCodi().equals(((SistemaSeguretat)obj).getCodi())) return true;
                
        return false;
    }

    
    public int compareTo(SistemaSeguretat o){
        if (Integer.parseInt(this.getCodi()) > Integer.parseInt(o.getCodi())) return 1;
        else if (Integer.parseInt(this.getCodi()) < Integer.parseInt(o.getCodi())) return -1;
        else return 0;
    }

    
    
}
