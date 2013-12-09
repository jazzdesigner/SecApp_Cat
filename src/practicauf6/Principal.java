package practicauf6;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import sql.*;

/**
 * Classe Principal de l'aplicació
 * @author Jaime
 */
public class Principal {
    
    static ArrayList<Sensor> llistaSensors = new ArrayList();
    private static Connection conn;
    private static BDAccessor bd= null;


    public static void main(String[] args){
        
            bd = new BDAccessor();
        try {
            conn = bd.obtenirConnexio();
            
            DemanarDades.crearTaules();  //Métode que crea les tables si no estan creades
            aplicacio();    //Métode que crida el joc de proves per a executar l'aplicació
            
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
                
    }

    /**
     * Métode principal Que posa en marxa l'aplicació
     */
    private static void aplicacio() throws SQLException, ClassNotFoundException, IOException {
        
        int opcio;
        Scanner entrada = new Scanner(System.in);
        
        do {
            System.out.println(
                "\n--------------------------------------"
                + "\nsegurACME: Aplicació de Seguretat"
                + "\n--------------------------------------\n");
            System.out.println("Trii quina operació vol realitzar:");
            
//            System.out.println("0. Carregar Joc de proves");
            System.out.println("1. CLIENT: Alta");
            System.out.println("2. CLIENT: Modificació");
            System.out.println("3. CLIENT: Llistat dels clients");
            System.out.println("4. CLIENT: Cerca (per DNI)");
            System.out.println("5. SENSORS: Alta");
            System.out.println("6. SENSORS: Modificació");
            System.out.println("7. SENSORS: Llistat dels sensors");
            System.out.println("8. SENSORS: Cerca (per codi del sensor)");
            System.out.println("9. SISTEMA DE SEGURETAT: Alta");
            System.out.println("10. SISTEMA DE SEGURETAT: Modificació");
            System.out.println("11. SISTEMA DE SEGURETAT: Llistat dels sistemes de seguretat");
            System.out.println("12. SISTEMA DE SEGURETAT: Cerca (per codi del sistema de seguretat)");
            System.out.println("13. SERVEI: Alta");
            System.out.println("14. SERVEI: Modificació");
            System.out.println("15. SERVEI: Llistat dels serveis");
            System.out.println("16. SERVEI: Cerca (per codi del servei");
            System.out.println("17. HISTORIAL DE REPARACIÓ: Llistat");
            System.out.println("18. Sortir de l'aplicació");
            
            opcio = entrada.nextInt();
            switch (opcio) {
//                case 0:
//                    DemanarDades.jocDeProves();
//                    break;
                case 1:
                    DemanarDades.introduirDadesClient();
                    break;
                case 2:
                    DemanarDades.modificacioClient();
                    break;
                case 3:
                    DemanarDades.llistatClients();
                    break;
                case 4:
                    DemanarDades.cercarClients();
                    break;
                case 5:
                    DemanarDades.introduirDadesSensor();
                    break;
                case 6:
                    DemanarDades.modificacioSensor();
                    break;
                case 7:
                    DemanarDades.llistatSensors();
                    break; 
                case 8:
                    DemanarDades.cercaSensors();
                    break;
                case 9:
                    DemanarDades.introduirDadesSistemaSeguretat();
                    break;
                case 10:
                    DemanarDades.modificacioSistemaSeguretat();
                    break;
                case 11:
                    DemanarDades.llistatSistemesSeguretat();
                    break;
                case 12:
                    DemanarDades.cercaSistemaSeguretat();
                    break;
                case 13:
                    DemanarDades.introduirDadesServei();
                    break;
                case 14:
                    DemanarDades.modificacioServei();
                    break;
                case 15:
                    DemanarDades.llistatServeis();
                    break;
                case 16:
                    DemanarDades.cercarServeis();
                    break;
                case 17:
                    DemanarDades.llistatHistorialReparacio();
                    break;
                case 18:
                    System.out.println("Sortint de l'aplicació...");
                    conn.close();
                    break;
                default:
                    System.out.println("Opció incorrecta.\n");
                break;
            }
            
        } while (opcio != 18);
        System.out.println("Final aplicació");
    }
}
