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
public class ColectivoFolklorico {
    private Semaphore asientos, avisarEmpleado, avisarClientes;
    
      public ColectivoFolklorico(){
        asientos= new Semaphore(25, true);
        avisarEmpleado= new Semaphore(0);
        avisarClientes= new Semaphore(0);
    }
    
    public void iniciarViaje(){
        //metodo que usa el empleado folklorico para iniciar su viaje
        //hasta que no consiga 25 permisos no puede comenzar el viaje
        try {
            avisarEmpleado.acquire(25);
        } catch (Exception e) {
        }
    }
    
    public void llegaADestino(){
        //metodo que usa el empleado para avisarle a los clientes que ya llegaron al parque
        avisarClientes.release(25);
    }
    
    public void terminoVuelta(){
        //metodo que usa el empleado para avisar a los otros clientes que ya volvio a cabecera
        asientos.release(25);
    }
    
    public void agarroPermiso(){
        //metodo que usa el cliente para agarrar un lugar en el colectivo
        //si no hay mas permisos se quedan trabados hasta que el colectivo vuelva vacio
        try {
            asientos.acquire();
        } catch (Exception e) {
        }
    }
    
    public void avisoLlegada(){
        //metodo que usa el cliente para avisarle al empleado que entro al colectivo
        //cuando el empleado consiga 25 permisos arracna el viaje
        avisarEmpleado.release();
    }
    
    public void avisoSalida(){
        //metodo que usa el cliente para avisarle al empleado que salio
        //esto es para que el colectivero no se pueda ir hasta que no salgan todos los clientes
        avisarEmpleado.release();
    }
    
    public void bajarse(){
        //metodo que usa el cliente para intentar bajarse
        //los que suban van a quedarse bloqueados esperando a que el cliente libere los permisos cuando llege a destino
        try {
            avisarClientes.acquire();
        } catch (Exception e) {
        }
    }
        
    
}
