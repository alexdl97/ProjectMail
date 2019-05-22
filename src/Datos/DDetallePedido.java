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
public class DDetallePedido {
    
    private int id;
    private int cantidad;
    private String descripcion;
    private double total;
    private int pedidoid;
    private int productoid;
    private Conexion conexion;
    
    public DDetallePedido() {
        id = 0;
        cantidad = -1;
        descripcion = "";
        total = -1;
        pedidoid = 0;
        productoid = 0;
        conexion = Conexion.getInstancia();
    }
    
    public DDetallePedido(int cantidad, String descripcion, int pedidoid, int productoid) {
        this.id = 0;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.pedidoid = pedidoid;
        this.productoid = productoid;
    }

    public DDetallePedido(int id, int cantidad, String descripcion, int pedidoid, int productoid) {
        this.id = id;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.pedidoid = pedidoid;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getPedidoid() {
        return pedidoid;
    }

    public void setPedidoid(int pedidoid) {
        this.pedidoid = pedidoid;
    }

    public int getProductoid() {
        return productoid;
    }

    public void setProductoid(int productoid) {
        this.productoid = productoid;
    }
    
    public DefaultTableModel getDetallePedidos() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel detpedidos = new DefaultTableModel();
        String sql = "SELECT * FROM detallepedido WHERE detallepedido.pedidoid = ?";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setInt(1, this.pedidoid);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                // Agrego las tuplas a mi tabla
                detpedidos.addRow(new Object[]{
                    result.getInt("id"),
                    result.getInt("cantidad"),
                    result.getString("descripcion"),
                    result.getDouble("total"),
                    result.getInt("pedidoid"),
                    result.getInt("productoid")
                });
            }
            // Cierro Conexion
            this.conexion.cerrarConexion();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return detpedidos;
    }
    
    public int registrar() {
         // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        String sql = "INSERT INTO detallepedido(cantidad, descripcion, total, pedidoid, productoid) " +
                      "VALUES(?, ?, ?, ?, ?)";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setInt(1, this.cantidad);
            stmt.setString(2, this.descripcion);
            stmt.setDouble(3, this.total);
            stmt.setInt(4, this.pedidoid);
            stmt.setInt(5, this.productoid);
            int rows = stmt.executeUpdate();

            // Cierro Conexion
            this.conexion.cerrarConexion();

            // Obtengo el id generado pra devolverlo
            if (rows != 0) {
                ResultSet generateKeys = stmt.getGeneratedKeys();
                if (generateKeys.next()) {
                    return generateKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    public int eliminar() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        String sql = "DELETE FROM detallepedido WHERE detallepedido.id = ?";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setInt(1, this.id);
            if (stmt.execute()) {
                this.conexion.cerrarConexion();
                return 1;
            } else {
                this.conexion.cerrarConexion();
                return 0;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }
}
