
package Controlador;

import Modelo.*;
import Modelo.Proceso;
import Vista.InOut;

public class Main {

     static Proceso gestion = new Proceso();
     static InOut ioData = new InOut();
    public static void main(String[] args) {
           Personas obj_persona = new Personas();
           obj_persona.setNombre_persona("Paula");  
           
              Medicamentos obj_medicamento = new Medicamentos();
              obj_medicamento.setId_medicamento(1);
              obj_medicamento.setNombre_medicamento("Acetaminofen");
              Horarios obj_horario = new Horarios();
              obj_horario.setDia(5);
              obj_horario.setHora("20:57");
              Horarios obj_horario2 = new Horarios();
              obj_horario2.setDia(5);
              obj_horario2.setHora("20:59");
              Horarios obj_horario3 = new Horarios();
              obj_horario3.setDia(5);
              obj_horario3.setHora("20:42");
              obj_medicamento.setHorario(obj_horario);
              obj_medicamento.setHorario(obj_horario2);
              obj_medicamento.setHorario(obj_horario3);
              obj_persona.setMedicamento(obj_medicamento);
              gestion.iniciarRecordatorio(obj_persona);
           //menuMedicamentos(obj_persona);
    }
    public static void menuMedicamentos(Personas obj_persona)
    {
        String menu= "Que bueno verte nuevamente "+obj_persona.getNombre();
               menu+="\n\n1.Insertar Medicamento"+
                      "\n2.Desplegar medicamentos"+
                      "\n3.Medicamentos del día "+
                      "\n4.Modificar medicamentos"+
                      "\n5.Eliminar medicamentos"; 
        int opcion;
        do
        {
         opcion =  ioData.solicitarEntero(menu+"\n\nDigite una opción");
            switch(opcion)
            {

                case 1:{
                    gestion.insertarMedicamento(obj_persona);
                    break;
                   }
                case 2:{
                    gestion.listarMedicamentos(obj_persona);
                    break;
                }
                case 3:{
                   break;
                }
                case 6:{
                    System.exit(0);
                    break;
                }
                default:{
                    ioData.mostrarResultado("Opción incorrecta");
                    break;
                }

            }
        }
        while(opcion!=6);
    }
}
