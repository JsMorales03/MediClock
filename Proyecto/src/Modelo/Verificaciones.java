
package Modelo;

import Controlador.Main;
import Vista.InOut;
import java.text.*;
import java.util.*;
import javax.swing.JOptionPane;


public class Verificaciones {

    
    InOut inOut = new InOut();
    Calendar day = Calendar.getInstance();
    
    public boolean vencimiento(Date fecha)
    {
        Date fecha1 = new Date();               //Fecha actual
        if(fecha.getYear()<=fecha1.getYear() || fecha.getMonth()<=fecha1.getMonth() ||fecha.getDate()<=fecha1.getDate()  )
        {
                    inOut.mostrarResultado("El medicamento esta vencido por lo tanto no se tendra en cuenta");
                    return true;
                
        }
        return false;
    }
    
    
    public boolean verificarDia(int x)
    {
        return x>0 && x<=31;
    }

    public boolean verificarMes(int x)
    {
        return x>0 && x<=12;
    }

    public boolean verficarAño(int x)
    {
        return x>=2020 && x<=2022;
    }
    
    public int datoAño(int x)
    {
        if(x==2020)
        {
            x = 120;
        }
        else if(x==2021)
        {
            x = 121;
        }
        else if(x==2022)
        {
            x = 122;
        }
        return x;
    }
    
    public boolean validarEspacio(String cadena)
    {
        StringTokenizer toke = new StringTokenizer(cadena);        
        return toke.countTokens()>=1;
    }
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
 
     public boolean validarFormato(String m) { 
            try {
                String[] horaMinutos = m.split(":");         //Divide la hora en partes :
                if(horaMinutos.length > 1) {                        //Al menos un ":"
                    int hora = Integer.valueOf(horaMinutos[0]);   //Obtiene la parte de hora
                    int minutos = Integer.valueOf(horaMinutos[1]);          //Obtiene la parte de minutos
                    if((hora<0 || hora>24) ||  (minutos<0 || minutos>60) ){
                        return false;
                    }else{
                        return true;
                    }
                } else{
                    return false;
                }
            } catch (NumberFormatException ex) {
                return false;//Entra aquí si hay error convirtiendo de String a número
            }
        
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

            if (persona.getLista_medicamentos().get(i).getNombre_medicamento().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;

    }
 
     public Medicamentos validarAlarma(int dia_actual,Date hora,Personas obj_persona)
    {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");     // Darle formato a la hora del pc
        
        for(Medicamentos medicamentos:obj_persona.getLista_medicamentos())
        {
            for(Horarios hora_consumo: medicamentos.getHorarios_medicamento())  // Horarios 
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
                     return hora_consumo;       //Retorna el horario
                 }
            }   
       return null;
    }
  
    public boolean fechaIgual(Date fecha)       //La que está registrada
    {
        Date fecha1 = new Date();               //Fecha del pc
        if(fecha.getYear()==fecha1.getYear())
        {
            if(fecha.getMonth()==fecha1.getMonth())
            {
                if(fecha.getDate()==fecha1.getDate())
                {
                    inOut.mostrarResultado("El medicamento esta vencido por lo tanto se eliminara de la lista por su salud");
                    return true;
                }      
            }
        }
        return false;
    }
    
    
    public int verificacionFecha(Date fecha)
    {
        Date fecha1 = new Date();        //Fecha del pc
        int dias=(int) ((fecha.getTime()-fecha1.getTime())/86400000);   //Cantidad de días entre dos fechas
        return dias;
    }
    
    
}
