/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.concurrente.tpFinal.Gomones;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author male_
 */
public class Rio {
    //recurso compartido
    private int cantTotalGomonesInd, cantTotalGomonesDobles, cantGomonesQueSePuedenTirar;
    private Semaphore cantGomonesIndividuales, cantGomonesDobles, puedeSalir, puedeSalirCamioneta;
    CyclicBarrier barrera;
    
   public Rio(int cantGomonesInd, int cantGomonesDobles, int cantGomonesQueSePuedenTirar){
       this.cantGomonesDobles=new Semaphore(cantGomonesDobles);
       cantTotalGomonesInd=cantGomonesInd;
       cantTotalGomonesDobles=cantGomonesDobles;
       this.cantGomonesIndividuales= new Semaphore(cantGomonesInd);
       barrera= new CyclicBarrier(cantGomonesQueSePuedenTirar);
       puedeSalir= new Semaphore(0);
       puedeSalirCamioneta= new Semaphore(0);
   }
   
   public void entrarIndividual(){
        try {
            //agarra un gomon individual, sino se queda bloqueado
             cantGomonesIndividuales.acquire();
                //espera a tirarse
                barrera.await();
                
                //cuando se tiran, agarran este permiso que lo liberara la camioneta cuando llegue
                puedeSalir.acquire();
        } catch (Exception ex) {
            Logger.getLogger(Rio.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   }
   public void entrarDoble(){
        try {
            //agarra un gomon individual, sino se queda bloqueado
             cantGomonesDobles.acquire();
                //espera a tirarse
                barrera.await();
                 //cuando se tiran, agarran este permiso que lo liberara la camioneta cuando llegue
                puedeSalir.acquire();
        } catch (Exception ex) {
            Logger.getLogger(Rio.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public void salirCamioneta(){
       try {
           puedeSalirCamioneta.acquire(cantGomonesQueSePuedenTirar);
       } catch (Exception e) {
       }
   }
   
   public void llegoLaCamioneta(){
       //la camioneta llego al final, ahora les avisa a los del gomon que se pueden retirar de la actividad
       puedeSalir.release(cantGomonesQueSePuedenTirar);
       barrera.reset();
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}
