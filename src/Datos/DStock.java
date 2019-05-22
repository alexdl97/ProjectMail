/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class DStock {
    
    private int id;
    private int cantidad;
    private int almacenid;
    private int productoid;
    private Conexion conexion;
    
    public DStock() {
        id = 0;
        cantidad = -1;
        almacenid = 0;
        productoid = 0;
        conexion = Conexion.getInstancia();
    }
    
    public DStock(int cantidad, int almacenid, int productoid) {
        this.id = 0;
        this.cantidad = cantidad;
        this.almacenid = almacenid;
        this.productoid = productoid;
    }

    public DStock(int id, int cantidad, int almacenid, int productoid) {
        this.id = id;
        this.cantidad = cantidad;
        this.almacenid = almacenid;
        this.productoid = productoid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getAlmacenid() {
        return almacenid;
    }

    public void setAlmacenid(int almacenid) {
        this.almacenid = almacenid;
    }

    public int getProductoid() {
        return productoid;
    }

    public void setProductoid(int productoid) {
        this.productoid = productoid;
    }
    
    
}
