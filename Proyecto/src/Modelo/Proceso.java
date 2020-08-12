
package Modelo;

import Vista.InOut;
import java.util.ArrayList;


public class Proceso {
    
    ArrayList<Personas> personas = new ArrayList();
    
    InOut inOut = new InOut();
    
    public void crearUsuario(){

        String nombre = inOut.solicitarNombre("Digite su nombre: ");
       
         String usuario = inOut.solicitarNombre("Digite su nombre de usuario: ");
         
        while(verificarUsuario(usuario)!= -1){
             usuario = inOut.solicitarNombre("\nEl usuario ya existe. \nDigite su nombre de usuario: ");
        }
        
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
    public Personas IniciarSecion(){
        String usuario = inOut.solicitarNombre("Digite su nombre de usuario: ");
        if(verificarUsuario(usuario)!= -1){
            int pos= verificarUsuario(usuario);
             String contraseña=inOut.solicitarNombre("Ingrese la contraseña: ");
                 while(verificarContrasena(contraseña,pos)== false){
                     contraseña=inOut.solicitarNombre("Ingrese la contraseña: ");
                 }
            return personas.get(pos);
        }else{
            return null;
        }
    }
    public void menuInicio(){
        int opc=0;
        String contraseña;
        Personas usuario = new Personas();
        do{
            String mensaje= "1.Iniciar Secion \n"
                      +     "2.Registrar Cuenta \n";
            opc=inOut.solicitarEntero(mensaje);
              switch(opc)
                {
                    case 1:{
                       usuario=IniciarSecion();
                       if(usuario != null){
                          
                           
                       }else{
                           inOut.mostrarResultado("USUARIO NO ENCONTRADO");
                       }
                        break;
                    }
                    case 2:{
                        crearUsuario();
                        break;
                    }
                    default:{
                        inOut.mostrarResultado("Opción incorrecta");
                        break;
                    }
                }  
        } while(opc!=2); 
    }
}
