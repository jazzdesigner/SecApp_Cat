
package practicauf6;

/**
 * Classe que representa Tactil i extén Sensor
 * @author Jaime, Mario
 */
public class Tactil extends Sensor{
    private boolean polsat;

    public Tactil(String codi, double preu) {
        super(codi, preu);
    }

    /*Métodes*/

    public boolean isPolsat() {
        return polsat;
    }

    public void setPolsat(boolean polsat) {
        this.polsat = polsat;
    }
    
    @Override
    public String toString() {
        return super.toString() + "\npolsat: "+polsat;
    }
}
