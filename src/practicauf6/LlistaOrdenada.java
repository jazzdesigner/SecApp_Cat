
package practicauf6;

import java.util.Collections;
import java.util.List;

/**
 * Métode que retorna la llista ordenada
 * @author Jaime, Mario
 */
public class LlistaOrdenada {
    
    /**
     * Métode que ordena una llista
     * @param l llista a ordenar
     * @return retorna la llista ordenada
     */
    public List ordenarLlista(List l) {
        Collections.sort(l);
        return l;
    }
}
