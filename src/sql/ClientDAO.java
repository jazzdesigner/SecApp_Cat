
package sql;

import com.mysql.jdbc.MysqlDataTruncation;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.sql.rowset.CachedRowSet;

/**
 * Classe DAO que representa Client
 * Es conecta amb la base de dades i fa els métodes referents a Client
 * @author Jaime, Mario
 */
public class ClientDAO {
    
    private static CachedRowSet crs = null;
    

    /**
     * Métode que crea un nou Client a la base de dades
     * @param dni dni del client (clau)
     * @param nom nom del client
     * @param cognom cognom del client
     * @param adreca adreça del client
     * @param poblacio població del client
     * @param telefon teléfon del client
     */
    public void altaClient(String dni,String nom,String cognom, String adreca,
            String poblacio,String telefon) throws SQLException {

        try {
            BDAccessor bd= new BDAccessor();
            Connection conn = bd.obtenirConnexio();
            //Savepoint svpt = conn.setSavepoint(); 

            String consultaSQL= "INSERT INTO Client VALUES ";
            PreparedStatement ps= conn.prepareStatement(consultaSQL+"(?,?,?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, dni);
            ps.setString(2, nom);
            ps.setString(3, cognom);
            ps.setString(4, adreca);
            ps.setString(5, poblacio);
            ps.setString(6, telefon);

            ps.executeUpdate();
            conn.commit();
            System.out.println("S'ha afegit el Client " + nom + " " + cognom + "\n");
        } catch (InputMismatchException ex) {
            System.out.println("Dades introduides incorrectament");
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (MysqlDataTruncation ex){
            System.out.println("Error: Dades amb longitud errònia");
        } finally {
            
        }

    }
    
    
    /**
     * Métode que mostra per consola la llista dels Clients
     */
    public void llistarClients() throws SQLException {
        try{
            BDAccessor bd= new BDAccessor();
            Connection conn = bd.obtenirConnexio();
            try (Statement stmt = conn.createStatement()) {
                ResultSet resultat = stmt.executeQuery("SELECT * FROM Client");

                while (resultat.next()) {
                    System.out.println(
                            "DNI: " + resultat.getString(1)
                            + ", nom: " + resultat.getString(2)
                            + ", cognom: " + resultat.getString(3)
                            + ", adreça: " + resultat.getString(4)
                            + ", població: " + resultat.getString(5)
                            + ", telèfon: " + resultat.getString(6));   //telefon
                }
                resultat.close();
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
     * Métode que mostra per consola els Noms del clients amb el seu dni
     */
    public void llistarCodisClients() throws SQLException {
        try{
            BDAccessor bd= new BDAccessor();
            Connection conn = bd.obtenirConnexio();
            try (Statement stmt = conn.createStatement()) {
                ResultSet resultat = stmt.executeQuery("SELECT DNI, nom, cognom FROM Client");

                while (resultat.next()) {
                    System.out.println(
                            "DNI: " + resultat.getString(1)
                            + ", nom: " + resultat.getString(2)
                            + ", cognom: " + resultat.getString(3));
                }
                resultat.close();
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
     * Métode que cerca un servei donat el seu DNI
     * @param c DNI del client
     */
    public void cercaClient(String c) throws SQLException {
        try{
            BDAccessor bd= new BDAccessor();
        
            Connection conn = bd.obtenirConnexio();

            String consultaSQL = "SELECT * FROM Client WHERE DNI = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(consultaSQL,PreparedStatement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, c);

                ResultSet resultat = pstmt.executeQuery();

                while (resultat.next()) {
                        System.out.println(
                                "DNI: " + resultat.getString(1)
                                + ", nom: " + resultat.getString(2)
                                + ", cognom: " + resultat.getString(3)
                                + ", adreça: " + resultat.getString(4)
                                + ", població: " + resultat.getString(5)
                                + ", telèfon: " + resultat.getString(6)); 
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
     * Métode que permet modificar un client 
     */
    public void modificarClient() throws SQLException, IOException, ClassNotFoundException, MysqlDataTruncation {
        
        crs = new com.sun.rowset.CachedRowSetImpl();
        crs.setUrl("jdbc:mysql://localhost:3306/practicauf6?zeroDateTimeBehavior=convertToNull&relaxAutoCommit=true");
        crs.setUsername("root");
        crs.setPassword("");
        crs.setCommand("SELECT * FROM Client");
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
                            "DNI: " + crs.getString("DNI")
                            + ", nom: " + crs.getString("nom")
                            + ", cognom: " + crs.getString("cognom")
                            + ", adreça: " + crs.getString("adreca")
                            + ", població: " + crs.getString("poblacio")
                            + ", telèfon: " + crs.getString("telefon"));
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
                            "DNI: " + crs.getString("DNI")
                            + ", nom: " + crs.getString("nom")
                            + ", cognom: " + crs.getString("cognom")
                            + ", adreça: " + crs.getString("adreca")
                            + ", població: " + crs.getString("poblacio")
                            + ", telèfon: " + crs.getString("telefon"));
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
                            "DNI: " + crs.getString("DNI")
                            + ", nom: " + crs.getString("nom")
                            + ", cognom: " + crs.getString("cognom")
                            + ", adreça: " + crs.getString("adreca")
                            + ", població: " + crs.getString("poblacio")
                            + ", telèfon: " + crs.getString("telefon"));
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
                            "DNI: " + crs.getString("DNI")
                            + ", nom: " + crs.getString("nom")
                            + ", cognom: " + crs.getString("cognom")
                            + ", adreça: " + crs.getString("adreca")
                            + ", població: " + crs.getString("poblacio")
                            + ", telèfon: " + crs.getString("telefon"));
                        } else {
                            System.out.println("no queden mes registres");
                        }
                    } catch (InputMismatchException ex) {
                            System.out.println("Error al introduir dades.");
                    }
                    break;
                case "M":
                    try {
                        entrada = new Scanner(System.in);
                        System.out.println("Modifica el nom:");
                        String nom = entrada.nextLine();
                        entrada = new Scanner(System.in);
                        System.out.println("Modifica el cognom:");
                        String cognom = entrada.nextLine();
                        entrada = new Scanner(System.in);
                        System.out.println("Modifica l'adreça:");
                        String adreca = entrada.nextLine();
                        entrada = new Scanner(System.in);
                        System.out.println("Modifica la població:");
                        String poblacio = entrada.nextLine();
                        entrada = new Scanner(System.in);
                        System.out.println("Modifica el teléfon:");
                        String telefon = entrada.nextLine();

                        crs.updateString("nom", nom);
                        crs.updateString("cognom", cognom);
                        crs.updateString("adreca", adreca);
                        crs.updateString("poblacio", poblacio);
                        crs.updateString("telefon", telefon);
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

        } while (!opcio.equalsIgnoreCase(
                "X"));

    }
    
}
