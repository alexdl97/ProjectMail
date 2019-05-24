/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesador;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author ADL
 */
public class TPC {

    private static final LinkedList<String> lexemas = new LinkedList<>(Arrays.asList(
            "HELP",
            "TRUE",
            "FALSE",
            // CU GESTION DE USUARIO
            "REGISTRARCLIENTE",
            "MODIFICARCLIENTE",
            "ELIMINARCLIENTE",
            "OBTENERCLIENTE",
            "OBTENERCLIENTES",
            "OBTENERADMINISTRATIVOS",
            "REGISTRARADMINISTRATIVO",
            "MODIFICARADMINISTRATIVO",
            "ELIMINARADMINISTRATIVO",
            "OBTENERPROVEEDORES",
            "REGISTRARPROVEEDOR",
            "MODIFICARPROVEEDOR",
            "ELIMINARPROVEEDOR",
            // CU GESTIONAR PRODUCTO
            "OBTENERTIPOSPRODUCTOS",
            "REGISTRARTIPOPRODUCTO",
            "MODIFICARTIPOPRODUCTO",
            "ELIMINARTIPOPRODUCTO",
            "OBTENERPRODUCTOS",
            "REGISTRARPRODUCTO",
            "MODIFICARPRODUCTO",
            "ELIMINARPRODUCTO",
            
            "OBTENERTIPOSENTREGAS",
            "REGISTRARTIPOENTREGA",
            "MODIFICARTIPOENTREGA",
            "ELIMINARTIPOENTREGA",
            
            "OBTENERALMACENES",
            "REGISTRARALMACEN",
            "MODIFICARALMACEN",
            "ELIMINARALMACEN"
            
    ));

    private static final LinkedList<Token> tokens = new LinkedList<>(Arrays.asList(
            new Token(Token.HELP, -1, "HELP"),
            new Token(Token.TRUE, -1, "TRUE"),
            new Token(Token.FALSE, -1, "FALSE"),
            // CASO DE USO GESTIONAR USUARIO
            new Token(Token.FUNC, Token.REGISTRARCLIENTE, "REGISTRARCLIENTE"),
            new Token(Token.FUNC, Token.MODIFICARCLIENTE, "MODIFICARCLIENTE"),
            new Token(Token.FUNC, Token.ELIMINARCLIENTE, "ELIMINARCLIENTE"),
            new Token(Token.FUNC, Token.OBTENERCLIENTE, "OBTENERCLIENTE"),
            new Token(Token.FUNC, Token.OBTENERCLIENTES, "OBTENERCLIENTES"),
            
            new Token(Token.FUNC, Token.OBTENERADMINISTRATIVOS, "OBTENERADMINISTRATIVOS"),
            new Token(Token.FUNC, Token.REGISTRARADMINISTRATIVO, "REGISTRARADMINISTRATIVO"),
            new Token(Token.FUNC, Token.MODIFICARADMINISTRATIVO, "MODIFICARADMINISTRATIVO"),
            new Token(Token.FUNC, Token.ELIMINARADMINISTRATIVO, "ELIMINARADMINISTRATIVO"),
            
            new Token(Token.FUNC, Token.OBTENERPROVEEDORES, "OBTENERPROVEEDORES"),
            new Token(Token.FUNC, Token.REGISTRARPROVEEDOR, "REGISTRARPROVEEDOR"),
            new Token(Token.FUNC, Token.MODIFICARPROVEEDOR, "MODIFICARPROVEEDOR"),
            new Token(Token.FUNC, Token.ELIMINARPROVEEDOR, "ELIMINARPROVEEDOR"),
            // CASO DE USO GESTIONAR PRODUCTO
            new Token(Token.FUNC, Token.OBTENERTIPOSPRODUCTOS, "OBTENERTIPOSPRODUCTOS"),
            new Token(Token.FUNC, Token.REGISTRARTIPOPRODUCTO, "REGISTRARTIPOPRODUCTO"),
            new Token(Token.FUNC, Token.MODIFICARTIPOPRODUCTO, "MODIFICARTIPOPRODUCTO"),
            new Token(Token.FUNC, Token.ELIMINARTIPOPRODUCTO, "ELIMINARTIPOPRODUCTO"),
            
            new Token(Token.FUNC, Token.OBTENERPRODUCTOS, "OBTENERPRODUCTOS"),
            new Token(Token.FUNC, Token.REGISTRARPRODUCTO, "REGISTRARPRODUCTO"),
            new Token(Token.FUNC, Token.MODIFICARPRODUCTO, "MODIFICARPRODUCTO"),
            new Token(Token.FUNC, Token.ELIMINARPRODUCTO, "ELIMINARPRODUCTO"),
            
            new Token(Token.FUNC, Token.OBTENERTIPOSENTREGAS, "OBTENERTIPOSENTREGAS"),
            new Token(Token.FUNC, Token.REGISTRARTIPOPRODUCTO, "REGISTRARTIPOENTREGA"),
            new Token(Token.FUNC, Token.MODIFICARTIPOPRODUCTO, "MODIFICARTIPOENTREGA"),
            new Token(Token.FUNC, Token.ELIMINARTIPOPRODUCTO, "ELIMINARTIPOENTREGA"),
            
            new Token(Token.FUNC, Token.OBTENERALMACENES, "OBTENERALMACENES"),
            new Token(Token.FUNC, Token.REGISTRARALMACEN, "REGISTRARALMACEN"),
            new Token(Token.FUNC, Token.MODIFICARALMACEN, "MODIFICARALMACEN"),
            new Token(Token.FUNC, Token.ELIMINARALMACEN, "ELIMINARALMACEN")
    ));

    public static Token estaEnTPC(String lexema) {
        lexema = lexema.toUpperCase();
        for (int i = 0; i < lexemas.size(); i++) {
            if (lexemas.get(i).toUpperCase().equals(lexema)) {
                Token token = new Token();
                token.setNombre(tokens.get(i).getNombre());
                token.setAtributo(tokens.get(i).getAtributo());
                token.setToStr(tokens.get(i).getToStr());
                return token;
            }
        }
        return null;
    }
}
