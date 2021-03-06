/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DAdministrativo;
import Datos.DUsuario;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class NUsuario {
    private DUsuario user;
   
    public NUsuario() {
        user = new DUsuario();
    }
    
    public DefaultTableModel getUsuarios() {
        return user.getUsuarios();
    }
    
    public int registrar(String usuario, String contrasenia, int administrativoid) {
        user = new DUsuario(usuario, contrasenia, "A", administrativoid);
        return user.registrar();
    }
    
    public DefaultTableModel getUsusario(int id) {
        user.setId(id);
        return user.getUsuario();
    }
    
    public int modificar(int id, String usuario, String contrasenia, String estado, int administrativoid) {
        user = new DUsuario(id, usuario, contrasenia, estado, administrativoid);
        return user.modificar();
    }
    
    public int eliminar(int id) {
        user.setId(id);
        return user.eliminar();
    }
}
