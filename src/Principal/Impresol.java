/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Negocio.NAdministrativo;
import Negocio.NCliente;
import Negocio.NProveedor;
import Negocio.NTipoProducto;
import Procesador.Analex;
import Procesador.Cinta;
import Procesador.Parser;
import Procesador.Token;
import Protocolos.MimeMail;
import Protocolos.SMTP;
import java.sql.Date;
import utils.Constantes;
import utils.Tools;

/**
 *
 * @author ADL
 */
public class Impresol {

    public void procesarMensaje(String Mensaje) {
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
                obtenerClientes(analex, destinatario);
                //System.out.println("OBTENER CLIENTES");
                break;
            case Token.REGISTRARCLIENTE:
                registrarCliente(analex, destinatario);
                break;
            case Token.MODIFICARCLIENTE:
                modificarCliente(analex, destinatario);
                break;
            case Token.ELIMINARCLIENTE:
                eliminarCliente(analex, destinatario);
                break;

            case Token.OBTENERADMINISTRATIVOS:
                obtenerAdministrativos(analex, destinatario);
                break;
            case Token.REGISTRARADMINISTRATIVO:
                registrarAdministrativo(analex, destinatario);
                break;
            case Token.MODIFICARADMINISTRATIVO:
                modificarAdministrativo(analex, destinatario);
                break;
            case Token.ELIMINARADMINISTRATIVO:
                eliminarAdministrativo(analex, destinatario);
                break;

            case Token.OBTENERPROVEEDORES:
                obtenerProveedores(analex, destinatario);
                break;
            case Token.REGISTRARPROVEEDOR:
                registrarProveedor(analex, destinatario);
                break;
            case Token.MODIFICARPROVEEDOR:
                modificarProveedor(analex, destinatario);
                break;
            case Token.ELIMINARPROVEEDOR:
                eliminarProveedor(analex, destinatario);
                break;
                
             // CASO DE USO PRODUCTO
            case Token.OBTENERTIPOSPRODUCTOS:
                obtenerTiposProductos(analex, destinatario);
                break;
            case Token.REGISTRARTIPOPRODUCTO:
                registrarTipoProducto(analex, destinatario);
                break;
            case Token.MODIFICARTIPOPRODUCTO:
                modificarTipoProducto(analex, destinatario);
                break;
            case Token.ELIMINARTIPOPRODUCTO:
                eliminarTipoProducto(analex, destinatario);
                break;
                
            case Token.OBTENERPRODUCTOS:
                obtenerProductos(analex, destinatario);
                break;
            case Token.REGISTRARPRODUCTO:
                registrarTipoProducto(analex, destinatario);
                break;
            case Token.MODIFICARPRODUCTO:
                modificarTipoProducto(analex, destinatario);
                break;
            case Token.ELIMINARPRODUCTO:
                eliminarTipoProducto(analex, destinatario);
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

    private void obtenerClientes(Analex analex, String destinatario) {
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
        NCliente cliente = new NCliente();
        try {
            //MimeMail mimemailer = new MimeMail();
            //mimemailer.sendHtmlEmail(correoDest, "Mostrar Clientes", "Lista de Clientes\n" + Tools.dibujarTablawithHTMLwithoutOpciones(cliente.getClientes()));            
            SMTP.sendMail(destinatario, "OBTENERCLIENTES", "Lista de Clientes\n" + Tools.dibujarDatos(cliente.getClientes()));
        } catch (Exception e) {
            SMTP.sendMail(destinatario, "Mostrar Clientes", "error durante la obtencion de la tabla, verifique con el comando HELP");

        }

        //  String mensaje = Herramientas.dibujarTabla(clienteNegocio.mostrarClientes());        
        //  clienteSMTP.sendMail(correoDest, "Mostrar Clientes\n\n", mensaje);   
    }

    private void registrarCliente(Analex analex, String correoDest) {
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
            NCliente cliente = new NCliente();
            analex.Avanzar();
            // Atributos      
            String codigo = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            int nit = analex.Preanalisis().getAtributo();
            analex.Avanzar();
            analex.Avanzar();
            String nombre = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String telefono = Tools.quitarComillas(analex.Preanalisis().getToStr());

            cliente.registrar(codigo, nit, nombre, telefono);

            System.out.println("SUPUESTAMENTE GUARDO");
        } catch (Exception e) {
            //SMTP.sendMail(correoDest, "Registrar Cliente", Constantes.IngresoErrorR+"\n"+"Mensaje enviado: "+ analex.M.texto);
            SMTP.sendMail(correoDest, "Registrar Cliente", "ERROR XD" + "\n" + "Mensaje enviado: " + analex.M.texto);

        }
    }

