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
public class CamionGomones {
    private boolean [] lockers;
    private int cantLugaresOcupados, puntero;
    private Semaphore mutex;
    
    public CamionGomones(int cantLugares){
        lockers= new boolean[cantLugares];
        mutex= new Semaphore(1);
        cantLugaresOcupados=0;
        puntero=0;
                
    }
    
    public int dejarPertenencia(){
        try {
            mutex.acquire();
            //con true indico que ese lugar esta ocupado (?
            lockers[puntero]=true;
            cantLugaresOcupados++;
            puntero++;
        } catch (Exception e) {
        }
        mutex.release();
        //devuelvo el nro del lugar donde dejo sus "cosas"
        return (puntero--);
    }
    
    public void sacarPertenencias(int lugar){
        try {
            mutex.acquire();
            lockers[lugar]=false;
            cantLugaresOcupados--;
            if(cantLugaresOcupados==0){
                puntero=0;
            }
            mutex.release();
        } catch (Exception e) {
        }
    }
    
    private void inicializarLugares(){
        for (int i = 0; i < lockers.length; i++) {
            lockers[i]=false;
        }
    }

}
