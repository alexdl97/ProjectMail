/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Negocio.NCliente;
import Procesador.Analex;
import Procesador.Cinta;
import Procesador.Parser;
import Procesador.Token;
import Protocolos.MimeMail;
import Protocolos.SMTP;
import utils.Constantes;
import utils.Tools;

/**
 *
 * @author ADL
 */
public class Impresol {
    public void procesarMensaje(String Mensaje){
        String destinatario = Tools.getDestinatario(Mensaje);        
        System.out.println("Destinatario: " + destinatario);
        String content = Tools.getSubjectOrden(Mensaje);
        System.out.println("ProcesarMensaje:Contenido " + content);
        
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
             SMTP.sendMail(destinatario, "Error de Comando", "El comando:  "+ analex.M.texto +"\n es incorrecto!, trate consultando los comandos de ayuda con el comando HELP");
            return;
        }

        // Si todo va bien, procesar el Comando
        analex.Init();
        Token token = analex.Preanalisis();

        if (token.getNombre() == Token.HELP) {
            // Mostrar Ayudas
            System.out.println("HELP----");
            //SMTP.sendMail(destinatario, Constante.msgAyudaPropietario, Comandos_Ayuda.AYUDA_GENERAL);
            return;
        }
        
        switch (token.getAtributo()) {
            case Token.OBTENERCLIENTES :
                obtenerClientes(analex, destinatario);
                System.out.println("OBTENER CLIENTES");
                break;
            case Token.REGISTRARCLIENTE :
                registrarCliente(analex, destinatario);
                break;
            case Token.MODIFICARCLIENTE :
                modificarCliente(analex, destinatario);
                break;
            case Token.ELIMINARCLIENTE :
                eliminarCliente(analex, destinatario);
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
    
    private void obtenerClientes(Analex analex, String correoDest) {
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
            MimeMail mimemailer = new MimeMail();
            //mimemailer.sendHtmlEmail(correoDest, "Mostrar Clientes", "Lista de Clientes\n" + Tools.dibujarTablawithHTMLwithoutOpciones(cliente.getClientes()));            
            SMTP.sendMail(correoDest,"OBTENERCLIENTES", "Lista de Clientes\n" + Tools.dibujarDatos(cliente.getClientes()));
        } catch (Exception e) {
            SMTP.sendMail(correoDest, "Mostrar Clientes", "error durante la obtencion de la tabla, verifique con el comando HELP");

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
            //Creamos un usuario para cada cliente al momento de crear al cliente
            //Usuario u= new Usuario();
            //u.setUsuario(correoDest, nombre,"Cliente");
            //Verificamos si existe el usuario
            /*if(! (u.existeUsuario(correoDest))){
             u.registrarUsuario();
            }*/
            //mimeMail mimemailer = new mimeMail();            
            //mimemailer.sendHtmlEmail(correoDest, "Registrar Cliente", Constante.IngresoPositivoR+"\n\n"+ Herramientas.dibujarTablawithHTMLwithoutOpciones(clienteNegocio.mostrarClientes()));                   
            //}     
            System.out.println("SUPUESTAMENTE GUARDO");
        } catch (Exception e) {
            //SMTP.sendMail(correoDest, "Registrar Cliente", Constantes.IngresoErrorR+"\n"+"Mensaje enviado: "+ analex.M.texto);
            SMTP.sendMail(correoDest, "Registrar Cliente", "ERROR XD"+"\n"+"Mensaje enviado: "+ analex.M.texto);

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
            //Creamos un usuario para cada cliente al momento de crear al cliente
            //Usuario u= new Usuario();
            //u.setUsuario(correoDest, nombre,"Cliente");
            //Verificamos si existe el usuario
            /*if(! (u.existeUsuario(correoDest))){
             u.registrarUsuario();
            }*/
            //mimeMail mimemailer = new mimeMail();            
            //mimemailer.sendHtmlEmail(correoDest, "Registrar Cliente", Constante.IngresoPositivoR+"\n\n"+ Herramientas.dibujarTablawithHTMLwithoutOpciones(clienteNegocio.mostrarClientes()));                   
            //}     
            System.out.println("SUPUESTAMENTE GUARDO");
        } catch (Exception e) {
            //SMTP.sendMail(correoDest, "Registrar Cliente", Constantes.IngresoErrorR+"\n"+"Mensaje enviado: "+ analex.M.texto);
            SMTP.sendMail(correoDest, "Registrar Cliente", "ERROR XD"+"\n"+"Mensaje enviado: "+ analex.M.texto);

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

      //  String mensaje = Herramientas.dibujarTabla(clienteNegocio.mostrarClientes());        
      //  clienteSMTP.sendMail(correoDest, "Mostrar Clientes\n\n", mensaje);   
    }
    
}
