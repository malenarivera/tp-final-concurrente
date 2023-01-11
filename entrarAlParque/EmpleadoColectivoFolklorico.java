/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.concurrente.tpFinal.entrarAlParque;

/**
 *
 * @author male_
 */
public class EmpleadoColectivoFolklorico {
    ColectivoFolklorico c;
    
    public EmpleadoColectivoFolklorico(ColectivoFolklorico c){
        this.c=c;
    }
    
    public void run(){
        while(true){
         c.iniciarViaje();
         System.out.println("Colectivo Folklorico: Comenzando viaje");
         this.simulaViaje();
         System.out.println("Colectivo Folklorico: LLevando los clientes al parquee");
         c.llegaADestino();
         c.iniciarViaje();
         System.out.println("Colectivo Folklorico: ya no hay mas clientes, vuelvo vacio");
         this.simulaViaje();
         System.out.println("Colectivo Folklorico: ya pueden subir el resto de clientes");
         c.terminoVuelta();
        }
    }
    
    private void simulaViaje(){
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
    }
    
}
