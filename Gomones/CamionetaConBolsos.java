/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.concurrente.tpFinal.Gomones;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author male_
 */
public class CamionetaConBolsos {
    private Semaphore mutex;
    private String [] bolsitos;
    private int bolsoCliente;
    
    
    public CamionetaConBolsos(int cantLugares){
        mutex= new Semaphore(1);
        bolsitos= new String[cantLugares];
    }
    
    public int guardarBolso(){
        try {
          mutex.acquire();
           int i=0;
          while(i<bolsitos.length){
           if(bolsitos[i]==null){
               //ese bolso esta libre
               bolsoCliente=i;
               i=bolsitos.length;
           }
          }
        }catch(Exception e){}
        mutex.release();
        return bolsoCliente;
      }
    
    
    
    public void sacarBolso(int numeroBolso){
        try {
            mutex.acquire();
            bolsitos[numeroBolso]=null;
            mutex.release();
        } catch (Exception e) {
        }
    }
    
    public void run(){
        while(true){
            
        }
    }
}
