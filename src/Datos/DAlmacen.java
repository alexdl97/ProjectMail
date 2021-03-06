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
public class DAlmacen {
    
    private int id;
    private String codigo;
    private Conexion conexion;
    
    public DAlmacen() {
        id = 0;
        codigo = "";
        conexion = Conexion.getInstancia();
    }
    
    public DAlmacen(String codigo) {
        this.id = 0;
        this.codigo = codigo;
        conexion = Conexion.getInstancia();
    }
    
    public DAlmacen(int id, String codigo) {
        this.id = id;
        this.codigo = codigo;
        conexion = Conexion.getInstancia();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public DefaultTableModel getAlmacenes() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel lotes = new DefaultTableModel();
        String sql = "SELECT * FROM almacen";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                // Agrego las tuplas a mi tabla
                lotes.addRow(new Object[]{
                    result.getInt("id"),
                    result.getString("codigo")
                });
            }
            // Cierro Conexion
            this.conexion.cerrarConexion();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lotes;
    }
    
    public DefaultTableModel getAlmacen() {
        
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel lote = new DefaultTableModel();
        String sql = "SELECT * FROM almacen WHERE almacen.id = ?";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setInt(1, this.id);
            ResultSet result = stmt.executeQuery();
            
             while (result.next()) {
                // Agrego las tuplas a mi tabla
                lote.addRow(new Object[] {
                    result.getInt("id"),
                    result.getString("codigo")
                });
            }
             
            // Cierro Conexion
            this.conexion.cerrarConexion();

            // Obtengo el id generado pra devolverlo
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lote;
    }
    
    public int eliminar() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        String sql = "DELETE FROM almacen WHERE almacen.id = ?";
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
