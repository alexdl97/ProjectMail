/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DAdministrativo;
import java.sql.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADL
 */
public class NAdministrador {

    private DAdministrativo adm;

    public NAdministrador() {
        adm = new DAdministrativo();
    }

    public DefaultTableModel getAdministrativos() {
        return adm.getAdministrativos();
    }

    public int registrar(String codigo, String nombre, String telefono, String estado, String cargo, Date fecha_ingreso) {
        adm = new DAdministrativo(codigo, nombre, telefono, estado, cargo, fecha_ingreso);
        return adm.registrar();
    }

    public DefaultTableModel getAdministrativo(int id) {
        adm.setId(id);
        return adm.getAdministrativo();
    }

    public int modificar(int id, String codigo, String nombre, String telefono, String estado, String cargo, Date fecha_ingreso) {
        adm = new DAdministrativo(id, codigo, nombre, telefono, estado, cargo, fecha_ingreso);
        return adm.modificar();
    }

    public int eliminar(int id) {
        adm.setId(id);
        return adm.eliminar();
    }

    public String Mostrar() throws Exception {
          String rx = "";
        try {
            List<DAdministrativo> lObj = (List<DAdministrativo>) this.getAdministrativos();

            rx = "<center><h2>LISTA DE ADMINISTRATIVOS DE LA EMPRESA</h2></center><br>";
            rx += " <table style=\"width:100%; border-style: outset; text-align: left;\" >" +
                    "             <thead>\n" +
                    "                   <tr >\n" +
                    "                         <th>#</th>\n" +
                    "                         <th>Codigo</th>\n" +
                    "                         <th>Nombre</th>\n" +
                    "                         <th>Telefono</th>\n" +
                    "                         <th>Cargo</th>\n" +
                    "                   </tr>\n" +
                    "             </thead>\n" +
                    "                  <tbody> ";
            for (DAdministrativo obj : lObj) {
                rx = rx +
                        "<tr style=\"\">\n" +
                        "   <td>"+ obj.getId() + "</td>\n" +
                        "   <td>"+ obj.getCodigo()+ "</td>\n" +
                        "   <td>"+ obj.getNombre()+ "</td>\n" +
                        "   <td>"+ obj.getTelefono()+ "</td>\n" +
                        "   <td>"+ obj.getCargo()+ "</td>\n" +
                        "</tr>\n"
                ;
            }
            rx  +=   "  </tbody>\n" +
                    "</table>\n"
            ;
        } catch (Exception e){
            throw e;
        }
        return rx;
    }
}
