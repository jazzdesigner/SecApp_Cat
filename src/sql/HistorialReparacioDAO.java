
package sql;

import com.mysql.jdbc.MysqlDataTruncation;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;

/**
 * Classe DAO que representa HistorialReparacio
 * Es conecta amb la base de dades i fa els métodes referents al Historial de Reparacio
 * @author Jaime, Mario
 */
public class HistorialReparacioDAO {

    /**
     * Métode que crea un nou Historial de Reparació
     * @param cSensor codi del sensor passat per parametre
     * @throws SQLException 
     */
    public void altaHistorialReparacio(int cSensor) throws SQLException {

        try{
            BDAccessor bd= new BDAccessor();
            Connection conn = bd.obtenirConnexio();

            int cServei = 0;
            String consultaSQL = "SELECT codi FROM Sensor";
            
            PreparedStatement pstmt = conn.prepareStatement(consultaSQL,PreparedStatement.RETURN_GENERATED_KEYS);
            
            ResultSet resultat = pstmt.executeQuery();
            if (resultat.last()) {
                cServei = resultat.getInt(1);
            }
            
            
            String consultaSQL2 = "INSERT INTO HistorialReparacio(codi_servei, codi_sensor) VALUES ";
            PreparedStatement ps = conn.prepareStatement(consultaSQL2 + "(?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1, cServei);
            ps.setInt(2, cSensor);

            ps.executeUpdate();
            conn.commit();
            
            System.out.println("S'ha afegit el SistemaSeguretat.\n");

            
        } catch (InputMismatchException ex) {
            System.out.println("Dades introduides incorrectament");
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (MysqlDataTruncation ex){
            System.out.println("Error: Dades amb longitud errònia");
        }

    }
    
    /**
     * Métode que mostra per consola la llista amb l'historial de Reparació.
     * Mostra el codi del servei i del sensor
     */
    public void llistarHistorialReparacio() throws SQLException {
        
        try {
        
            BDAccessor bd= new BDAccessor();
            Connection conn = bd.obtenirConnexio();
            Statement stmt = conn.createStatement();
            ResultSet resultat = stmt.executeQuery("SELECT * FROM HistorialReparacio");

            while (resultat.next()) {
                System.out.println(
                        "codi_servei: " + resultat.getString(1)
                        + ", codi_sensor: " + resultat.getString(2)); 
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
    
}
