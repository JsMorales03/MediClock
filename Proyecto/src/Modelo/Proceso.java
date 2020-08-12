
package Modelo;

import Vista.InOut;
import java.util.ArrayList;


public class Proceso {
    
    ArrayList<Personas> personas = new ArrayList();
    
    InOut inOut = new InOut();
    
    public void crearUsuario(){

        String nombre = inOut.solicitarNombre("Digite su nombre: ");
        String  usuario = inOut.solicitarNombre("Digite su nombre de usuario: ");
        String contrasena = inOut.solicitarNombre("Digite su contraseña: ");
           
        Personas persona = new Personas(nombre,usuario,contrasena);
        
        personas.add(persona);
    }
    
    public void ingresar(){
        
        String usuario = inOut.solicitarNombre("Digite su usuario");
        
        int posicion = verificarUsuario(usuario);
        
        if(posicion != -1){
            
            String contrasena = inOut.solicitarNombre("Digite su contraseña: ");
            
            if(verificarContrasena(contrasena, posicion)){
                
                
            }
                
        } else
            inOut.mostrarResultado("El usuario es incorrecto.");
        
        
    }
    
    public int verificarUsuario(String usuario){
        
        for(int i=0 ; i<personas.size(); i++){
            
            if(personas.get(i).getUsuario().equals(usuario))
                    return i;
        }
        
        return -1;
        
    }
    
    public boolean verificarContrasena(String contrasena, int posicion){
        
        if(personas.get(posicion).getContrasena().equals(contrasena))
            return true;
        else
            return false;
           
    }
    
}
