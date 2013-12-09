
package practicauf6;

/**
 * Classe que representa Infrarig, extén Sensor i implementa Infraroig
 * @author Jaime, Mario
 */
public class InfraroigImpl extends Sensor implements Infraroig {
    
    public int temperatura;

    public InfraroigImpl(int temperatura, String codi, double preu) {
        super(codi, preu);
        this.temperatura = temperatura;
    }

    
    /*Metodes*/

    @Override
    public int getTemperatura() {
        return temperatura;
    }

    @Override
    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    
    
    @Override
    public String toString() {
        return super.toString()+"\nTemperatura: " + temperatura;
    }
    
}
