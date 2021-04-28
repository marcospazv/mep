package br.edu.ceub.mep.negocio;

import br.edu.ceub.mep.entity.Setor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class TesteBO {
    
    public static void main(String[] args) {
        
        
        
        List<Setor> sets;
        
        SetorBO sbo = new SetorBO();
        
        sets = sbo.getSetores();
        
        for(Setor setor : sets){
            System.out.println(setor);
        }
        
    }
}
