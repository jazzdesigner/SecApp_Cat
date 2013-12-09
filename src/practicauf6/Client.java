
package practicauf6;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa Client
 * @author Jaime, Mario
 */
public class Client implements Comparable<Client>{
   private String DNI;
   private String nom;
   private String cognoms;
   private String adreça;
   private String poblacio;
   private String telefon;
   /**/private List<Servei> llistaServeis= new ArrayList();

   
    public Client(String DNI, String nom, String cognoms, String adreça, String poblacio, String telefon) {
        this.DNI = DNI;
        this.nom = nom;
        this.cognoms = cognoms;
        this.adreça = adreça;
        this.poblacio = poblacio;
        this.telefon = telefon;
    }

    
    /**
     * Métode que afegeix un Servei
     * @param s Servei a afegir
     */
    public void afegirServei(Servei s) {
        llistaServeis.add(s);
    }
    
    
    /**
     * Métode que comprova si els Sistema de seguretat s'ha venut
     * @param sis
     * @return 
     */
    public boolean comprovarVenda(SistemaSeguretat sis) {
        return false;
    }
    
    /**
     * Métode que comprova si el servei existeix
     * @param ser Servei a comprovar
     * @return 
     */
    public boolean existeixServei(Servei ser) {
        if (llistaServeis.contains(ser)) return true;        
        return false;
    }
    
    /**
     * Métode que Retorna la llista dels serveis
     * @return retornla llistaServeis
     */
    public List<Servei> obtenirLlistaServeis() {
        return llistaServeis;
    }
    
    
    /*Métodes accessors i lectors*/
    
    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getAdreça() {
        return adreça;
    }

    public void setAdreça(String adreça) {
        this.adreça = adreça;
    }

    public String getPoblacio() {
        return poblacio;
    }

    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    
    
    /*Métodes d'objecte*/
    
    @Override
    public String toString() {
        return "DNI: " + DNI + "\nnom: " + nom + "\ncognoms: " + cognoms +
            "\nadre\u00e7a: " + adreça + "\npoblacio: " + poblacio + "\ntelefon: " + telefon;
    }
   
    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(obj==null) return false;
        if(!(obj instanceof Client)) return false;
        if (getDNI().equals(((Client)obj).getDNI())) return true;
                
        return false;
    }

    public int compareTo(Client o){
        if (Integer.parseInt(this.getDNI()) > Integer.parseInt(o.getDNI())) return 1;
        else if (Integer.parseInt(this.getDNI()) < Integer.parseInt(o.getDNI())) return -1;
        else return 0;
    }

   
}
