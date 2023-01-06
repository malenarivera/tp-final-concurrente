/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.concurrente.tp5.punto6;

/**
 *
 * @author male_
 */
public class Cliente implements Runnable{
    Mirador m;
    char tobogan;
    
    public Cliente (Mirador m){
        this.m=m;
    }
    
    public void run(){
      m.entrarALaFila();
      m.primeroDeLaFila();
      m.avisoEncargado();
       tobogan= m.tirarsePorTobogan();
        System.out.println(Thread.currentThread().getName()+": Tirandose por el tobogan "+tobogan);
       this.simular();
        System.out.println(Thread.currentThread().getName()+": Termino de tirarse por el tobogan "+tobogan);
        m.liberarTobogan(tobogan);
    }
    
    private void simular(){
        try {
            Thread.sleep(4000);
        } catch (Exception e) {
        }
    }
    
    
    
}
