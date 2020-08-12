
package Modelo;

import Vista.InOut;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

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
    public void insertarMedicamento(Personas obj_persona)
    {
        Medicamentos obj_medicamento = new Medicamentos();
        obj_medicamento.setId_medicamento(obj_persona.getLista_medicamentos().size()+1);
        obj_medicamento.setNombre_medicamento(inOut.solicitarNombre("Digite el nombre del medicamento"));
        if(validarExistenciaMedicamento(obj_persona.getLista_medicamentos(),obj_medicamento.getNombre_medicamento()))
        {
            while(validarExistenciaMedicamento(obj_persona.getLista_medicamentos(),obj_medicamento.getNombre_medicamento())&&JOptionPane.showConfirmDialog(null,"El medicamento ingresado ya se encuentra registrado \n¿Desea continuar con el proceso?","Seleccione una opción", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
            {
                obj_medicamento.setNombre_medicamento(inOut.solicitarNombre("El medicamento registrado ya se encuentra registrado\nDigite el nombre del medicamento")); 
            }
            
        }
        if(!validarExistenciaMedicamento(obj_persona.getLista_medicamentos(),obj_medicamento.getNombre_medicamento()))
        {
            obj_medicamento.setCantidad_medicamento(inOut.solicitarDoubles("Digite el contenido neto del producto"));
            while(obj_medicamento.getCantidad_medicamento()<=0)
            {
              obj_medicamento.setCantidad_medicamento(inOut.solicitarDoubles("Digite el contenido neto del producto")); 
            }
            obj_medicamento.setUnidad_medida(inOut.solicitarNombre("Digite la unidad de medida"));
           asignarHorario(obj_medicamento);
           obj_persona.setMedicamento(obj_medicamento);
        }
    }
    public void asignarHorario(Medicamentos obj_medicamento)
    {
        Horarios obj_horario = new Horarios();
           String mensaje= "1.Lunes\n2.Martes\n3.Miercoles\n4.Jueves\n5.Viernes\n6.Sábado\n7.Domingo";
            do 
            {
                int opcion= inOut.solicitarEntero(mensaje+"\n\nDigite una opción");

                obj_horario.setDia(seleccionarDias(opcion));
                while(obj_horario.getDia()==null)
                {
                    opcion= inOut.solicitarEntero(mensaje+"\n\nOpción no encontrada\nDigite una opción");
                    obj_horario.setDia(seleccionarDias(opcion));
                }
                obj_horario.setHora(inOut.solicitarNombre("Digite la hora en formato de 24h\nEjemplo: 14:30"));
                while(!validarFormato(obj_horario.getHora()))
                {
                 obj_horario.setHora(inOut.solicitarNombre("FORMATO INCORRECTO\nDigite la hora en formato de 24h\nEjemplo: 14:30"));
                }
               obj_horario.setDosis(inOut.solicitarDoubles("Digite cuantos "+obj_medicamento.getUnidad_medida()+ " ingiere el "+obj_horario.getDia()));
               obj_medicamento.setHorario(obj_horario);
            }
            while(JOptionPane.showConfirmDialog(null,"¿Desea registrar un nuevo recordatorio?","Seleccione una opcion",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION);
    }
    public String seleccionarDias(int opc)
    {
        switch(opc)
        {
            case 1:{
                return String.valueOf(Calendar.MONDAY);
            }
            case 2:{
                return String.valueOf(Calendar.TUESDAY);
            }
            case 3:{
                return String.valueOf(Calendar.WEDNESDAY);
            }
            case 4:{
                return String.valueOf(Calendar.THURSDAY);
            }
            case 5:{
                return String.valueOf(Calendar.FRIDAY);
            }
            case 6:{
                return String.valueOf(Calendar.SATURDAY);
            }
            case 7:{
                return String.valueOf(Calendar.FRIDAY);
            }
            default:{
                return null;
            }
        }
    }
    public void listarMedicamentos(Personas obj_persona)
    {
        if(!obj_persona.getLista_medicamentos().isEmpty())
        inOut.mostrarResultado(obj_persona.toString());
        else
        inOut.mostrarResultado("No hay ningun medicamento registrado");
    }

    
    public boolean validarExistenciaMedicamento(ArrayList<Medicamentos> lista_medicamentos,String nombre_medicamento)
    {
        if(!lista_medicamentos.isEmpty())
        {
          for(Medicamentos medicamento:lista_medicamentos)
            {
            if(medicamento.getNombre_medicamento().equalsIgnoreCase(nombre_medicamento))
                   return true;
            }
        }
        return false;
    }
    public boolean validarFormato(String horario)
    {
        int horas;
        int minutos;
        try
        {
            horas= Integer.parseInt((horario.charAt(0)+horario.charAt(1)));
            minutos = Integer.parseInt((horario.charAt(3)+horario.charAt(4)));    
        }
        catch(NumberFormatException ex)
        {
            return false; //si alguno no es un número retorna falso
        }
        if(horas>24||horas<=0||minutos>60||minutos<0)
        {
            return false;
        }
        if(horario.charAt(2)!=':')
        {
            return false;
        }
        return true;
    }
  
}

    


