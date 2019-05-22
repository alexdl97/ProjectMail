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
            case Token.OBTENERCLIENTES:
                mostrarClientes(analex, destinatario);
                System.out.println("OBTENER CLIENTES");
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
    
    private void mostrarClientes(Analex analex, String correoDest) {
       // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            //SMTP.sendMail(correoDest, Constantes.MsgAyuda, Comandos_Ayuda.AYUDA_MOSTRARCLIENTES);
            return;
        }
        // Sino, ejecutar el comando
        NCliente cliente = new NCliente();   
        
        
        try {
            MimeMail mimemailer = new MimeMail();
            //mimemailer.sendHtmlEmail(correoDest, "Mostrar Clientes", "Lista de Clientes\n" + Tools.dibujarTablawithHTMLwithoutOpciones(cliente.getClientes()));            
            SMTP.sendMail(correoDest,"OBTENERCLIENTES", "Lista de Clientes\n" + Tools.dibujarTablawithHTMLwithoutOpciones(cliente.getClientes()));
        } catch (Exception e) {
            SMTP.sendMail(correoDest, "Mostrar Clientes", "error durante la obtencion de la tabla, verifique con el comando HELP");

        }

      //  String mensaje = Herramientas.dibujarTabla(clienteNegocio.mostrarClientes());        
      //  clienteSMTP.sendMail(correoDest, "Mostrar Clientes\n\n", mensaje);   
    }
}
