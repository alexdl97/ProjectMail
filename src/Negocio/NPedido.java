/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DPedido;
import Datos.DProveedor;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class NPedido {
    
    private DPedido pedido;
    
    public NPedido() {
        pedido = new DPedido();
    }
    
    public DefaultTableModel getPedidos() {
        return pedido.getPedidos();
    }
    
    public int registrar(String codigo, Date fecha_registro, String descripcion, double monto_total, int clienteid) {
        pedido = new DPedido(codigo, fecha_registro, descripcion,"A", monto_total, clienteid);
        return pedido.registrar();
        //registrar en detalle pedido
    }
    
    public DefaultTableModel getPedido(int id) {
        pedido.setId(id);
        return pedido.getPedido();
    }
    
    public int modificar(int id, String codigo, Date fecha_registro, String descripcion, String estado, double monto_total, int clienteid) {
        pedido = new DPedido(id, codigo, fecha_registro, descripcion, estado, monto_total, clienteid);
        return pedido.modificar();
    }
    
    public int eliminar(int id) {
        pedido.setId(id);
        return pedido.eliminar();
    }
}
