
package sql;

import com.mysql.jdbc.MysqlDataTruncation;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.sql.rowset.CachedRowSet;

/**
 * Classe DAO que representa Servei
 * Es conecta amb la base de dades i fa els métodes referents a Servei
 * @author Jaime, Mario
 */
public class ServeiDAO {

    private static CachedRowSet crs = null;

    /**
     * Métode que crea un nou Servei a la base de dades
     * @param tipusServei tipus de servei (Venda | Reparació)
     * @param data data de creació del servei
     * @param temps durada del servei
     * @param codi_client codi del client
     * @param codi_SistemaSeguretat codi del sistema de seguretat
     */
    public void altaServei(String tipusServei, String data, Integer temps, String codi_client, int codi_SistemaSeguretat)
            throws SQLException {
        
        try{
            BDAccessor bd= new BDAccessor();
            Connection conn = bd.obtenirConnexio();

            String consultaSQL = "INSERT INTO Servei(tipusServei,dateServei,temps,codi_client,codi_SistemaSeguretat) VALUES ";
            PreparedStatement ps = conn.prepareStatement(consultaSQL + "(?,?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, tipusServei);
            ps.setString(2, data);
            ps.setDouble(3, temps);
            ps.setString(4, codi_client);
            ps.setInt(5, codi_SistemaSeguretat);

            ps.executeUpdate();
            conn.commit();
            System.out.println("S'ha afegit el Servei.\n");
        
            
        } catch (InputMismatchException ex) {
            System.out.println("Dades introduides incorrectament");
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (MysqlDataTruncation ex){
            System.out.println("Error: Dades amb longitud errònia");
        }
    }

    /**
     * Métode que mostra per consola la llista dels Serveis de tipus Reparació
     */
    public void llistarReparacio() throws SQLException {
        
        try {
            BDAccessor bd= new BDAccessor();
            Connection conn = bd.obtenirConnexio();
            Statement stmt = conn.createStatement();
            ResultSet resultat = stmt.executeQuery(""
                    + "SELECT codi, tipusServei, dateServei, temps, codi_client, codi_SistemaSeguretat\n" +
                    "FROM Servei\n" +
                    "WHERE Servei.tipusServei = 'Reparacio'\n" +
                    "ORDER BY codi;\n" +
                    "");
            
            while (resultat.next()) {
                System.out.println(
                        "codi : " + resultat.getString(1)
                        + ", tipusServei: " + resultat.getString(2)
                        + ", dateServei: " + resultat.getString(3)
                        + ", temps (minuts): " + resultat.getInt(4)
                        + ", codi del client: " + resultat.getString(5)
                        + ", codi del sistema de seguretat: " + resultat.getInt(6));
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
     * Métode que mostra per consola la llista dels Serveis de tipus Venda
     */
    public void llistarVenda() throws SQLException {
        
        try {
            BDAccessor bd= new BDAccessor();
            Connection conn = bd.obtenirConnexio();
            Statement stmt = conn.createStatement();
            ResultSet resultat = stmt.executeQuery(
                    "SELECT codi, tipusServei, dateServei, codi_client, codi_SistemaSeguretat\n" +
                    "FROM Servei\n" +
                    "WHERE Servei.tipusServei = 'Venda'\n" +
                    "ORDER BY codi;\n");
            
            while (resultat.next()) {
                System.out.println(
                        "codi : " + resultat.getString(1)
                        + ", tipusServei: " + resultat.getString(2)
                        + ", dateServei: " + resultat.getString(3)
                        + ", codi del client: " + resultat.getString(4)
                        + ", codi del sistema de seguretat: " + resultat.getInt(5));
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
     * Métode que cerca un servei donat el seu codi
     * @param c codi del servei
     */
    public void cercaServei(int c) throws SQLException {
        
        try {
            BDAccessor bd= new BDAccessor();
            Connection conn = bd.obtenirConnexio();

            String consultaSQL = "SELECT * FROM Servei WHERE codi = ?";
            PreparedStatement pstmt = conn.prepareStatement(consultaSQL,PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, c);
            ResultSet resultat = pstmt.executeQuery();

            while (resultat.next()) {
                System.out.println(
                        "codi : " + resultat.getString(1)
                        + ", tipusServei: " + resultat.getString(2)
                        + ", dateServei: " + resultat.getString(3)
                        + ", temps (minuts): " + resultat.getInt(4)
                        + ", codi del client: " + resultat.getString(5)
                        + ", codi del sistema de seguretat: " + resultat.getInt(6));
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
     * Métode que permet modificar un Servei 
     */
    public void modificarServei() throws SQLException, IOException, ClassNotFoundException, MysqlDataTruncation {

        crs = new com.sun.rowset.CachedRowSetImpl();
        crs.setUrl("jdbc:mysql://localhost:3306/practicauf6?zeroDateTimeBehavior=convertToNull&relaxAutoCommit=true");
        crs.setUsername("root");
        crs.setPassword("");
        crs.setCommand("SELECT * FROM Servei");
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
                            "codi : " + crs.getString("codi")
                            + ", tipusServei: " + crs.getString("tipusServei")
                            + ", dateServei: " + crs.getString("dateServei")
                            + ", temps (minuts): " + crs.getInt("temps")
                            + ", codi del client: " + crs.getString("codi_client")
                            + ", codi del sistema de seguretat: " + crs.getInt("codi_SistemaSeguretat"));
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
                            "codi : " + crs.getString("codi")
                            + ", tipusServei: " + crs.getString("tipusServei")
                            + ", dateServei: " + crs.getString("dateServei")
                            + ", temps (minuts): " + crs.getInt("temps")
                            + ", codi del client: " + crs.getString("codi_client")
                            + ", codi del sistema de seguretat: " + crs.getInt("codi_SistemaSeguretat"));
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
                            "codi : " + crs.getString("codi")
                            + ", tipusServei: " + crs.getString("tipusServei")
                            + ", dateServei: " + crs.getString("dateServei")
                            + ", temps (minuts): " + crs.getInt("temps")
                            + ", codi del client: " + crs.getString("codi_client")
                            + ", codi del sistema de seguretat: " + crs.getInt("codi_SistemaSeguretat"));
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
                            "codi : " + crs.getString("codi")
                            + ", tipusServei: " + crs.getString("tipusServei")
                            + ", dateServei: " + crs.getString("dateServei")
                            + ", temps (minuts): " + crs.getInt("temps")
                            + ", codi del client: " + crs.getString("codi_client")
                            + ", codi del sistema de seguretat: " + crs.getInt("codi_SistemaSeguretat"));
                        } else {
                            System.out.println("no queden mes registres");
                        }
                    } catch (InputMismatchException ex) {
                            System.out.println("Error al introduir dades.");
                    }
                    break;
                case "M":
                    try {
                        Date dataServei;
                        String data;
                        String[] diaMesAny;
                        Integer temps = 0;

                        entrada = new Scanner(System.in);
                        System.out.println("Modifica la data: (dd/MM/aaaa)");
                        data = entrada.nextLine();
                        diaMesAny = data.split("/");
                        dataServei = new Date(Integer.parseInt(diaMesAny[2]), Integer.parseInt(diaMesAny[1]) - 1, Integer.parseInt(diaMesAny[0]));
                        if(crs.getString("tipusServei").equals("Reparacio")){
                            System.out.println("Modifica el temps:");
                            temps = entrada.nextInt();
                        }

                        crs.updateString("dateServei", data);
                        crs.updateInt("temps", temps);
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
