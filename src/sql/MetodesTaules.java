
package sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe que Conté Métodes de taules no relacionades amb el tractament específic
 * de cap objecte representat al Model DAO
 * @author Jaime, Mario
 */
public class MetodesTaules {
   
    /**
     * Métode que crea les taules, comprovant si existeixen
     */
    public void crearTaules() throws SQLException, IOException, ClassNotFoundException {
        BDAccessor bd= new BDAccessor();
        Connection conn = bd.obtenirConnexio();
        
        Statement stmt = conn.createStatement();

        //Creació de taules
        String creacioTablaClient = "CREATE TABLE IF NOT EXISTS Client ("
                + "DNI VARCHAR(9), nom VARCHAR(20), cognom VARCHAR(20), "
                + "adreca VARCHAR(20), poblacio VARCHAR(20), telefon VARCHAR(10),"
                + "CONSTRAINT PK_Client PRIMARY KEY (DNI))";
        
        String creacioTablaSistemaSeguretat = "CREATE TABLE IF NOT EXISTS SistemaSeguretat ("
                + "codi INT NOT NULL AUTO_INCREMENT, preuCentral DOUBLE, venut BOOLEAN,"
                + "CONSTRAINT PK_SistemaSeguretat PRIMARY KEY (codi))";

        String creacioTablaSensor = "CREATE TABLE IF NOT EXISTS Sensor ("
                + "codi INT NOT NULL AUTO_INCREMENT, tipusSensor VARCHAR(20), preu DOUBLE, "
                + "temperatura INT, radi DOUBLE, LED VARCHAR(10), polsat BOOLEAN, "
                + "codi_SistemaSeguretat INT, "
                + "CONSTRAINT PK_Sensor PRIMARY KEY (codi), "
                + "CONSTRAINT FK_Sensor_SistemaSeguretat FOREIGN KEY (codi_SistemaSeguretat) "
                + "REFERENCES SistemaSeguretat(codi)"
                + "ON DELETE CASCADE ON UPDATE CASCADE)";

        String creacioServei = "CREATE TABLE IF NOT EXISTS Servei ("
                + "codi INT NOT NULL AUTO_INCREMENT, tipusServei VARCHAR(20), dateServei VARCHAR(20),"
                + "temps INTEGER, codi_client VARCHAR(9), codi_SistemaSeguretat INT,"
                + "CONSTRAINT PK_Servei PRIMARY KEY (codi),"
                + "CONSTRAINT FK_Servei_Client FOREIGN KEY (codi_client) "
                    + "REFERENCES Client(DNI)"
                    + "ON DELETE CASCADE ON UPDATE CASCADE,"
                + "CONSTRAINT FK_Servei_SistemaSeguretat FOREIGN KEY (codi_SistemaSeguretat) "
                    + "REFERENCES SistemaSeguretat(codi) "
                    + "ON DELETE CASCADE ON UPDATE CASCADE)";

        String creaciohReparacio = "CREATE TABLE IF NOT EXISTS HistorialReparacio ("
                + "codi_servei INT, codi_sensor INT,"
                + "CONSTRAINT PK_HistorialReparacio PRIMARY KEY (codi_servei,codi_sensor))";
        
                
        stmt.executeUpdate(creacioTablaClient);
        stmt.executeUpdate(creacioTablaSistemaSeguretat);
        stmt.executeUpdate(creacioTablaSensor);
        stmt.executeUpdate(creacioServei);
        stmt.executeUpdate(creaciohReparacio);

        
        System.out.println("Taules creades correctament.");

        stmt.close();
                
        System.out.println("Tancament connexió.");

    }

    public void crearRegistres() throws SQLException, IOException, ClassNotFoundException {
        
        BDAccessor bd= new BDAccessor();
        Connection conn = bd.obtenirConnexio();
        
        Statement stmt = conn.createStatement();
        
        //Clients
        String registre = "INSERT INTO Client VALUES ('53827372N','mario','aguilar','sedo','santaco','933919555');"
                + "INSERT INTO Client VALUES ('33889972T','jaime','zamorano','villa','badalona','934569392');"
                + "INSERT INTO Client VALUES ('88748591M','sergi','grau','java','barcelona','902918876');"
                + "INSERT INTO SistemaSeguretat(preuCentral,venut) VALUES (344,TRUE);"
                + "INSERT INTO SistemaSeguretat(preuCentral,venut) VALUES (199,FALSE);"
                + "INSERT INTO SistemaSeguretat(preuCentral,venut) VALUES (54.6,FALSE);"
                + "INSERT INTO Sensor(tipusSensor,preu,temperatura,radi,LED,polsat,codi_SistemaSeguretat) VALUES ('infraroig',45,20,0,null,FALSE,1);"
                + "INSERT INTO Sensor(tipusSensor,preu,temperatura,radi,LED,polsat,codi_SistemaSeguretat) VALUES ('volumetric',34.8,0,5,null,FALSE,1);"    
                + "INSERT INTO Sensor(tipusSensor,preu,temperatura,radi,LED,polsat,codi_SistemaSeguretat) VALUES ('hibrid',50,13,7.5,'verd',FALSE,2);"
                + "INSERT INTO Sensor(tipusSensor,preu,temperatura,radi,LED,polsat,codi_SistemaSeguretat) VALUES ('tactil',67.9,0,0,null,TRUE,2);"
                + "INSERT INTO Servei(tipusServei,dateServei,temps,codi_client,codi_SistemaSeguretat) VALUES ('Venda','19/01/1991',0,'53827372N',1);"
                + "INSERT INTO Servei(tipusServei,dateServei,temps,codi_client,codi_SistemaSeguretat) VALUES ('Reparacio','22/07/2000',30,'33889972T',2);"
                + "INSERT INTO Servei(tipusServei,dateServei,temps,codi_client,codi_SistemaSeguretat) VALUES ('Reparacio','15/03/2013',15,'88748591M',3);";
        
        stmt.executeUpdate(registre);       
        
        conn.commit();
        System.out.println("Registres creats correctament.");
        
        stmt.close();
        System.out.println("Tancament connexió.");
        
    }
        
}
