/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

/**
 *
 * @author ADL
 */
public class HiloAtencion extends Thread {
    public volatile String mensaje;

    public HiloAtencion(String mensaje) {
        this.mensaje = mensaje;
    }
    
    @Override
    public void run() {
         new Impresol().procesarMensaje(mensaje);
    }
    
    
}
