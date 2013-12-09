
package practicauf6;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import sql.ClientDAO;
import sql.HistorialReparacioDAO;
import sql.MetodesTaules;
import sql.SensorDAO;
import sql.ServeiDAO;
import sql.SistemaSeguretatDAO;

/**
 * Classe DemanarDades que conté els métodes per a demanar dades des del teclat
 * @author Jaime, Mario
 */
public class DemanarDades {

    /**
     * Métode que crea un nou objecte MétodesTaules i crea les taules corresponents a la base de dades
     */
    public static void crearTaules() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Verificant les taules...");
        MetodesTaules mt = new MetodesTaules();
        mt.crearTaules();
    }    

    /**
     * Joc de proves que conté senténcies per omplir de dades la base de dades
     */
//    public static void jocDeProves() throws SQLException, IOException, ClassNotFoundException {
//            MetodesTaules mt = new MetodesTaules();
//            mt.crearRegistres();
//    }

    /**
     * Métode que permet introduir per teclat les dades d'un client i instancia 
     * un objecte ClientDAO que les introdueix a la base de dades
     */
    public static void introduirDadesClient() throws SQLException, IOException, ClassNotFoundException {
        Scanner entrada = new Scanner(System.in);

        try {
            System.out.println("Afegeix el DNI:");
            String dni = entrada.nextLine();
            System.out.println("Afegeix el nom:");
            String nom = entrada.nextLine();
            System.out.println("Afegeix el cognom:");
            String cognom = entrada.nextLine();
            System.out.println("Afegeix l'adreça:");
            String adreca = entrada.nextLine();
            System.out.println("Afegeix la població:");
            String poblacio = entrada.nextLine();
            System.out.println("Afegeix el teléfon:");
            String telefon = entrada.nextLine();

            ClientDAO cd1 = new ClientDAO();
            cd1.altaClient(dni, nom, cognom, adreca, poblacio, telefon);
    
        } catch (InputMismatchException ex) {
            System.out.println("Error al introduir dades.");
        }

    }
    
    /**
     * Métode que permet introduir per teclat les dades d'un Sensor i instancia 
     * un objecte SensorDAO que les introdueix a la base de dades
     */
    public static void introduirDadesSensor() throws SQLException, IOException, ClassNotFoundException {

        Scanner entrada;

        String tS = null;
        double preu;
        String tipusSensor;
        int temperatura;
        double radi;
        String LED;
        boolean polsat;
        int codi_SistemaSeguretat;


        do {

            entrada = new Scanner(System.in);

            preu = 0;
            tipusSensor = null;
            temperatura = 0;
            radi = 0;
            LED = null;
            polsat = false;
            
            codi_SistemaSeguretat=0;
            SistemaSeguretatDAO ssDAO = new SistemaSeguretatDAO();

            try {
                System.out.println("Quin tipus de sensor vols crear? [S]ortir");
                System.out.println("[I]nfraroig/[V]olumetric/[H]ibrid/[T]actil");
                tS = entrada.nextLine().toUpperCase();
            } catch (InputMismatchException ex) {
                System.out.println("Error al introduir dades.");
            }

            switch (tS) {
                case "I":
                    try{
                        tipusSensor = "infraroig";
                        System.out.println("Afegeix el preu:");
                        preu = entrada.nextDouble();
                        System.out.println("Afegeix el temperatura:");
                        temperatura = entrada.nextInt();

                        System.out.println("\nMostrant el codi del Sistema de Seguretat...");
                        ssDAO.llistarSistemaSeguretat();
                        System.out.println("Insereix el codi del Sistema de Seguretat"
                                + " al qual vol afegir el servei:");
                        codi_SistemaSeguretat = entrada.nextInt();

                        SensorDAO sid1 = new SensorDAO();
                         sid1.altaSensor(tipusSensor, preu, temperatura, radi, LED,
                                 polsat,codi_SistemaSeguretat);
                         
                    } catch (InputMismatchException ex) {
                        System.out.println("Error al introduir dades.");
                    }

                    break;
                case "V":
                    try {
                        tipusSensor = "volumetric";
                        System.out.println("Afegeix el preu:");
                        preu = entrada.nextDouble();
                        System.out.println("Afegeix el radi:");
                        radi = entrada.nextDouble();

                        System.out.println("\nMostrant el codi del Sistema de Seguretat...");
                        ssDAO.llistarSistemaSeguretat();
                        System.out.println("Insereix el codi del Sistema de Seguretat"
                                + " al qual vol afegir el servei:");
                        codi_SistemaSeguretat = entrada.nextInt();


                        SensorDAO svd1 = new SensorDAO();
                        svd1.altaSensor(tipusSensor, preu, temperatura, radi, LED, polsat,codi_SistemaSeguretat);

                    } catch (InputMismatchException ex) {
                        System.out.println("Error al introduir dades.");
                    }

                    break;
                case "H":
                    try{
                        tipusSensor = "hibrid";
                        System.out.println("Afegeix el preu:");
                        preu = entrada.nextDouble();
                        System.out.println("Afegeix el temperatura:");
                        temperatura = entrada.nextInt();
                        System.out.println("Afegeix el radi:");
                        radi = entrada.nextDouble();
                        System.out.println("Afegeix el LED:");
                        LED = entrada.nextLine();

                        System.out.println("\nMostrant el codi del Sistema de Seguretat...");
                        ssDAO.llistarSistemaSeguretat();
                        System.out.println("Insereix el codi del Sistema de Seguretat al qual vol afegir el servei:");
                        codi_SistemaSeguretat = entrada.nextInt();


                        SensorDAO shd1 = new SensorDAO();
                        shd1.altaSensor(tipusSensor, preu, temperatura, radi, LED, polsat,codi_SistemaSeguretat);

                    } catch (InputMismatchException ex) {
                        System.out.println("Error al introduir dades.");
                    }
                    break;
                case "T":
                    try {
                        tipusSensor = "tactil";
                        System.out.println("Afegeix el preu:");
                        preu = entrada.nextDouble();

                        System.out.println("Polsat?(S/N):");
                        String opcio = entrada.next().toUpperCase();
                        switch (opcio) {
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

                        System.out.println("\nMostrant el codi del Sistema de Seguretat...");
                        ssDAO.llistarSistemaSeguretat();
                        System.out.println("Insereix el codi del Sistema de Seguretat al qual vol afegir el servei:");
                        codi_SistemaSeguretat = entrada.nextInt();


                        SensorDAO std1 = new SensorDAO();
                        std1.altaSensor(tipusSensor, preu, temperatura, radi, LED, polsat,codi_SistemaSeguretat);
                    
                    } catch (InputMismatchException ex) {
                        System.out.println("Error al introduir dades.");
                    }
                    break;
                case "S":
                    System.out.println("Sortint d'alta sensors...");
                    break;
                default:
                    System.out.println("Opció incorrecta.\n");
                    break;
            }
        } while (!tS.equals("S"));
    }

    /**
     * Métode que permet introduir per teclat les dades d'un Servei i instancia 
     * un objecte ServeiDAO que les introdueix a la base de dades
     */
    public static void introduirDadesServei() throws SQLException, IOException, ClassNotFoundException {

    Scanner entrada;

    String tServ = null;
    Date dataServei;
    Integer temps;
    String data;
    String[] diaMesAny;
    String tipusServei;
    String codi_client;
    int codi_SistemaSeguretat;


    do {

        entrada = new Scanner(System.in);

        temps = 0;
        data = null;
        dataServei = null;
        diaMesAny = null;
        codi_client="";
        codi_SistemaSeguretat=0;
        ClientDAO cDAO = new ClientDAO();

        try {
            System.out.println("Quin tipus de servei vols crear? [S]ortir");
            System.out.println("[V]enda/[R]eparacio");
            tServ = entrada.nextLine().toUpperCase();
        } catch (InputMismatchException ex) {
            System.out.println("Error al introduir dades.");
        }


        switch (tServ) {
            case "V":
                try {
                    System.out.println("Afegeix la data: (dd/MM/aaaa)");
                    data = entrada.nextLine();
                    diaMesAny = data.split("/");
                    dataServei = new Date(Integer.parseInt(diaMesAny[2]), Integer.parseInt(diaMesAny[1]) - 1, Integer.parseInt(diaMesAny[0]));
                    tipusServei = "Venda";

                    System.out.println("\nMostrant el codi del Client...");
                    cDAO.llistarCodisClients();
                    System.out.println("Insereix el codi del client al qual vol afegir el servei:");
                    codi_client = entrada.nextLine();

                    System.out.println("\nMostrant el codi del Sistema de Seguretat...");
                    SistemaSeguretatDAO ssDAO = new SistemaSeguretatDAO();
                    ssDAO.llistarSistemaSeguretat();
                    System.out.println("Insereix el codi del Sistema de Seguretat al qual vol afegir el servei:");
                    codi_SistemaSeguretat = entrada.nextInt();

                    ServeiDAO servvd1 = new ServeiDAO();
                    servvd1.altaServei(tipusServei, data, temps, codi_client, codi_SistemaSeguretat);
                } catch (InputMismatchException ex) {
                    System.out.println("Error al introduir dades.");
                }

                break;
            case "R":
                Scanner entrada2 = new Scanner(System.in);

                try {
                    System.out.println("Afegeix la data: (dd/MM/aaaa)");
                    data = entrada2.nextLine();
                    diaMesAny = data.split("/");
                    dataServei = new Date(Integer.parseInt(diaMesAny[2]), Integer.parseInt(diaMesAny[1]) - 1, Integer.parseInt(diaMesAny[0]));
                    tipusServei="Reparacio";

                    System.out.println("Afegeix el temps:");
                    temps = entrada2.nextInt();

                    System.out.println("\nMostrant el codi del Client...");
                    cDAO.llistarCodisClients();
                    System.out.println("Insereix el DNI del client al qual vol afegir el servei:");
                    String codi_client2 = entrada2.next();

                    System.out.println("\nMostrant el codi del Sistema de Seguretat...");
                    SistemaSeguretatDAO ssDAO1 = new SistemaSeguretatDAO();
                    ssDAO1.llistarSistemaSeguretat();
                    System.out.println("Insereix el codi del Sistema de Seguretat al qual vol afegir el servei:");
                    codi_SistemaSeguretat = entrada2.nextInt();

                    ServeiDAO servrd2 = new ServeiDAO();
                    servrd2.altaServei(tipusServei, data, temps, codi_client2, codi_SistemaSeguretat);

                    System.out.println("Mostrant la llista de sensors d'aquest sistema:");
                    SensorDAO ss2 = new SensorDAO();
                    ss2.cercaSensorsPerSistemaSeguretat(codi_SistemaSeguretat);

                    System.out.println("Introdueix el codi del sensor que vols reparar:");
                    int perReparar = entrada.nextInt();

                    HistorialReparacioDAO hr = new HistorialReparacioDAO();
                    hr.altaHistorialReparacio(perReparar);

                } catch (InputMismatchException ex) {
                    System.out.println("Error al introduir dades.");
                }

                break;
            case "S":
                System.out.println("Sortint d'alta serveis...");
                break;
            default:
                System.out.println("Opció incorrecta.\n");
                break;
            }
        
        } while (!tServ.equals("S"));
    }
    
    /**
     * Métode que permet introduir per teclat les dades d'un Sistema de Seguretat i instancia 
     * un objecte Sistema de Seguretat que les introdueix a la base de dades
     */
    public static void introduirDadesSistemaSeguretat() throws SQLException,
            IOException, ClassNotFoundException{
        
        Scanner entrada = new Scanner(System.in);
        boolean venut;

        try {
            System.out.println("Afegeix el Preu:");
            double preuCentral = entrada.nextDouble();
            System.out.println("El sistema s'ha venut?(S/N):");
            String opcio = entrada.next().toUpperCase();
            switch (opcio) {
                case "S":
                    venut=true;
                    break;
                case "N":
                    venut=false;
                    break;
                default:
                    venut=false; // -> throw error
                    break;
            }
            SistemaSeguretatDAO ss1= new SistemaSeguretatDAO();
            ss1.altaSistemaSeguretat(preuCentral,venut);
        
        } catch (InputMismatchException ex) {
            System.out.println("Error al introduir dades.");
        }
    }
  
    /**
     * Métode que crea un objecte ClientDAO i fa mostrar per pantalla el llistat
     * de Clients
     */
    public static void llistatClients() throws SQLException, IOException, ClassNotFoundException {
        ClientDAO cd = new ClientDAO();
        cd.llistarClients();
    }
    
    /**
     * Métode que crea un objecte SensorDAO i fa mostrar per pantalla el llistat
     * de Sensors
     */
    public static void llistatSensors() throws SQLException, IOException, ClassNotFoundException {
        SensorDAO sd = new SensorDAO();
        sd.llistarSensors();
    }
    
    /**
     * Métode que crea un objecte SistemaSeguretatDAO i fa mostrar per pantalla
     * el llistat de Sistemes de Seguretat
     */
    public static void llistatSistemesSeguretat() throws SQLException, IOException, ClassNotFoundException {
        SistemaSeguretatDAO ssd = new SistemaSeguretatDAO();
        ssd.llistarSistemaSeguretat();
    }
 
    /**
     * Métode que crea un objecte HistorialReparació i fa mostrar per pantalla
     * el llistat de l'historial de reparacions
     */
    public static void llistatHistorialReparacio() throws SQLException, IOException, ClassNotFoundException {
        HistorialReparacioDAO hr = new HistorialReparacioDAO();
        hr.llistarHistorialReparacio();
    }
    
    /**
     * Métode que crea un objecte ServeiDAO i fa mostrar per pantalla
     * el llistat de Serveis
     */
    public static void llistatServeis() throws SQLException, IOException, ClassNotFoundException {
        ServeiDAO sd = new ServeiDAO();
        sd.llistarReparacio();
        sd.llistarVenda();
    }
    
    /**
     * Métode que crea un objecte ServeiDAO i busca un servei específic donat
     * el codi del mateix, introduit per teclat 
     */
    public static void cercarServeis() throws SQLException, IOException, ClassNotFoundException {
        Scanner entrada = new Scanner(System.in);
        
        try{
            System.out.println("Llistat de Serveis");
            ServeiDAO sd = new ServeiDAO();
            sd.llistarReparacio();
            sd.llistarVenda();
            System.out.println("Introdueix el codi servei que vols cercar:");
            int codi = entrada.nextInt();

            sd.cercaServei(codi);
        
        } catch (InputMismatchException ex) {
            System.out.println("Error al introduir dades.");
        }
    }
    
    /**
     * Métode que crea un objecte ClientDAO i busca un client específic donat
     * el DNI del mateix, introduit per teclat 
     */
    public static void cercarClients() throws SQLException, IOException, ClassNotFoundException {
        Scanner entrada = new Scanner(System.in);

        try {
            System.out.println("Llistat de Clients");
            ClientDAO cd = new ClientDAO();
            cd.llistarClients();
            System.out.println("Introdueix el DNI que vols cercar:");
            String codi = entrada.next();

            cd.cercaClient(codi);
        } catch (InputMismatchException ex) {
            System.out.println("Error al introduir dades.");
        }
    }

    /**
     * Métode que crea un objecte SensorDAO i busca un Sensor específic donat
     * el codi del mateix, introduit per teclat 
     */
    public static void cercaSensors() throws SQLException, IOException, ClassNotFoundException {
        Scanner entrada = new Scanner(System.in);

        try {
        System.out.println("Llistat de Sensors:");
        SensorDAO sd = new SensorDAO();
        sd.llistarSensors();
        System.out.println("Introdueix el codi del sensor que vols cercar:");
        int codi = entrada.nextInt();
        
        sd.cercaSensor(codi);
        } catch (InputMismatchException ex) {
            System.out.println("Error al introduir dades.");
        }
    }

    /**
     * Métode que crea un objecte SistemaSeguretatDAO i busca un servei específic
     * donat el codi del mateix, introduit per teclat 
     */
    public static void cercaSistemaSeguretat() throws SQLException, IOException, ClassNotFoundException {
        Scanner entrada = new Scanner(System.in);

        try{
            System.out.println("Llistat dels Sistemes de Seguretat:");
            SistemaSeguretatDAO sd = new SistemaSeguretatDAO();
            sd.llistarCodiSistemaSeguretat();
            System.out.println("Introdueix el codi del sensor que vols cercar:");
            int codi = entrada.nextInt();
        
            sd.cercaSistemaSeguretat(codi);
    
        } catch (InputMismatchException ex) {
            System.out.println("Error al introduir dades.");
        }
    }

    /**
     * Métode que crea un objecte ClientDAO que permet modificar el Client
     */
    public static void modificacioClient() throws SQLException, IOException, ClassNotFoundException {
        ClientDAO cd = new ClientDAO();
        cd.modificarClient();
    }

    /**
     * Métode que crea un objecte ClientDAO que permet modificar el Client
     */
    public static void modificacioSistemaSeguretat() throws SQLException, IOException, ClassNotFoundException {
        SistemaSeguretatDAO sissegd = new SistemaSeguretatDAO();
        sissegd.modificarSistemaSeguretat();
    }

    /**
     * Métode que crea un objecte ClientDAO que permet modificar el Client
     */
    public static void modificacioServei() throws SQLException, IOException, ClassNotFoundException {
        ServeiDAO servd = new ServeiDAO();
        servd.modificarServei();
    }

    /**
     * Métode que crea un objecte ClientDAO que permet modificar el Client
     */
    public static void modificacioSensor() throws SQLException, IOException, ClassNotFoundException {
        SensorDAO sd = new SensorDAO();
        sd.modificarSensor();
    }
}
