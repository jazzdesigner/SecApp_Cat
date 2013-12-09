
package practicauf6;

/**
 * Classe que representa Hibrid, extén Volumetric i implementa infrarroig
 * És un tipus de Sensor
 * @author Jaime, Mario
 */
public class Hibrid extends Volumetric implements Infraroig{
    private String LED;
    private int temperatura;
    private Infraroig infraroig;

    public Hibrid(String LED, int temperatura, Infraroig infra, double radi, String codi, double preu) {
        super(radi, codi, preu);
        this.LED = LED;
        this.temperatura = temperatura;
        infraroig = new InfraroigImpl(temperatura, codi, preu);
    }

    
    /*Métodes*/
    public String getLED() {
        return LED;
    }

    public void setLED(String LED) {
        this.LED = LED;
    }

    @Override
    public int getTemperatura() {
        return infraroig.getTemperatura();
    }

    @Override
    public void setTemperatura(int temperatura) {
        infraroig.setTemperatura(temperatura);
    }

    @Override
    public String toString() {
        return "\nLED: " + LED + "\ntemperatura: " + temperatura;
    }
}
