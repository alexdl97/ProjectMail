/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmail;

import Protocolos.POP;
import Protocolos.SMTP;

/**
 *
 * @author ADL
 */
public class ProjectMail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //POP.readMail();
        SMTP.sendMail("alexdl97@hotmail.com", "ADL", "CONTENT-ADL");

    }
    
}
