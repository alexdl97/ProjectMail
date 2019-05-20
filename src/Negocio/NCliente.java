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
        cliente = new DCliente(codigo, nit, nombre,telefono, "A");
        return cliente.registrar();
    }
    
    public DefaultTableModel getCliente(int id) {
        cliente.setId(id);
        return cliente.getCliente();
    }
    
    public int modificar(int id, String codigo, int nit, String nombre, String telefono, String estado) {
        cliente = new DCliente(id, codigo, nit, nombre,telefono, estado);
        return cliente.modificar();
    }
    
    public int eliminar(int id) {
        cliente.setId(id);
        return cliente.eliminar();
    }
}
