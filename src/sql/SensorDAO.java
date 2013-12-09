
package sql;

import com.mysql.jdbc.MysqlDataTruncation;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.sql.rowset.CachedRowSet;

/**
 * Classe DAO que representa Sensor
 * Es conecta amb la base de dades i fa els métodes referents a Sensor
 * @author Jaime, Mario
 */
public class SensorDAO {

    private static CachedRowSet crs = null;

    /**
     * Métode que crea un nou Sensor a la base de dades
     * @param tipusSensor tipus del sensor (Infrarroig | Tactil | Hibrid | Volumetric)
     * @param preu preu del sensor
     * @param temperatura temperatura del sensor
     * @param radi radi del sensor
     * @param LED conté LEDS?
     * @param polsat té algún tipus de polsació
     * @param codi_SistemaSeguretat codi del Sistema de seguretat al que pertany
     */
    public void altaSensor(String tipusSensor, double preu, int temperatura, double radi,
           String LED, boolean polsat,int codi_SistemaSeguretat) throws SQLException {

        try {
            
            BDAccessor bd= new BDAccessor();
            Connection conn = bd.obtenirConnexio();

            String consultaSQL = "INSERT INTO Sensor(tipusSensor,preu,temperatura,radi,LED,polsat,codi_SistemaSeguretat) VALUES ";
            PreparedStatement ps = conn.prepareStatement(consultaSQL + "(?,?,?,?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, tipusSensor);
            ps.setDouble(2, preu);
            ps.setInt(3, temperatura);
            ps.setDouble(4, radi);
            ps.setString(5, LED);
            ps.setBoolean(6, polsat);
            ps.setInt(7, codi_SistemaSeguretat);

            ps.executeUpdate();
            conn.commit();
            System.out.println("S'ha afegit el Sensor " + tipusSensor + ")\n");

        } catch (InputMismatchException ex) {
            System.out.println("Dades introduides incorrectament");
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (MysqlDataTruncation ex){
            System.out.println("Error: Dades amb longitud errònia");
        }
    }
    
    
    /**
     * Métode que mostra per consola la llista dels Sensors
     */
    public void llistarSensors() throws SQLException {
       
        try {
            
            BDAccessor bd= new BDAccessor();
            Connection conn = bd.obtenirConnexio();
            Statement stmt = conn.createStatement();
            ResultSet resultat = stmt.executeQuery("SELECT * FROM Sensor");
                        
                while (resultat.next()) {
                    System.out.println(
                            "codi: " + resultat.getString(1)
                            + ", tipusSensor: " + resultat.getString(2)
                            + ", preu: " + resultat.getDouble(3)
                            + ", temperatura: " + resultat.getInt(4)
                            + ", radi: " + resultat.getDouble(5)
                            + ", LED: " + resultat.getString(6)
                            + ", polsat: " + resultat.getBoolean(7)
                            + ", codi del Sistema de Seguretat:" + resultat.getInt(8));
                }
                resultat.close();
                
        } catch (InputMismatchException ex) {
            System.out.println("Dades introduides incorrectament");
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (MysqlDataTruncation ex){
            System.out.println("Error: Dades amb longitud errònia");
        }
    }

    /**
     * Métode que cerca un sensor donat el seu codi
     * @param c codi del sensor
     */
    public void cercaSensor(int c) throws SQLException {
        
        try {
        
            BDAccessor bd= new BDAccessor();
            Connection conn = bd.obtenirConnexio();

            String consultaSQL = "SELECT * FROM Sensor WHERE codi = ?";
            PreparedStatement pstmt = conn.prepareStatement(consultaSQL,PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, c);
            ResultSet resultat = pstmt.executeQuery();

            while (resultat.next()) {
                System.out.println(
                        "codi: " + resultat.getString(1)
                        + ", tipusSensor: " + resultat.getString(2)
                        + ", preu: " + resultat.getDouble(3)
                        + ", temperatura: " + resultat.getInt(4)
                        + ", radi: " + resultat.getDouble(5)
                        + ", LED: " + resultat.getString(6)
                        + ", polsat: " + resultat.getBoolean(7)
                        + ", codi del Sistema de Seguretat:" + resultat.getInt(8));
            }
                
                
        } catch (InputMismatchException ex) {
            System.out.println("Dades introduides incorrectament");
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (MysqlDataTruncation ex){
            System.out.println("Error: Dades amb longitud errònia");
        }
    }
    
    /**
     * Métode que cerca un sensor dins d'un sistema de seguretat segons el codi
     * @param c codi del sensor
     */
    public void cercaSensorsPerSistemaSeguretat(int c) throws SQLException {
        try{
            BDAccessor bd= new BDAccessor();
            Connection conn = bd.obtenirConnexio();

            String consultaSQL = "SELECT * FROM Sensor WHERE codi_SistemaSeguretat = ?";
            PreparedStatement pstmt = conn.prepareStatement(consultaSQL,PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, c);
            try (ResultSet resultat = pstmt.executeQuery()) {
                while (resultat.next()) {
                    System.out.println(
                            "codi: " + resultat.getString(1)
                            + ", tipusSensor: " + resultat.getString(2)
                            + ", preu: " + resultat.getDouble(3)
                            + ", temperatura: " + resultat.getInt(4)
                            + ", radi: " + resultat.getDouble(5)
                            + ", LED: " + resultat.getString(6)
                            + ", polsat: " + resultat.getBoolean(7)
                            + ", codi del Sistema de Seguretat:" + resultat.getInt(8));
                }
            }

        } catch (InputMismatchException ex) {
            System.out.println("Dades introduides incorrectament");
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (MysqlDataTruncation ex){
            System.out.println("Error: Dades amb longitud errònia");
        }
    }

    
    /**
     * Métode que permet modificar un Sensor 
     */
    public void modificarSensor() throws SQLException, IOException, ClassNotFoundException, MysqlDataTruncation {

        crs = new com.sun.rowset.CachedRowSetImpl();
        crs.setUrl("jdbc:mysql://localhost:3306/practicauf6?zeroDateTimeBehavior=convertToNull&relaxAutoCommit=true");
        crs.setUsername("root");
        crs.setPassword("");
        crs.setCommand("SELECT * FROM Sensor");
        crs.setConcurrency(CachedRowSet.CONCUR_UPDATABLE);
        crs.setType(CachedRowSet.TYPE_SCROLL_SENSITIVE);


        crs.execute();

        crs.last();
        System.out.println("nombre de registres " + crs.getRow());
        crs.beforeFirst();

        String opcio;
        do {
            System.out.println("S:seguent | A:anterior | P:primer | U:ultim | E:esborrar | M:modificar | X:SORTIR");
            Scanner entrada = new Scanner(System.in);
            opcio = entrada.next();

            switch (opcio) {
                case "S":
                    try {
                        if (crs.next()) {
                            System.out.println(
                                    "codi: " + crs.getString("codi")
                                    + ", tipusSensor: " + crs.getString("tipusSensor")
                                    + ", preu: " + crs.getDouble("preu")
                                    + ", temperatura: " + crs.getInt("temperatura")
                                    + ", radi: " + crs.getDouble("radi")
                                    + ", LED: " + crs.getString("LED")
                                    + ", polsat: " + crs.getBoolean("polsat"));
                        } else {
                            System.out.println("no queden mes registres");
                        }
                    } catch (InputMismatchException ex) {
                            System.out.println("Error al introduir dades.");
                    }
                    break;
                case "A":
                    try {
                        if (crs.previous()) {
                            System.out.println(
                                    "codi: " + crs.getString("codi")
                                    + ", tipusSensor: " + crs.getString("tipusSensor")
                                    + ", preu: " + crs.getDouble("preu")
                                    + ", temperatura: " + crs.getInt("temperatura")
                                    + ", radi: " + crs.getDouble("radi")
                                    + ", LED: " + crs.getString("LED")
                                    + ", polsat: " + crs.getBoolean("polsat"));
                        } else {
                            System.out.println("no queden mes registres");
                        }
                    } catch (InputMismatchException ex) {
                            System.out.println("Error al introduir dades.");
                    }
                    break;
                case "P":
                    try {
                        if (crs.first()) {
                            System.out.println(
                                    "codi: " + crs.getString("codi")
                                    + ", tipusSensor: " + crs.getString("tipusSensor")
                                    + ", preu: " + crs.getDouble("preu")
                                    + ", temperatura: " + crs.getInt("temperatura")
                                    + ", radi: " + crs.getDouble("radi")
                                    + ", LED: " + crs.getString("LED")
                                    + ", polsat: " + crs.getBoolean("polsat"));
                        } else {
                            System.out.println("no queden mes registres");
                        }
                    } catch (InputMismatchException ex) {
                            System.out.println("Error al introduir dades.");
                    }
                    break;
                case "U":
                    try {
                        if (crs.last()) {
                            System.out.println(
                                    "codi: " + crs.getString("codi")
                                    + ", tipusSensor: " + crs.getString("tipusSensor")
                                    + ", preu: " + crs.getDouble("preu")
                                    + ", temperatura: " + crs.getInt("temperatura")
                                    + ", radi: " + crs.getDouble("radi")
                                    + ", LED: " + crs.getString("LED")
                                    + ", polsat: " + crs.getBoolean("polsat"));
                        } else {
                            System.out.println("no queden mes registres");
                        }
                    } catch (InputMismatchException ex) {
                            System.out.println("Error al introduir dades.");
                    }
                    break;
                case "M":
                    try {
                        double preu = 0;
                        int temperatura = 0;
                        double radi = 0;
                        String LED = null;
                        boolean polsat = false;

                        System.out.println("Modifica el preu:");
                        preu = entrada.nextDouble();

                        switch (crs.getString("tipusSensor")) {
                            case "infraroig":
                                System.out.println("Modifica el temperatura:");
                                temperatura = entrada.nextInt();
                                break;
                            case "volumetric":
                                System.out.println("Modifica el radi:");
                                radi = entrada.nextDouble();
                                break;
                            case "hibrid":
                                System.out.println("Modifica el LED:");
                                LED = entrada.nextLine();
                                break;
                            case "tactil":
                                System.out.println("Polsat?(S/N):");
                                String opcioPolsat = entrada.next().toUpperCase();
                                switch (opcioPolsat) {
                                    case "S":
                                        polsat = true;
                                        break;
                                    case "N":
                                        polsat = false;
                                        break;
                                    default:
                                        polsat = false; // -> throw error
                                        break;
                                }

                                break;
                        }

                        crs.updateDouble("preu", preu);
                        crs.updateInt("temperatura", temperatura);
                        crs.updateDouble("radi", radi);
                        crs.updateString("LED", LED);
                        crs.updateBoolean("polsat", polsat);
                        crs.updateRow();
                    } catch (InputMismatchException ex) {
                            System.out.println("Error al introduir dades.");
                    }
                    break;
                case "E":
                    crs.deleteRow();
                    System.out.println("registre esborrat");

                    break;

                case "X":

                    crs.acceptChanges();
                    crs.close();
                    break;
                default:
                    System.out.println("opció incorrecta");
                    break;
            }

        } while (!opcio.equalsIgnoreCase("X"));

    }

}
