
package Controlador;

import Modelo.Personas;
import Modelo.Proceso;
import Vista.InOut;

public class Main {

     static Proceso gestion = new Proceso();
     static InOut ioData = new InOut();
    public static void main(String[] args) {
           Personas obj_persona = new Personas();
           obj_persona.setNombre_persona("Paula");      
           menuMedicamentos(obj_persona);
    }
    public static void menuMedicamentos(Personas obj_persona)
    {
        String menu= "Que bueno verte nuevamente "+obj_persona.getNombre();
               menu+="\n\n1.Insertar Medicamento"+
                      "\n2.Desplegar medicamentos"+
                      "\n3.Medicamentos del día "+
                      "\n4.Modificar medicamentos"+
                      "\n5.Eliminar medicamentos"+
                      "\n6. Salir."; 
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
                    gestion.mostrarMedicamentos(obj_persona);
                    break;
                }
                case 3:{
                       gestion.medicamentosDia();
                   break;
                }
                case 4:{
                    gestion.modificarMedicamento(obj_persona);
                   break; 
                }
                case 5: {
                    gestion.eliminarMedicamento(obj_persona);
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
