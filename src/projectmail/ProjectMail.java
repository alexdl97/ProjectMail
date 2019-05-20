/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmail;

import Datos.DAdministrativo;
import Datos.DCliente;
import Datos.DProveedor;
import Datos.DUsuario;
import Protocolos.POP;
import Protocolos.SMTP;
import java.sql.Date;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class ProjectMail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //POP.readMail();
        DProveedor user = new DProveedor(1, "111", "prov", "123123", "av beni", "A");
        user.eliminar();
       
    }
    
}
