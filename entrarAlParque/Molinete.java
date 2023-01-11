/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.concurrente.tpFinal.entrarAlParque;

import java.util.concurrent.Semaphore;

/**
 *
 * @author male_
 */
public class Molinete {
    private Semaphore siendoUsado;
    
    public Molinete(){
        siendoUsado= new Semaphore(1,true);
    }
    
    public boolean molineteLibre(){
        return siendoUsado.tryAcquire();
    }
    
    public void librerarMolinete(){
        siendoUsado.release();
    }
    
    public void esperarPorMolinete(){
        try {
            siendoUsado.acquire();
        } catch (Exception e) {
        }
    }
    
}
