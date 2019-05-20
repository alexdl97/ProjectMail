/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DAdministrativo;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class NAdministrador {
    
    private DAdministrativo adm;
   
    public NAdministrador() {
        adm = new DAdministrativo();
    }
    
    public DefaultTableModel getAdministrativos() {
        return adm.getAdministrativos();
    }
    
    public int registrar(String codigo, String nombre, String telefono, String estado, String cargo, Date fecha_ingreso) {
        adm = new DAdministrativo(codigo, nombre, telefono, estado, cargo, fecha_ingreso);
        return adm.registrar();
    }
    
    public DefaultTableModel getAdministrativo(int id) {
        adm.setId(id);
        return adm.getAdministrativo();
    }
    
    public int modificar(int id, String codigo, String nombre, String telefono, String estado, String cargo, Date fecha_ingreso) {
        adm = new DAdministrativo(id, codigo, nombre, telefono, estado, cargo, fecha_ingreso);
        return adm.modificar();
    }
    
    public int eliminar(int id) {
        adm.setId(id);
        return adm.eliminar();
    }
}
