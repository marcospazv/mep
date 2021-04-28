package br.edu.ceub.mep.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class PersistenceManagerSingleton {
    
    private static PersistenceManagerSingleton instancia;
    private static EntityManagerFactory emf;

    private PersistenceManagerSingleton() {}
    
    public static PersistenceManagerSingleton getInstance(){
        if(instancia == null){
            instancia = new PersistenceManagerSingleton();
        }
        return instancia;
    }
    
    public static EntityManagerFactory getEntityManagerFactory(){
        if(emf == null){
            emf = Persistence.createEntityManagerFactory("mepTeste");
        }
        return emf;
    }
    
    public void closeFactory(){
        if(emf != null){
            emf.close();
        }
        emf = null;
    }
}
