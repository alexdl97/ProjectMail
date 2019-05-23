/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DProveedor;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class NProveedor {
    private DProveedor prov;
   
    public NProveedor() {
        prov = new DProveedor();
    }
    
    public DefaultTableModel getProveedores() {
        return prov.getProveedores();
    }
    
    public int registrar(String codigo, String nombre, String telefono, String direccion) {
        prov = new DProveedor(codigo, nombre,telefono, direccion, "A");
        return prov.registrar();
    }
    
    public DefaultTableModel getProveedor(int id) {
        prov.setId(id);
        return prov.getProveedor();
    }
    
    public int modificar(String codigo, String nombre, String telefono, String direccion) {
        prov.setCodigo(codigo);
        int id = prov.getIdProveedor();
        prov = new DProveedor(id, codigo, nombre,telefono, direccion, "A");
        return prov.modificar();
    }
    
   public int eliminar(String codigo) {
        prov.setCodigo(codigo);
        int id = prov.getIdProveedor();
        prov.setId(id);
        return prov.eliminar();
    }
}
