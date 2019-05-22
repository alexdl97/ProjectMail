/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DCliente;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class NCliente {
    private DCliente cliente;
   
    public NCliente() {
        cliente = new DCliente();
    }
    
    public DefaultTableModel getClientes() {
        return cliente.getClientes();
    }
    
    public int registrar(String codigo, int nit, String nombre, String telefono) {
        cliente = new DCliente(codigo, nit, nombre,telefono);
        return cliente.registrar();
    }
    
    public DefaultTableModel getCliente(int id) {
        cliente.setId(id);
        return cliente.getCliente();
    }
    
    public int modificar(String codigo, int nit, String nombre, String telefono) {
        cliente.setCodigo(codigo);
        int id = cliente.getIdCliente();
        cliente = new DCliente(id, codigo, nit, nombre,telefono);
        return cliente.modificar();
    }
    
    public int eliminar(String codigo) {
        cliente.setCodigo(codigo);
        cliente.getIdCliente();
        return cliente.eliminar();
    }
}
