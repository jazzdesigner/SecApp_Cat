
package practicauf6;

/**
 * Interfície que permet implementar el métode per comparar objectes
 * @author Jaime, Mario
 * @param <T> Qualsevol tipus d'objecte
 */
public interface Comparable <T>{

    /**
     * Implementa la comparació entre objectes
     * @param object objecte a comparar
     * @return retorna un enter
     */
    public int compareTo (T object);
    
}