
package Modelo;

import Controlador.Main;

import Vista.InOut;
import java.text.*;
import java.util.*;


public class Verificaciones {
 
    InOut inOut = new InOut();
    Proceso proceso = Main.gestion;

    Calendar day = Calendar.getInstance();
     public int verificarUsuario(String usuario) {

         
           for (int i = 0; i <Main.gestion.personas.size(); i++) {
                 if (Main.gestion.personas.get(i).getUsuario().equals(usuario)) {

                return i;
            }
        }
        return -1;
     }
       
    
    public boolean verificarContrasena(String contrasena, int posicion) {


        if (Main.gestion.personas.get(posicion).getContrasena().equals(contrasena)) {
           return true;
        } else {
            return false;
        }

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
    public boolean validarFormato(String horario,Horarios obj_horario) 
    {
        DateFormat formatoOrigen = new SimpleDateFormat("HHmm");
        DateFormat formatoDestino = new SimpleDateFormat("HH:mm");
        Date fecha;
        try {
            fecha = formatoOrigen.parse(horario);
        } catch (ParseException ex) {
            return false;
        }
        obj_horario.setHora(formatoDestino.format(fecha));
        return true;
    }
    public boolean validarHorario(Medicamentos obj_medicamento, String hora)
    {
        for(Horarios obmedicamento: obj_medicamento.getHorarios_medicamento())
        {
            if(obmedicamento.getHora().equals(hora))
            {
                return true;
            }
        }
        return false;
    }
    public boolean mirarID(int id, Personas persona) {

        for (int i = 0; i < persona.getLista_medicamentos().size(); i++) {

            if (persona.getLista_medicamentos().get(i).getId_medicamento() == id) {
                return true;
            }
        }
        return false;
    }
    
    public boolean verificarEnterosPos(int numero){
        return numero>0;
    }
    
    
    public int returnPosicion(int medicamento, Personas persona) {
        while (true) {
            if (medicamento > 0 && medicamento <= persona.getLista_medicamentos().size()) {                                            //Si digita un número entre 0 y la cantidad de categorias, entra
                return medicamento - 1;
            } else {
                medicamento = inOut.solicitarEntero("\nDebe digitar un número dentro del rango [1, " + persona.getLista_medicamentos().size() + "] \nDigite el número del medicamento que desea modificar: ");
            }
        }
    }
    
    public boolean verificarNombreMedicamento(String nombre, Personas persona) {

        for (int i = 0; i < persona.getLista_medicamentos().size(); i++) {

            if (persona.getLista_medicamentos().get(i).getNombre_medicamento().equals(nombre)) {
                return true;
            }
        }
        return false;

    }
 
     public Medicamentos validarAlarma(int dia_actual,Date hora,Personas obj_persona)
    {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        
        for(Medicamentos medicamentos:obj_persona.getLista_medicamentos())
        {
            for(Horarios hora_consumo: medicamentos.getHorarios_medicamento())
            {
                 if(hora_consumo.getDia()==dia_actual&&dateFormat.format(hora).equals(hora_consumo.getHora()))
                 {
                     return medicamentos;
                 }
            }
        }
       
        return null;
    }
    public Horarios returnHorario(Medicamentos medicamentos,int dia_actual,Date hora)
    {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
       for(Horarios hora_consumo: medicamentos.getHorarios_medicamento())
            {
                 if(hora_consumo.getDia()==dia_actual&&dateFormat.format(hora).equals(hora_consumo.getHora()))
                 {
                     return hora_consumo;
                 }
            }   
       return null;
    }
  

    
    
    
}
