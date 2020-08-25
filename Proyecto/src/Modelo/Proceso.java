
package Modelo;

import Controlador.Main;
import Vista.InOut;
import ds.desktop.notify.DesktopNotify;
import java.text.*;
import java.util.*;
import javax.swing.JOptionPane;

import Controlador.Main;
public class Proceso {

    public ArrayList<Personas> personas = new ArrayList();
     Calendar day = Calendar.getInstance();                         //Las fechas

  
    public static  boolean estado=false;

    InOut inOut = new InOut();                                      // Solicitar datos
    Verificaciones verificaciones = new Verificaciones();           //Verificar datos
    
    public void insertarMedicamento(Personas obj_persona)     
    {
        Medicamentos obj_medicamento = new Medicamentos();
        obj_medicamento.setId_medicamento(obj_persona.getLista_medicamentos().size()+1);
        obj_medicamento.setNombre_medicamento(inOut.solicitarNombre("Digite el nombre del medicamento"));
        if(verificaciones.validarExistenciaMedicamento(obj_persona.getLista_medicamentos(),obj_medicamento.getNombre_medicamento()))
        {
            while(verificaciones.validarExistenciaMedicamento(obj_persona.getLista_medicamentos(),obj_medicamento.getNombre_medicamento())&&JOptionPane.showConfirmDialog(null,"El medicamento ingresado ya se encuentra registrado \n¿Desea continuar con el proceso?","Seleccione una opción", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
            {
                obj_medicamento.setNombre_medicamento(inOut.solicitarNombre("El medicamento registrado ya se encuentra registrado\nDigite el nombre del medicamento")); 
            }
            
        }
        if(!verificaciones.validarExistenciaMedicamento(obj_persona.getLista_medicamentos(),obj_medicamento.getNombre_medicamento()))
        {
            obj_medicamento.setCantidad_medicamento(inOut.solicitarDoubles("Digite el contenido neto del producto"));
            while(obj_medicamento.getCantidad_medicamento()<=0)
            {
              obj_medicamento.setCantidad_medicamento(inOut.solicitarDoubles("Digite el contenido neto del producto")); 
            }

            obj_medicamento.setUnidad_medida(inOut.solicitarNombre("Digite la unidad de medida"));
            
            asignarHorario(obj_medicamento,obj_persona);
            obj_persona.setMedicamento(obj_medicamento);
        }
    }
    
     public String mostrarMedicamentos(Personas obj_persona) {

        String acumulador = " ";

        acumulador += ("Medicamentos:\n");

        for (int i = 0; i < obj_persona.getLista_medicamentos().size(); i++) {
            ArrayList<Horarios> lista = obj_persona.getLista_medicamentos().get(i).getHorarios_medicamento();
            acumulador += (obj_persona.getLista_medicamentos().get(i).getId_medicamento() + ": " + obj_persona.getLista_medicamentos().get(i).getNombre_medicamento() + "\n");
            for(int j =0;j<lista.size();j++)
            {
                acumulador+= "    Día: "+seleccionarDias(lista.get(j).getDia())+ " Hora : "+ lista.get(j).getHora()+"\n";
            }
                  
          }
        return acumulador;

     }

      public void modificarMedicamento(Personas persona) {
        
        String acumulador;
        int opc, medicamento,numero;

        do {
            opc = inOut.solicitarEntero("MENU MODIFICAR MEDICAMENTO. \n"
                    + "1. Cambiar id del medicamento \n"
                    + "2. Cambiar nombre del medicamento \n"
                    + "3. Cambiar cantidad del medicamento \n"
                    + "4. Salir \n");
            switch (opc) {
                case 1:
                    acumulador = mostrarMedicamentos(persona);
                    acumulador += ("\n\nDigite el número del medicamento que desea modificar su ID: ");
                    numero = inOut.solicitarEntero(acumulador);
                    numero = verificaciones.returnPosicion(numero, persona);
                    modificarId(persona, numero);
                    break;
                case 2:
                    acumulador = mostrarMedicamentos(persona);
                    acumulador += ("\n\nDigite el número del medicamento que desea modificar su nombre: ");
                    numero = inOut.solicitarEntero(acumulador);
                    numero = verificaciones.returnPosicion(numero, persona);
                    cambiarNombreMed(persona, numero);
                    break;
                case 3:
                    acumulador = mostrarMedicamentos(persona);
                    acumulador += ("\n\nDigite el número del medicamento que desea modificar su cantidad: ");
                    numero = inOut.solicitarEntero(acumulador);
                    numero = verificaciones.returnPosicion(numero, persona);
                    cambiarCantidad(persona, numero);
                    break;
                case 4: break;
                default:
                    inOut.mostrarResultado("OPCION NO VALIDA...");
            }
        } while (opc != 4);

    }
      
      public void modificarId(Personas persona, int posicion) {

        int id = inOut.solicitarEntero("Digite el nuevo ID del medicamento.");
        while (verificaciones.mirarID(id, persona) == true || verificaciones.verificarEnterosPos(id) == false ) {
            if(verificaciones.mirarID(id, persona) == true )
            id = inOut.solicitarEntero("El ID del medicamento ya existe. \nDigite el ID del medicamento.");
            else
            id = inOut.solicitarEntero("El ID del medicamento no puede ser negativo. \nDigite el ID del medicamento.");    
        }

        persona.getLista_medicamentos().get(posicion).setId_medicamento(id);
    }
      
      public void cambiarNombreMed(Personas persona, int posicion) {

        String nombre = inOut.solicitarNombre("Digite el nuevo nombre del medicamento.");

        while (verificaciones.verificarNombreMedicamento(nombre, persona)) {
            nombre = inOut.solicitarNombre("El nombre del medicamento ya existe. \nDigite el nombre del medicamento.");
        }
        persona.getLista_medicamentos().get(posicion).setNombre_medicamento(nombre);

    }
      
      
     public void cambiarCantidad(Personas persona, int posicion ){
        
        double cantidad = inOut.solicitarEntero("Digite la nueva cantidad del medicamento.");
        while(cantidad<=0){
            cantidad = inOut.solicitarEntero("\nLa cantidad no puede ser negativa. \nDigite la cantidad del medicamento."); 
        }
        persona.getLista_medicamentos().get(posicion).setCantidad_medicamento(cantidad);
        
    }
     
       public void eliminarMedicamento(Personas persona){
          
       int numero;   
       String acumulador;
       
        acumulador = mostrarMedicamentos(persona);
        acumulador += ("\n\nDigite el número del medicamento que desea eliminar: ");
        numero = inOut.solicitarEntero(acumulador);
        numero = verificaciones.returnPosicion(numero, persona);
       persona.getLista_medicamentos().remove(numero);   

    }
      
    public void crearUsuario(){

        String nombre = inOut.solicitarNombre("Digite su nombre: ");
       
         String usuario = inOut.solicitarNombre("Digite su nombre de usuario: ");
         
        while(verificaciones.verificarUsuario(usuario)!= -1){
             usuario = inOut.solicitarNombre("\nEl usuario ya existe. \nDigite su nombre de usuario: ");
        }
        
        String contrasena = inOut.solicitarNombre("Digite su contraseña: ");
           
        Personas persona = new Personas(nombre,usuario,contrasena);
        
        personas.add(persona);
    }
 
    public void asignarHorario(Medicamentos obj_medicamento,Personas obj_persona)
    {
        
           String mensaje= "1.Domingo\n2.Lunes\n3.Martes\n4.Miercoles\n5.Jueves\n6.Viernes\n7.Sábado\n";
            do 
            {
                Horarios obj_horario = new Horarios();
                int opcion= inOut.solicitarEntero(mensaje+"\n\nDigite una opción");
                      
             obj_horario.setDia(opcion);
                while(obj_horario.getDia()<=0||obj_horario.getDia()>7)
                {
                    opcion= inOut.solicitarEntero(mensaje+"\n\nOpción no encontrada\nDigite una opción");
                   obj_horario.setDia(opcion);
                }
                obj_horario.setHora(inOut.solicitarNombre("Digite la hora en formato de 24h SIN dos puntos\nEjemplo: 14:30 -> 1430"));
                while(!verificaciones.validarFormato(obj_horario.getHora(),obj_horario)||verificaciones.validarHorario(obj_medicamento,obj_horario.getHora()))
                {
                 obj_horario.setHora(inOut.solicitarNombre("FORMATO INCORRECTO\nDigite la hora en formato de 24h SIN dos puntos\nEjemplo: 14:30 -> 1430"));
                }
                inOut.mostrarResultado("Hora seleccionada: "+ obj_horario.getHora());

                
               obj_horario.setDosis(inOut.solicitarDoubles("Cantidad para ingerir el día "+obj_horario.getDia()));

               obj_horario.setDosis(inOut.solicitarDoubles("Cantidad ingeridad el día "+obj_horario.getDia()));
               while(obj_horario.getDosis()>obj_medicamento.getCantidad_medicamento()||obj_horario.getDosis()>7)
               {
                  if(obj_horario.getDosis()>obj_medicamento.getCantidad_medicamento())
                  obj_horario.setDosis(inOut.solicitarDoubles("NO puede exceder la cantidad disponible\nCantidad ingeridad el día "+obj_horario.getDia()));
                  else
                  obj_horario.setDosis(inOut.solicitarDoubles("NO puede excederse de 7 pastas\nCantidad ingeridad el día "+obj_horario.getDia()));
               }

               obj_medicamento.setHorario(obj_horario);
            }
            while(JOptionPane.showConfirmDialog(null,"¿Desea registrar un nuevo recordatorio?","Seleccione una opcion",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION);
            
    }       
    public String seleccionarDias(int opc)
    {
        switch(opc)
        {
            case 1:{
                return "Domingo";
            }
            case 2:{
                return "Lunes";
            }
            case 3:{
                return "Martes";
            }
            case 4:{
                return "Miercoles";
            }
            case 5:{
                return "Jueves";
            }
            case 6:{
                return "Viernes";
            }
            case 7:{
                return "Sábado";
            }
            default:{
                return null;
            }
        }
    }

  
    public Personas IniciarSesion(){
        String usuario = inOut.solicitarNombre("Digite su nombre de usuario: ");
        if(verificaciones.verificarUsuario(usuario)!= -1){
            int pos= verificaciones.verificarUsuario(usuario);
             String contraseña=inOut.solicitarNombre("Ingrese la contraseña: ");
                 while(verificaciones.verificarContrasena(contraseña,pos)== false){
                     contraseña=inOut.solicitarNombre("Ingrese la contraseña: ");
                 }
            return personas.get(pos);
        }else{
            return null;
        }
    }
    
    public void menuInicio(){
        int opc;
        String contraseña;
        Personas usuario;
        do{
            String mensaje= "1.Iniciar Sesion \n"
                      +     "2.Registrar Cuenta \n";
            opc=inOut.solicitarEntero(mensaje);
              switch(opc)
                {
                    case 1:{
                       usuario = IniciarSesion();
                       
                       if(usuario != null){
                 
                           Main.menuMedicamentos(usuario);

                       }else{
                           inOut.mostrarResultado("USUARIO NO ENCONTRADO");
                       }
                        break;
                    }
                    case 2:{
                        crearUsuario();
                        break;
                    }
                    case 3:{
                        break;
                    }
                    default:{
                        inOut.mostrarResultado("Opción incorrecta");
                        break;
                    }
                }  
        } while(opc!=3); 

    }

          
    public void descontardosis(Medicamentos obj_medicamento,Personas obj_persona){
  
                double opcion= inOut.solicitarDoubles("\n\nDigite la dosis ingerida");
                     while(obj_persona.getLista_medicamentos().get(obj_medicamento.getId_medicamento()).getCantidad_medicamento()<opcion){
                        opcion= inOut.solicitarDoubles("\n\nDigite la dosis ingerida recuerde que este medicamento tiente "+obj_persona.getLista_medicamentos().get(obj_medicamento.getId_medicamento()).getCantidad_medicamento());
                         }  
                     double nuevo=0;  
                     nuevo=obj_persona.getLista_medicamentos().get(obj_medicamento.getId_medicamento()).getCantidad_medicamento();
             obj_persona.getLista_medicamentos().get(obj_medicamento.getId_medicamento()).setCantidad_medicamento(nuevo-opcion);
              
    }


     public void medicamentosDia(Personas obj_persona){
        if(obj_persona.getLista_medicamentos().isEmpty()==true){
            inOut.mostrarResultado("LISTA VACIA...");
        }
        else{
            int dia = day.get(Calendar.DAY_OF_WEEK);;
            String mostrar = " ";
            for(int i=0; i<obj_persona.getLista_medicamentos().size(); i++){
                if(obj_persona.getLista_medicamentos().get(i).getHorarios_medicamento().get(i).getDia()==dia){
                    mostrar+= ("MEDICAMENTOS DE HOY \n"+"Nombre Medicamento: "+personas.get(i).getLista_medicamentos().get(i).getNombre_medicamento()
                        +"  Cantidad: "+personas.get(i).getLista_medicamentos().get(i).getCantidad_medicamento());
                }
            }
            inOut.mostrarResultado(mostrar);
        }
    }
   
    public void iniciarRecordatorio(Personas obj_persona)
    {
        
       if(!obj_persona.getLista_medicamentos().isEmpty())
       {
        DetenerRecordatorio o= new DetenerRecordatorio(); //formulario
         DateFormat dateFormat = new SimpleDateFormat("HH:mm");
         int dia=0;
         boolean estado_mensaje =false;
        
         o.setVisible(true);
         Horarios anterior=null;
         while(!estado)
         {
            dia =  day.get(Calendar.DAY_OF_WEEK);
            Date date = new Date();  
            if(estado_mensaje&&verificaciones.validarAlarma(dia,date,obj_persona)==null)
            {
                estado_mensaje = false;
            }
            if(estado_mensaje&&verificaciones.validarAlarma(dia,date,obj_persona)!=null&&anterior!=verificaciones.returnHorario(verificaciones.validarAlarma(dia,date,obj_persona),dia,date))
            {
                estado_mensaje=false;
            }
             if(verificaciones.validarAlarma(dia,date,obj_persona)!=null&&!estado_mensaje)
             {
                Medicamentos obmedicamento = verificaciones.validarAlarma(dia,date,obj_persona);
                Horarios obHorario = verificaciones.returnHorario(obmedicamento,dia,date);
                anterior = obHorario;
                System.out.println("Alarmaaa!"+ "Hora : "+ dateFormat.format(date));
                DesktopNotify.showDesktopMessage("Notificación", "NO olvide tomar "+obHorario.getDosis()+ " "+obmedicamento.getUnidad_medida() 
                +" de "+obmedicamento.getNombre_medicamento(), DesktopNotify.SUCCESS);
                descontardosis(obmedicamento,obj_persona);
                estado_mensaje=true;             
             }
         }
         o.setVisible(false);
         Main.menuMedicamentos(obj_persona);
       }
    }


}

    


