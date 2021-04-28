package br.edu.ceub.mep.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;


public class Criptografia {

    public String[] criptografarSenha(String senha) throws UnsupportedEncodingException{
        
        String senhaCriptografada = "";
        String saltCrip = ""; 
      
        
        try {
            
            //Criando salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            
            //Conf função hash
            MessageDigest algoritmo = MessageDigest.getInstance("SHA-512");
            algoritmo.update(salt);
            
            //criando byte
            byte messageDigest[] = algoritmo.digest(senha.getBytes("UTF-8"));
            
             //Convertendo byte para string
            StringBuilder hexString = new StringBuilder();
            StringBuilder hexSalt = new StringBuilder();
            
            for(byte b : messageDigest){
                hexString.append(String.format("%02X", 0xFF & b));
            }
            
            for(byte s : salt){
                hexSalt.append(String.format("%02X", 0xFF & s));
            }
            
            senhaCriptografada = hexString.toString();
            saltCrip = hexSalt.toString();
           
            
        } catch (NoSuchAlgorithmException e) {
            String msgErro = "Erro ao criptografar senha";
            System.out.println(e.getMessage());
        }
        
        String senhaSalt[] = new String[2];
        senhaSalt[0] = senhaCriptografada;
        senhaSalt[1] = saltCrip;
        
         return senhaSalt;
    }
    
    
    
    public byte[] pegaSalt(String salt){
        
        BigInteger bi = new BigInteger(salt, 16);
        byte[] saltParaOBanco = new byte[16];
        

        //transforma o array em bytes
        saltParaOBanco = bi.toByteArray();

        //faz uma copia do array sem o byte 0
        if (saltParaOBanco[0] == 0) {
            saltParaOBanco =  Arrays.copyOfRange(saltParaOBanco, 1, saltParaOBanco.length);
        }
            //na verdade é o salt do banco para fazer o calculo
            return saltParaOBanco;
    }
    
    
    public String calculaSenha(byte[] salt, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        
        String senhaCriptografada = "";
        
        //Conf função hash
            MessageDigest algoritmo = MessageDigest.getInstance("SHA-512");
            algoritmo.update(salt);
            
            //criando byte
            byte messageDigest[] = algoritmo.digest(senha.getBytes("UTF-8"));
        
            StringBuilder hexString = new StringBuilder();
        
            for(byte b : messageDigest){
                hexString.append(String.format("%02X", 0xFF & b));
            }
        
            senhaCriptografada = hexString.toString();
            
            return senhaCriptografada;
    }
    
    
    
    
    
}
