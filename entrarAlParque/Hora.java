/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.concurrente.tpFinal.entrarAlParque;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author male_
 */
public class Hora {
    private Entrada entrada;

    public Hora(Entrada entrada) {
        this.entrada = entrada;
    }
    
    public void run(){
       while(true){
           try {
               Thread.sleep(2000);
               entrada.pasarHora();
           } catch (InterruptedException ex) {
               Logger.getLogger(Hora.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }
    
}

