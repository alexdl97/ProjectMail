/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Email.MailAdministrativo;
import Email.MailAlmacen;
import Email.MailCliente;
import Email.MailProveedor;
import Email.MailTipoEntrega;
import Email.MailTipoProducto;
import Procesador.Analex;
import Procesador.Cinta;
import Procesador.Parser;
import Procesador.Token;
import Protocolos.MimeMail;
import Protocolos.SMTP;
import utils.Tools;

/**
 *
 * @author ADL
 */
public class Impresol {

    public Impresol() throws Exception {
    }
    private MailProveedor mail_proveedor = new MailProveedor();
    private MailTipoEntrega mail_tipo_entrega = new MailTipoEntrega();
    private MailTipoProducto mail_tipo_producto = new MailTipoProducto();
    private MailAlmacen mail_almacen = new MailAlmacen();
    private MailCliente mail_cliente = new MailCliente();
    private MailAdministrativo mail_administrativo = new MailAdministrativo();

    public void procesarMensaje(String Mensaje) throws Exception {
        String destinatario = Tools.getDestinatario(Mensaje);
        System.out.println("Destinatario: " + destinatario);
        String content = Tools.getSubjectOrden(Mensaje);
        //System.out.println("ProcesarMensaje:Contenido " + content);

        //Usuario user = new Usuario();
        //Verificamos si el usuario esta registrado en el sistema
        //Quitamos formato extra en caso de reenvio o de respuesta de mensajes
        content = Tools.quitar_formato_mail(content);
        Cinta cinta = new Cinta(content);
        Analex analex = new Analex(cinta);
        Parser parser = new Parser(analex);
        // Verificar Orden
        parser.Expresion(); //verifica la estructura

        if (parser.errorFlag) {
            // Enviar Correo de Error
            SMTP.sendMail(destinatario, "Error de Comando", "El comando:  " + analex.M.texto + "\n es incorrecto!, trate consultando los comandos de ayuda con el comando HELP");
            return;
        }

        // Si todo va bien, procesar el Comando
        analex.Init();
        Token token = analex.Preanalisis();

        if (token.getNombre() == Token.HELP) {
            // Mostrar Ayudas
            System.out.println("HELP-------");
            //SMTP.sendMail(destinatario, Constante.msgAyudaPropietario, Comandos_Ayuda.AYUDA_GENERAL);
            return;
        }

        switch (token.getAtributo()) {
            // CASO DE USO USUARIO
            case Token.OBTENERCLIENTES:
                this.mail_cliente.listar(analex, destinatario);
                //System.out.println("OBTENER CLIENTES");
                break;
            case Token.REGISTRARCLIENTE:
                this.mail_cliente.registrar(analex, destinatario);
                break;
            case Token.MODIFICARCLIENTE:
                this.mail_cliente.modificar(analex, destinatario);
                break;
            case Token.ELIMINARCLIENTE:
                this.mail_cliente.eliminar(analex, destinatario);
                break;

            case Token.OBTENERADMINISTRATIVOS:
                this.mail_administrativo.listar(analex, destinatario);
                break;
            case Token.REGISTRARADMINISTRATIVO:
                this.mail_administrativo.registrar(analex, destinatario);
                break;
            case Token.MODIFICARADMINISTRATIVO:
                this.mail_administrativo.modificar(analex, destinatario);
                break;
            case Token.ELIMINARADMINISTRATIVO:
                this.mail_administrativo.eliminar(analex, destinatario);
                break;

            case Token.OBTENERPROVEEDORES:
                this.mail_proveedor.listar(analex, destinatario);
                break;
            case Token.REGISTRARPROVEEDOR:
                this.mail_proveedor.registrar(analex, destinatario);
                break;
            case Token.MODIFICARPROVEEDOR:
                this.mail_proveedor.modificar(analex, destinatario);
                break;
            case Token.ELIMINARPROVEEDOR:
                this.mail_proveedor.eliminar(analex, destinatario);
                break;

            // CASO DE USO PRODUCTO
            case Token.OBTENERTIPOSPRODUCTOS:
                this.mail_tipo_producto.listar(analex, destinatario);
                break;
            case Token.REGISTRARTIPOPRODUCTO:
                this.mail_tipo_producto.modificar(analex, destinatario);
                break;
            case Token.MODIFICARTIPOPRODUCTO:
                this.mail_tipo_producto.modificar(analex, destinatario);
                break;
            case Token.ELIMINARTIPOPRODUCTO:
                this.mail_tipo_producto.eliminar(analex, destinatario);
                break;

            case Token.OBTENERTIPOSENTREGAS:
                this.mail_tipo_entrega.listar(analex, destinatario);
                break;
            case Token.REGISTRARTIPOENTREGA:
                this.mail_tipo_entrega.registrar(analex, destinatario);
                break;
            case Token.MODIFICARTIPOENTREGA:
                this.mail_tipo_entrega.modificar(analex, destinatario);
                break;
            case Token.ELIMINARPTIPOENTREGA:
                this.mail_tipo_entrega.eliminar(analex, destinatario);
                break;

            case Token.OBTENERALMACENES:
                this.mail_almacen.listar(analex, destinatario);
                break;
            case Token.REGISTRARALMACEN:
                this.mail_almacen.registrar(analex, destinatario);
                break;
            case Token.MODIFICARALMACEN:
                this.mail_almacen.modificar(analex, destinatario);
                break;
            case Token.ELIMINARALMACEN:
                this.mail_almacen.eliminar(analex, destinatario);
                break;

            case Token.OBTENERPRODUCTOS:
                //       obtenerProductos(analex, destinatario);
                break;
            case Token.REGISTRARPRODUCTO:
                //       registrarTipoProducto(analex, destinatario);
                break;
            case Token.MODIFICARPRODUCTO:
                //       modificarTipoProducto(analex, destinatario);
                break;
            case Token.ELIMINARPRODUCTO:
                //        eliminarTipoProducto(analex, destinatario);
                break;

        }

        //Para registrar a un usuario
//        if(token.getAtributo()== Token.REGISTRARME)
//        {
//        if(user.existeUsuario(destinatario)){
//         clienteSMTP.sendMail(destinatario, "Error de Registro!", "El usuario ya se encuentra registrado");
//        }else{
//        registrarme(analex, destinatario);    
//        }                           
//        }        
//        if (user.existeUsuario(destinatario)){
//          // Sino es HELP, es una funcionalidad
//
//            switch (token.getAtributo()) {
//
//            }         
//
//        } else{
//           //clienteSMTP.sendMail(destinatario, "No se encuentra registrado!", "El sistema no puede atender sus peticiones dado que no se encuentra registrado, favor comuniquese con el administrador, Gracias... :)");   
//        }   
    }
}
