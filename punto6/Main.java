/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.concurrente.tp5.punto6;

import utiles.TecladoIn;

/**
 *
 * @author male_
 */
public class Main {
    public static void main(String[] args) {
        Mirador m;
        Cliente c[];
        Thread clientes[];
        Encargado e;
        Thread emp;
        
        System.out.println("Ingrese la cant de escalones del mirador");
        int cantEscalones= TecladoIn.readLineInt();
        m= new Mirador(cantEscalones);
        e= new Encargado(m);
        emp= new Thread(e);
        
        System.out.println("Ingrese la cant de clientes");
        int cantClientes= TecladoIn.readLineInt();
        c= new Cliente[cantClientes];
        clientes= new Thread [c.length];
        
        for (int i = 0; i < c.length; i++) {
            c[i]= new Cliente(m);
            clientes[i]= new Thread(c[i], "Cliente "+i);
        }
        
        for (int i = 0; i < clientes.length; i++) {
            clientes[i].start();
        }
        
        emp.start();
        
    }
    
}
