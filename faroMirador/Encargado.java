/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.concurrente.tpFinal.faroMirador;

import universidad.concurrente.tp5.punto6.*;

/**
 *
 * @author male_
 */
public class Encargado implements Runnable {
    Mirador m;
    
    public Encargado(Mirador m){
        this.m=m;
    }
    
    public void run(){
        while(true){
            m.buscarToboganyLiberarPuedeTirarse();
        }
            
    }
    
}
