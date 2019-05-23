/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DProducto;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class NProducto {
    
    private DProducto producto;
    
    public NProducto() {
        producto =  new DProducto();
    }
    
    public DefaultTableModel getProductos() {
        return producto.getProductos();
    }
    
    public DefaultTableModel getProducto(int id) {
        producto.setId(id);
        return producto.getProducto();
    }
    
    public int registrar(String codigo, String marca, String modelo, double precio,
            double costo, String estado, int loteid, int tipoproductoid) {
        
        producto = new DProducto(codigo, marca, modelo, precio, costo, estado, loteid, tipoproductoid);
        return producto.registrar();
    }
    
    public int modificar(int id, String codigo, String marca, String modelo, double precio,
        double costo, String estado, String codigoLote, String tipoProducto) {
     //   int loteId = 
    //    producto = new DProducto(id, codigo, marca, modelo, precio, costo, estado, loteid, tipoproductoid);
        return 1;
    }
    
    public int eliminar(int id) {
        producto.setId(id);
        return producto.eliminar();
    }
}
