
package practicauf6;

/**
 * Classe que representa Volumetric i extén Sensor
 * @author Jaime, Mario
 */
public class Volumetric extends Sensor{
    private double radi;

    public Volumetric(double radi, String codi, double preu) {
        super(codi, preu);
        this.radi = radi;
    }
    
    /*Métodes*/

    public double getRadi() {
        return radi;
    }

    public void setRadi(double radi) {
        this.radi = radi;
    }

    @Override
    public String toString() {
        return super.toString()+"\nradi: " + radi;
    }
}
