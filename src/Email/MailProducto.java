/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import Negocio.NProducto;
import Procesador.Analex;
import Procesador.Token;
import Protocolos.SMTP;
import utils.Tools;

/**
 *
 * @author Junior Guzman
 */
public class MailProducto extends TemplateMail{
    
    private NProducto producto;
    
    public MailProducto() throws Exception {
        this.producto = new NProducto();
    }

    @Override
    public void registrar(Analex analex, String destinatario) throws Exception {

    }

    @Override
    public void modificar(Analex analex, String destinatario) throws Exception {
         // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            //clienteNegocio clienteNegocio = new clienteNegocio();
            //String mensaje = Herramientas.dibujarTabla(clienteNegocio.mostrarClientes());
            //SMTP.sendMail(correoDest, Constante.msgAyudaPropietario+"\n\n",Comandos_Ayuda.AYUDA_REGISTRARCLIENTE);
            return;
        }
        try {
            // Sino, ejecutar el comando
            analex.Avanzar();
            // Atributos      
            String codigo = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String marca = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String modelo = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            int precio = analex.Preanalisis().getAtributo();
            analex.Avanzar();
            analex.Avanzar();
            int costo = analex.Preanalisis().getAtributo();
            analex.Avanzar();
            analex.Avanzar();
            String codigoLote = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String tipoProducto = Tools.quitarComillas(analex.Preanalisis().getToStr());
            

           // producto.modificar(codigo, marca, modelo, precio, costo, codigoLote, tipoProducto);
            System.out.println("SUPUESTAMENTE GUARDO");
        } catch (Exception e) {
            //SMTP.sendMail(correoDest, "Registrar Cliente", Constantes.IngresoErrorR+"\n"+"Mensaje enviado: "+ analex.M.texto);
            SMTP.sendMail(destinatario, "Registrar Cliente", "ERROR XD" + "\n" + "Mensaje enviado: " + analex.M.texto);

        }
    }

    @Override
    public void eliminar(Analex analex, String destinatario) throws Exception {
          // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            //SMTP.sendMail(correoDest, Constantes.MsgAyuda, Constantes.AYUDA_MOSTRARCLIENTES);
            return;
        }

        try {
            // Sino, ejecutar el comando
            analex.Avanzar();
            // Atributos      
            int id = analex.Preanalisis().getAtributo();
            analex.Avanzar();
            producto.eliminar(id);
            System.out.println("ELIMINO");
            //MimeMail mimemailer = new MimeMail();
            //mimemailer.sendHtmlEmail(correoDest, "Mostrar Clientes", "Lista de Clientes\n" + Tools.dibujarTablawithHTMLwithoutOpciones(cliente.getClientes()));            
            //SMTP.sendMail(correoDest,"OBTENERCLIENTES", "Lista de Clientes\n" + Tools.dibujarDatos(cliente.getClientes()));
        } catch (Exception e) {
            SMTP.sendMail(destinatario, "Eliminar producto", "error durante la eliminacion del producto, verifique con el comando HELP");

        }

    }

    @Override
    public void listar(Analex analex, String destinatario) throws Exception {
               // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            //SMTP.sendMail(correoDest, Constantes.MsgAyuda, Constantes.AYUDA_MOSTRARCLIENTES);
            return;
        }
        // Sino, ejecutar el comando
        try {
            //MimeMail mimemailer = new MimeMail();
            //mimemailer.sendHtmlEmail(correoDest, "Mostrar Clientes", "Lista de Clientes\n" + Tools.dibujarTablawithHTMLwithoutOpciones(cliente.getClientes()));            
            SMTP.sendMail(destinatario, "OBTENERPRODUCTOS", "Lista de productos\n" + Tools.dibujarDatos(producto.getProductos()));
        } catch (Exception e) {
            SMTP.sendMail(destinatario, "Mostrar Productos", "error durante la obtencion de la tabla, verifique con el comando HELP");

        }       
    }
    
}
