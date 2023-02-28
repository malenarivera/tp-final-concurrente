/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.concurrente.tpFinal.Restaurante;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author male_
 */
public class Restaurante {
    private int capacidadMax, cantActual;
    private ConcurrentLinkedQueue colaDeEspera;
    private String nombreRestaurante;
    private Object punteroAlPrimerHiloEsperando;

    
    public Restaurante (String nombre, int capacidadMaxima){
        this.nombreRestaurante=nombre;
        this.capacidadMax=capacidadMaxima;
        this.cantActual=0;
        colaDeEspera= new ConcurrentLinkedQueue();
    }
    
    public synchronized void entrarAlRestaurante(){
        //si el restaurante esta lleno y no soy el primero esperando para entrar
        while(cantActual>=capacidadMax&&!Thread.currentThread().equals(punteroAlPrimerHiloEsperando)){
            try {
                colaDeEspera.add(Thread.currentThread());
                this.wait();
            } catch (InterruptedException ex) {
            }
        }
        //Si era el primero esperando en la fila entonces me elimino
        if(Thread.currentThread().equals(punteroAlPrimerHiloEsperando))
            colaDeEspera.poll();
        cantActual++;
        
    }
    
    public synchronized void salirDelRestaurante(){
        cantActual--;
        //busco el primer elemento de la cola de espera, para poder decirle dps que ya puede entrar
        punteroAlPrimerHiloEsperando=colaDeEspera.peek();
        this.notifyAll();
    }
}
