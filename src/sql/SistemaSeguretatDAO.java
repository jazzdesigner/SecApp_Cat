
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
 * Classe DAO que representa SistemaSeguretat
 * Es conecta amb la base de dades i fa els métodes referents a SistemaSeguretat
 * @author Jaime, Mario
 */
public class SistemaSeguretatDAO {
    
    private static CachedRowSet crs = null;

    /**
     * Métode que crea un nou Sistema de Seguretat a la base de dades
     * @param preuCentral preu central o total del Sistema
     * @param venut està venut el sistema?
     */
    public void altaSistemaSeguretat(double preuCentral, boolean venut)
            throws SQLException {
        
        try{
        
            BDAccessor bd= new BDAccessor();
            Connection conn = bd.obtenirConnexio();

            String consultaSQL = "INSERT INTO SistemaSeguretat(preuCentral,venut) VALUES ";
            PreparedStatement ps = conn.prepareStatement(consultaSQL + "(?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setDouble(1, preuCentral);
            ps.setBoolean(2, venut);

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
     * Métode que mostra per consola la llista dels Sistemes de Seguretat
     */
    public void llistarSistemaSeguretat() throws SQLException  {
        
        try {
            
            BDAccessor bd= new BDAccessor();
            Connection conn = bd.obtenirConnexio();
            Statement stmt = conn.createStatement();
            ResultSet resultat = stmt.executeQuery("SELECT * FROM SistemaSeguretat");
                    
            while (resultat.next()) {
                System.out.println(
                        "Codi: " + resultat.getString(1)
                        + ", preu central: " + resultat.getString(2)
                        + ", s'ha venut?: " + resultat.getString(3));
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
     * Métode que mostra per consola la llista dels codis dels Sistemes de Seguretat
     */
    public void llistarCodiSistemaSeguretat() throws SQLException {
        
        try{
            
            BDAccessor bd= new BDAccessor();
            Connection conn = bd.obtenirConnexio();
            Statement stmt = conn.createStatement();
            ResultSet resultat = stmt.executeQuery("SELECT codi, venut FROM SistemaSeguretat");

            while (resultat.next()) {
                System.out.println(
                        "Codi: " + resultat.getString(1)
                        + ", s'ha venut?: " + resultat.getString(2));
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
     * Métode que cerca un Sistema de Seguretat donat el seu codi
     * @param c codi del Sistema de Seguretat
     */
    public void cercaSistemaSeguretat(int c) throws SQLException {
            try {

                BDAccessor bd= new BDAccessor();
                Connection conn = bd.obtenirConnexio();

                String consultaSQL = "SELECT * FROM SistemaSeguretat WHERE codi = ?";
                PreparedStatement pstmt = conn.prepareStatement(consultaSQL,PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, c);
                try (ResultSet resultat = pstmt.executeQuery()) {
                    while (resultat.next()) {
                        System.out.println(
                                "Codi: " + resultat.getString(1)
                                + ", preu central: " + resultat.getString(2)
                                + ", s'ha venut?: " + resultat.getString(3));
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Dades introduides incorrectament");
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
     * Métode que permet modificar un client 
     */
    public void modificarSistemaSeguretat() throws SQLException, IOException, ClassNotFoundException, MysqlDataTruncation {

        crs = new com.sun.rowset.CachedRowSetImpl();
        crs.setUrl("jdbc:mysql://localhost:3306/practicauf6?zeroDateTimeBehavior=convertToNull&relaxAutoCommit=true");
        crs.setUsername("root");
        crs.setPassword("");
        crs.setCommand("SELECT * FROM SistemaSeguretat");
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
                            "Codi: " + crs.getString("codi")
                            + ", preu central: " + crs.getString("preuCentral")
                            + ", s'ha venut?: " + crs.getString("venut"));
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
                            System.out.println(crs.getInt("codi") + "-" + crs.getDouble("preuCentral") + "-" + crs.getBoolean("venut"));
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
                            System.out.println(crs.getInt("codi") + "-" + crs.getDouble("preuCentral") + "-" + crs.getBoolean("venut"));
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
                            System.out.println(crs.getInt("codi") + "-" + crs.getDouble("preuCentral") + "-" + crs.getBoolean("venut"));
                        } else {
                            System.out.println("no queden mes registres");
                        }
                    } catch (InputMismatchException ex) {
                            System.out.println("Error al introduir dades.");
                    }
                    break;
                case "M":
                    try {
                        boolean venut;

                        System.out.println("Modifica el Preu:");
                        double preuCentral = entrada.nextDouble();
                        System.out.println("El sistema s'ha venut?(S/N):");
                        String vopcio = entrada.next().toUpperCase();
                        switch (vopcio) {
                            case "S":
                                venut = true;
                                break;
                            case "N":
                                venut = false;
                                break;
                            default:
                                venut = false; // -> throw error
                                break;
                        }

                        crs.updateDouble("preuCentral", preuCentral);
                        crs.updateBoolean("venut", venut);
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
