/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DEntrega;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class NEntrega {
    private DEntrega entrega;
    
    public NEntrega() {
        entrega = new DEntrega();
    }
    
    public DefaultTableModel getEntregas() {
        return entrega.getEntregas();
    }
    
    public DefaultTableModel getEntrega(int id) {
        entrega.setId(id);
        return entrega.getEntrega();
    }
    
    public int registrar(String codigo, Date fecha_registro, Date fecha_entrega, 
            String destino, String estado, int administrativoid, int notaventaid, int tipoentregaid) {
        entrega = new DEntrega(codigo, fecha_registro, fecha_entrega, destino, estado, administrativoid, notaventaid, tipoentregaid);
        return entrega.registrar();
    }
    
    public int modificar(int id, String codigo, Date fecha_registro, Date fecha_entrega, 
            String destino, String estado, int administrativoid, int notaventaid, int tipoentregaid) {
        entrega = new DEntrega(id, codigo, fecha_registro, fecha_entrega, destino, estado, administrativoid, notaventaid, tipoentregaid);
        return entrega.modificar();
    }
    
    public int eliminar(int id) {
        entrega.setId(id);
        return entrega.eliminar();
    }
}
