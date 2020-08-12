
package Modelo;


public class Personas {
    private String nom;
    private String usuario;
    private String contrasena;

    public Personas(String nom, String usuario, String contrasena) {
        this.nom = nom;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public String getNom() {
        return nom;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }
    
    
}
