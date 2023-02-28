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
    private int cantTotalGomonesInd, cantTotalGomonesDobles, cantGomonesQueSePuedenTirar, cantGomonesQueSeTiraron;
    private Semaphore gomonesIndividuales, gomonesDobles, puedeSalir, puedeSalirCamioneta, puedenTirarse, mutex, espaciosCamioneta, camionetaVacia;
    CyclicBarrier barrera;
    private CamionetaConBolsos c;
    
   public Rio(int cantGomonesInd, int cantGomonesDobles, int cantGomonesQueSePuedenTirar){
       this.cantTotalGomonesInd=cantGomonesInd;
       this.cantTotalGomonesDobles=cantGomonesDobles;
       this.cantGomonesQueSePuedenTirar=cantGomonesQueSePuedenTirar;
       this.cantGomonesQueSeTiraron=0;
       
       gomonesIndividuales= new Semaphore(this.cantTotalGomonesInd);
       gomonesDobles= new Semaphore(this.cantTotalGomonesDobles);
       puedenTirarse= new Semaphore(this.cantGomonesQueSePuedenTirar);
       mutex= new Semaphore(1);
       
       barrera= new CyclicBarrier(cantGomonesQueSePuedenTirar);
       
       
       //parte camioneta
       
       c= new CamionetaConBolsos(cantGomonesQueSePuedenTirar);
       espaciosCamioneta= new Semaphore(cantGomonesQueSePuedenTirar);
       camionetaVacia=new Semaphore(0);
       puedeSalir= new Semaphore(0);
       puedeSalirCamioneta= new Semaphore(0);
       
             
   }
   
   
   public void entrarIndividual(){
        try {
            //agarra un gomon individual, sino se queda bloqueado
             gomonesIndividuales.acquire();
                //se fija si todavia hay espacio para tirarse
                puedenTirarse.acquire();
                //espera a que el resto de gomones esten listos
                barrera.await();
                
                //avisa que se tiro
                mutex.acquire();
                cantGomonesQueSeTiraron++;
                puedeSalirCamioneta.release();
                
                //si soy el ultimo hilo que se podia tirar, reseteo la barrera
                if(cantGomonesQueSeTiraron==this.cantGomonesQueSePuedenTirar){
                barrera.reset();
                puedenTirarse.release(this.cantGomonesQueSePuedenTirar);
                }
                
                mutex.release();
                
                
        } catch (Exception ex) {
            Logger.getLogger(Rio.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   }
   public void entrarDoble(){
        try {
            //agarra un gomon individual, sino se queda bloqueado
             gomonesDobles.acquire();
                
             //se fija si todavia hay espacio para tirarse
                puedenTirarse.acquire();
                
                //espera que el resto de gomones esten listos
                barrera.await();
                
                //avisa que se tiro
                mutex.acquire();
                cantGomonesQueSeTiraron++;
                puedeSalirCamioneta.release();
                
                //si soy el ultimo hilo que se podia tirar, reseteo la barrera
                if(cantGomonesQueSeTiraron==this.cantGomonesQueSePuedenTirar){
                barrera.reset();
                puedenTirarse.release(this.cantGomonesQueSePuedenTirar);
                }
                
                mutex.release();
                
                
        } catch (Exception ex) {
            Logger.getLogger(Rio.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   
   public int dejarBolso(){
       int nroBolso=-1;
       try {
       espaciosCamioneta.acquire();
       nroBolso= c.guardarBolso();
       }catch(Exception e){}
       return nroBolso;
   }

   public void retirarBolso(int nroBolso){
        c.sacarBolso(nroBolso);
        camionetaVacia.release();
    }
   
   public void camionetaBaja(){
       try {
           puedeSalirCamioneta.acquire(cantGomonesQueSePuedenTirar);
      
       } catch (Exception e) {
       }
   }
   
   public void camionetaSube(){
       try {
           camionetaVacia.acquire(cantGomonesQueSePuedenTirar);
   
       } catch (Exception e) {
       }
       
   }
   
   
   
   
   
   
   public void salirDoble(){
           gomonesDobles.release();
   }
   
   public void salirIndividual(){
       gomonesIndividuales.release();
   }
  
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}
