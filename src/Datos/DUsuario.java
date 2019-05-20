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
public class DUsuario {
    
    private int id;
    private String usuario;
    private String contrasenia;
    private String estado;
    private int administrativoid;
    private Conexion conexion;
    
    public DUsuario() {
        this.id = 0;
        this.usuario = "";
        this.contrasenia = "";
        this.estado = "";
        this.administrativoid = 0;
        conexion = Conexion.getInstancia();
    }

    public DUsuario(String usuario, String contrasenia, String estado, int administrativoid) {
        this.id = 0;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.estado = estado;
        this.administrativoid = administrativoid;
        conexion = Conexion.getInstancia();
    }
    
    public DUsuario(int id, String usuario, String contrasenia, String estado, int administrativoid) {
        this.id = id;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.estado = estado;
        this.administrativoid = administrativoid;
        conexion = Conexion.getInstancia();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return usuario;
    }

    public void setUser(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getAdministrativoid() {
        return administrativoid;
    }

    public void setAdministrativoid(int administrativoid) {
        this.administrativoid = administrativoid;
    }
    
    public DefaultTableModel getUsuarios() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel users = new DefaultTableModel();
        String sql = "SELECT * FROM usuario WHERE estado = 'A'";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                // Agrego las tuplas a mi tabla
                users.addRow(new Object[]{
                    result.getInt("id"),
                    result.getString("usuario"),
                    result.getString("contrasenia"),
                    result.getString("estado"),
                    result.getString("administrativoid"),
                });
            }
            // Cierro Conexion
            this.conexion.cerrarConexion();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }
    
    public int registrar() {
         // Abro y obtengo la conexion
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        String sql = "INSERT INTO usuario(usuario, contrasenia, estado, administrativoid) " +
                      "VALUES(?, ?, ?, ?)";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setString(1, this.usuario);
            stmt.setString(2, this.contrasenia);
            stmt.setString(3, this.estado);
            stmt.setInt(4, this.administrativoid);
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
    
    public DefaultTableModel getUsuario() {
        
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();
        DefaultTableModel user = new DefaultTableModel();
        String sql = "SELECT * FROM usuario WHERE estado = 'A' AND usuario.id = ? LIMIT 1";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setInt(1, this.id);
            ResultSet result = stmt.executeQuery();
            
             while (result.next()) {
                // Agrego las tuplas a mi tabla
                 System.out.println("NOMBRE --> " + result.getString("usuario"));
                user.addRow(new Object[] {
                    result.getInt("id"),
                    result.getString("usuario"),
                    result.getString("contrasenia"),
                    result.getString("estado"),
                    result.getString("administrativoid")
                });
            }
             
            // Cierro Conexion
            this.conexion.cerrarConexion();

            // Obtengo el id generado pra devolverlo
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }
    
    public int modificar() {
        this.conexion.abrirConexion();
        Connection con = this.conexion.getConexion();

        String sql = "UPDATE usuario SET usuario = ?, contrasenia = ?, estado = ?, administrativoid = ? " +
                "WHERE usuario.id = ?";
        try {
            // La ejecuto
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            stmt.setString(1, this.usuario);
            stmt.setString(2, this.contrasenia);
            stmt.setString(3, this.estado);
            stmt.setInt(4, this.administrativoid);
            stmt.setInt(5, this.id);
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

        String sql = "UPDATE usuario SET estado = 'D' WHERE usuario.id = ?";
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
