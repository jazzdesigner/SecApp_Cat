
package practicauf6;

import java.util.ArrayList;

/**
 * Classe que conté llistes a fer servir
 * @author Jaime, Mario
 * @param <T> Tipus de Llista
 */
public class Lista <T>{

    private ArrayList<Servei> serveis = new ArrayList();

    /**
     * Métode que retorna la llista de serveis
     * @return retorna la llista de serveis
     */
    public ArrayList<Servei> getServeis() {
        return serveis;
    }
    
    /**
     * Métode que permet escollir un Servei donat l'índex
     * @param index Index del servei
     * @return 
     */
    public Servei escollirServeis(int index){
        return serveis.get(index);
    }
    
    /**
     * Métode que mostra els serveis per pantalla
     */
    public void llistarServeis(){
        for (Servei t: serveis){
            System.out.println("Num. del servei: "+serveis.indexOf(t) +". "+t);
        }
    }

}
