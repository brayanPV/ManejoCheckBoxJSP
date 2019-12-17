/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NEGOCIO;

import DAO.Conexion;
import DAO.PruebaJpaController;
import DAO.UsuarioJpaController;
import DTO.Prueba;
import DTO.Usuario;
import java.util.List;

/**
 *
 * @author stive
 */
public class SegundoParcial {

    Conexion con = Conexion.getConexion();
    UsuarioJpaController usuarioDAO = new UsuarioJpaController(con.getBd());
    List<Usuario> usuarios = usuarioDAO.findUsuarioEntities();
    PruebaJpaController pruebaDAO = new PruebaJpaController(con.getBd());
    List<Prueba> pruebas = pruebaDAO.findPruebaEntities();

    public SegundoParcial() {
    }

    public boolean createUsuario(String correo, String password) {
        Usuario u = new Usuario();
        u = findUsuario(correo);
        String[] parte = correo.split("@");
        if (u != null) {
            return false;
        } else {
            if (parte[1].equals("ufps.edu.co")) {
                if (password.length() == 6) {
                    Usuario usuario = new Usuario();
                    usuario.setCorreo(correo);
                    int p = Integer.parseInt(password);
                    usuario.setPassword(p);
                    try {
                        usuarioDAO.create(usuario);
                        return true;
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
        }
        return false;
    }

    public Usuario login(String correo, String contraseña) {
        Usuario u = new Usuario();
        u = findUsuario(correo);
        int password = Integer.parseInt(contraseña);
        if (u == null) {
            return null;
        } else {
            if (u.getPassword() == password) {
                return u;
            }
        }
        return null;
    }

    public Usuario findUsuario(String correo) {
        return usuarioDAO.findUsuario(correo);
    }

    public boolean realizarPrueba(String correo, String[] check) {
        int suma = 0;
        Usuario u = new Usuario();
        u = findUsuario(correo);
        if (u == null) {
            return false;
        }
        if (check != null) {
            for (int i = 0; i < check.length; i++) {
                suma += Integer.parseInt(check[i]);
            }
            try {
                Prueba p = new Prueba();
                p.setIdusuario(u);
                p.setResultado(suma);
                pruebaDAO.create(p);
                return true;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return false;
    }

    public UsuarioJpaController getUsuarioDAO() {
        return usuarioDAO;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

}