    private void modificarCliente(Analex analex, String correoDest) {
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
            NCliente cliente = new NCliente();
            analex.Avanzar();
            // Atributos      
            String codigo = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            int nit = analex.Preanalisis().getAtributo();
            analex.Avanzar();
            analex.Avanzar();
            String nombre = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String telefono = Tools.quitarComillas(analex.Preanalisis().getToStr());

            cliente.modificar(codigo, nit, nombre, telefono);
            System.out.println("SUPUESTAMENTE GUARDO");
        } catch (Exception e) {
            //SMTP.sendMail(correoDest, "Registrar Cliente", Constantes.IngresoErrorR+"\n"+"Mensaje enviado: "+ analex.M.texto);
            SMTP.sendMail(correoDest, "Registrar Cliente", "ERROR XD" + "\n" + "Mensaje enviado: " + analex.M.texto);

        }
    }

    private void eliminarCliente(Analex analex, String correoDest) {
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
            NCliente cliente = new NCliente();
            analex.Avanzar();
            // Atributos      
            String codigo = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            cliente.eliminar(codigo);
            System.out.println("ELIMINO");
            //MimeMail mimemailer = new MimeMail();
            //mimemailer.sendHtmlEmail(correoDest, "Mostrar Clientes", "Lista de Clientes\n" + Tools.dibujarTablawithHTMLwithoutOpciones(cliente.getClientes()));            
            //SMTP.sendMail(correoDest,"OBTENERCLIENTES", "Lista de Clientes\n" + Tools.dibujarDatos(cliente.getClientes()));
        } catch (Exception e) {
            SMTP.sendMail(correoDest, "Mostrar Clientes", "error durante la obtencion de la tabla, verifique con el comando HELP");

        }

    }

    private void obtenerAdministrativos(Analex analex, String destinatario) {
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
        NAdministrativo adm = new NAdministrativo();
        try {
            //MimeMail mimemailer = new MimeMail();
            //mimemailer.sendHtmlEmail(correoDest, "Mostrar Clientes", "Lista de Clientes\n" + Tools.dibujarTablawithHTMLwithoutOpciones(cliente.getClientes()));            
            SMTP.sendMail(destinatario, "OBTENERCLIENTES", "Lista de Clientes\n" + Tools.dibujarDatos(adm.getAdministrativos()));
        } catch (Exception e) {
            SMTP.sendMail(destinatario, "Mostrar Clientes", "error durante la obtencion de la tabla, verifique con el comando HELP");

        }

        //  String mensaje = Herramientas.dibujarTabla(clienteNegocio.mostrarClientes());        
        //  clienteSMTP.sendMail(correoDest, "Mostrar Clientes\n\n", mensaje);   
    }

    private void registrarAdministrativo(Analex analex, String correoDest) {
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
            NAdministrativo adm = new NAdministrativo();
            analex.Avanzar();
            // Atributos      
            String codigo = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String nombre = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String telefono = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String cargo = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String fecha_ingreso = Tools.quitarComillas(analex.Preanalisis().getToStr());
            int y = Integer.parseInt(Character.toString(fecha_ingreso.charAt(0)));
            int m = Integer.parseInt(Character.toString(fecha_ingreso.charAt(1))) + 1;
            int d = Integer.parseInt(Character.toString(fecha_ingreso.charAt(2)));
            Date f = new Date(1000);
            //String y = fecha_ingreso.charAt(0);
            //REGISTAR {"adm","alex",123213,"2017-19-19"}

            adm.registrar(codigo, nombre, telefono, cargo, f);
            //mimeMail mimemailer = new mimeMail();            
            //mimemailer.sendHtmlEmail(correoDest, "Registrar Cliente", Constante.IngresoPositivoR+"\n\n"+ Herramientas.dibujarTablawithHTMLwithoutOpciones(clienteNegocio.mostrarClientes()));                   
            System.out.println("SUPUESTAMENTE MODIFICO");
        } catch (Exception e) {
            //SMTP.sendMail(correoDest, "Registrar Cliente", Constantes.IngresoErrorR+"\n"+"Mensaje enviado: "+ analex.M.texto);
            SMTP.sendMail(correoDest, "Registrar Cliente", "ERROR XD" + "\n" + "Mensaje enviado: " + analex.M.texto);

        }
    }

    private void modificarAdministrativo(Analex analex, String correoDest) {
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
            NAdministrativo adm = new NAdministrativo();
            analex.Avanzar();
            // Atributos      
            String codigo = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String nombre = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String telefono = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String cargo = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String fecha_ingreso = Tools.quitarComillas(analex.Preanalisis().getToStr());
            int y = Integer.parseInt(Character.toString(fecha_ingreso.charAt(0)));
            int m = Integer.parseInt(Character.toString(fecha_ingreso.charAt(1))) + 1;
            int d = Integer.parseInt(Character.toString(fecha_ingreso.charAt(2)));
            Date f = new Date(1000);
            //String y = fecha_ingreso.charAt(0);
            //REGISTAR {"adm","alex",123213,"2017-19-19"}

            adm.modificar(codigo, nombre, telefono, cargo, f);
            System.out.println("SUPUESTAMENTE ACTUALIZDO");
        } catch (Exception e) {
            //SMTP.sendMail(correoDest, "Registrar Cliente", Constantes.IngresoErrorR+"\n"+"Mensaje enviado: "+ analex.M.texto);
            SMTP.sendMail(correoDest, "Registrar Cliente", "ERROR XD" + "\n" + "Mensaje enviado: " + analex.M.texto);

        }
    }

    private void eliminarAdministrativo(Analex analex, String correoDest) {
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
            NAdministrativo adm = new NAdministrativo();
            analex.Avanzar();
            // Atributos      
            String codigo = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            adm.eliminar(codigo);
            System.out.println("ELIMINO");
            //MimeMail mimemailer = new MimeMail();
            //mimemailer.sendHtmlEmail(correoDest, "Mostrar Clientes", "Lista de Clientes\n" + Tools.dibujarTablawithHTMLwithoutOpciones(cliente.getClientes()));            
            //SMTP.sendMail(correoDest,"OBTENERCLIENTES", "Lista de Clientes\n" + Tools.dibujarDatos(cliente.getClientes()));
        } catch (Exception e) {
            SMTP.sendMail(correoDest, "Mostrar Clientes", "error durante la obtencion de la tabla, verifique con el comando HELP");

        }

    }

      private void obtenerProveedores(Analex analex, String destinatario) {
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
        NProveedor proveedor = new NProveedor();
        try {
            //MimeMail mimemailer = new MimeMail();
            //mimemailer.sendHtmlEmail(correoDest, "Mostrar Clientes", "Lista de Clientes\n" + Tools.dibujarTablawithHTMLwithoutOpciones(cliente.getClientes()));            
            SMTP.sendMail(destinatario, "OBTENER PROVEEDORES", "Lista de PROVEEDORES\n" + Tools.dibujarDatos(proveedor.getProveedores()));
        } catch (Exception e) {
            SMTP.sendMail(destinatario, "Mostrar Proveedores", "error durante la obtencion de la tabla, verifique con el comando HELP");

        }

        //  String mensaje = Herramientas.dibujarTabla(clienteNegocio.mostrarClientes());        
        //  clienteSMTP.sendMail(correoDest, "Mostrar Clientes\n\n", mensaje);   
    }

    private void registrarProveedor(Analex analex, String correoDest) {
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
            NProveedor proveedor = new NProveedor();
            analex.Avanzar();
            // Atributos      
            String codigo = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String nombre = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String telefono = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String direccion = Tools.quitarComillas(analex.Preanalisis().getToStr());

            proveedor.registrar(codigo, nombre, telefono, direccion);
            //mimeMail mimemailer = new mimeMail();            
            //mimemailer.sendHtmlEmail(correoDest, "Registrar Cliente", Constante.IngresoPositivoR+"\n\n"+ Herramientas.dibujarTablawithHTMLwithoutOpciones(clienteNegocio.mostrarClientes()));                   
            System.out.println("SUPUESTAMENTE MODIFICO");
        } catch (Exception e) {
            //SMTP.sendMail(correoDest, "Registrar Cliente", Constantes.IngresoErrorR+"\n"+"Mensaje enviado: "+ analex.M.texto);
            SMTP.sendMail(correoDest, "Registrar PROVEEDOR", "ERROR XD" + "\n" + "Mensaje enviado: " + analex.M.texto);

        }
    }

    private void modificarProveedor(Analex analex, String correoDest) {
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
            NProveedor proveedor = new NProveedor();
            analex.Avanzar();
            // Atributos      
            String codigo = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String nombre = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String telefono = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String direccion = Tools.quitarComillas(analex.Preanalisis().getToStr());

            proveedor.modificar(codigo, nombre, telefono, direccion);
            System.out.println("SUPUESTAMENTE ACTUALIZDO");
        } catch (Exception e) {
            //SMTP.sendMail(correoDest, "Registrar Cliente", Constantes.IngresoErrorR+"\n"+"Mensaje enviado: "+ analex.M.texto);
            SMTP.sendMail(correoDest, "Actualizar Proveedor", "ERROR XD" + "\n" + "Mensaje enviado: " + analex.M.texto);

        }
    }

    private void eliminarProveedor(Analex analex, String correoDest) {
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
            NProveedor proveedor = new NProveedor();
            analex.Avanzar();
            // Atributos      
            String codigo = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            proveedor.eliminar(codigo);
            System.out.println("ELIMINO");
            //MimeMail mimemailer = new MimeMail();
            //mimemailer.sendHtmlEmail(correoDest, "Mostrar Clientes", "Lista de Clientes\n" + Tools.dibujarTablawithHTMLwithoutOpciones(cliente.getClientes()));            
            //SMTP.sendMail(correoDest,"OBTENERCLIENTES", "Lista de Clientes\n" + Tools.dibujarDatos(cliente.getClientes()));
        } catch (Exception e) {
            SMTP.sendMail(correoDest, "Eliminar proveedor", "error durante la obtencion de la tabla, verifique con el comando HELP");

        }
    }
     private void obtenerTiposProductos(Analex analex, String destinatario) {
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
         NTipoProducto tipoProducto = new NTipoProducto();
        try {
            //MimeMail mimemailer = new MimeMail();
            //mimemailer.sendHtmlEmail(correoDest, "Mostrar Clientes", "Lista de Clientes\n" + Tools.dibujarTablawithHTMLwithoutOpciones(cliente.getClientes()));            
            SMTP.sendMail(destinatario, "OBTENER TIPO PRODUCTO", "Lista de TIPO PRODUCTO\n" + Tools.dibujarDatos(tipoProducto.getTipoProducto()));
        } catch (Exception e) {
            SMTP.sendMail(destinatario, "Mostrar tipo Producto", "error durante la obtencion de la tabla, verifique con el comando HELP");

        }

        //  String mensaje = Herramientas.dibujarTabla(clienteNegocio.mostrarClientes());        
        //  clienteSMTP.sendMail(correoDest, "Mostrar Clientes\n\n", mensaje);   
    }

    
     private void registrarTipoProducto(Analex analex, String correoDest) {
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
            NTipoProducto tipoProducto = new NTipoProducto();
            analex.Avanzar();
            // Atributos      
            String descripcion = Tools.quitarComillas(analex.Preanalisis().getToStr());
            tipoProducto.registrar(descripcion);
            //mimeMail mimemailer = new mimeMail();            
            //mimemailer.sendHtmlEmail(correoDest, "Registrar Cliente", Constante.IngresoPositivoR+"\n\n"+ Herramientas.dibujarTablawithHTMLwithoutOpciones(clienteNegocio.mostrarClientes()));                   
            System.out.println("SUPUESTAMENTE REGISTRO");
        } catch (Exception e) {
            //SMTP.sendMail(correoDest, "Registrar Cliente", Constantes.IngresoErrorR+"\n"+"Mensaje enviado: "+ analex.M.texto);
            SMTP.sendMail(correoDest, "Registrar TIPO DE PRODUCTO", "ERROR XD" + "\n" + "Mensaje enviado: " + analex.M.texto);

        }
    }

    private void modificarTipoProducto(Analex analex, String correoDest) {
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
            NTipoProducto tipoProducto = new NTipoProducto();
            analex.Avanzar();
            // Atributos      
            String id = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            analex.Avanzar();
            String descripcion = Tools.quitarComillas(analex.Preanalisis().getToStr());
            tipoProducto.modificar(Integer.parseInt(id), descripcion);
            System.out.println("SUPUESTAMENTE ACTUALIZDO");
        } catch (Exception e) {
            //SMTP.sendMail(correoDest, "Registrar Cliente", Constantes.IngresoErrorR+"\n"+"Mensaje enviado: "+ analex.M.texto);
            SMTP.sendMail(correoDest, "Actualizar tipo de producto", "ERROR XD" + "\n" + "Mensaje enviado: " + analex.M.texto);

        }
    }

    private void eliminarTipoProducto(Analex analex, String correoDest) {
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
            NTipoProducto tipoProducto = new NTipoProducto();
            analex.Avanzar();
            // Atributos      
            String id = Tools.quitarComillas(analex.Preanalisis().getToStr());
            analex.Avanzar();
            tipoProducto.eliminar(Integer.parseInt(id));
            System.out.println("ELIMINO");
            //MimeMail mimemailer = new MimeMail();
            //mimemailer.sendHtmlEmail(correoDest, "Mostrar Clientes", "Lista de Clientes\n" + Tools.dibujarTablawithHTMLwithoutOpciones(cliente.getClientes()));            
            //SMTP.sendMail(correoDest,"OBTENERCLIENTES", "Lista de Clientes\n" + Tools.dibujarDatos(cliente.getClientes()));
        } catch (Exception e) {
            SMTP.sendMail(correoDest, "Eliminar Tipo producto", "error durante la obtencion de la tabla, verifique con el comando HELP");

        }
    }


}
