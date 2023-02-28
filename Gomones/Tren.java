/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.concurrente.tpFinal.Gomones;

import java.util.concurrent.Semaphore;

/**
 *
 * @author male_
 */
public class Tren {
    private Semaphore lugarTren,salirTren,puedeBajarTren;
    
    public void Tren(){
        lugarTren= new Semaphore(15);
        puedeBajarTren= new Semaphore(0);
        salirTren= new Semaphore(0);
    }
    
     public void subirAlTren(){
       try {
           lugarTren.acquire();
           salirTren.release();
           puedeBajarTren.acquire();
           
       } catch (Exception e) {
       }
   }
   
   public void viajarTren(){
       try {
           salirTren.acquire(15);
           Thread.sleep(4000);
       } catch (Exception e) {
       }
   }
     
   public void bajarTren(){
       puedeBajarTren.release(15);
   }
    
}
