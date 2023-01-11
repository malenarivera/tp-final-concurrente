/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.concurrente.tpFinal.entrarAlParque;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author male_
 */
public class Entrada {
   private Molinete[] molinetes;
   
   public Entrada(int cantMolinetes){
       molinetes= new Molinete [cantMolinetes];
       this.inicializarMolinetes();
   }
   
   public int entrarAlParque(){
       boolean entro=false;
       int molineteAEsperar=0, i;
       i=0;
       
       //Busca un molinete libre
       while(i<molinetes.length){
           if(molinetes[i].molineteLibre()){
               //si lo encuentra nais
               molineteAEsperar=i;
               entro=true;
               i=molinetes.length;
           }
           else
               i++;
       }
       
       if(!entro){
       //Si no habia ningun molinete libre, simulamos que hace la fila en alguno
       molineteAEsperar= (int) (Math.random()*(molinetes.length));
       molinetes[molineteAEsperar].esperarPorMolinete();
       }
       return molineteAEsperar;
   }
   
   public void entro(int molineteQueLeToco){
       molinetes[molineteQueLeToco].librerarMolinete();
   }
   
   private void inicializarMolinetes(){
       for (int i = 0; i < molinetes.length; i++) {
           molinetes[i]= new Molinete();
       }
   }
    
    
}
