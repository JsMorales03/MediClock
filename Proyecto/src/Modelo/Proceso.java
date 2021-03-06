
package Modelo;

import Vista.InOut;
import ds.desktop.notify.DesktopNotify;
import java.text.*;
import java.util.*;
import javax.swing.JOptionPane;
import static Controlador.Main.gestion;

import Controlador.Main;
public class Proceso {
    
    public ArrayList<Personas> personas = new ArrayList();
     Calendar day = Calendar.getInstance();                         //Trae el calendario del computador

  
    public static  boolean estado=false;

    InOut inOut = new InOut();                                      // Solicitar datos
    Verificaciones verificaciones = new Verificaciones();           //Verificar datos
     
    public void insertarMedicamento(Personas obj_persona)     
    {
        int año,mes,dia;
        Medicamentos obj_medicamento = new Medicamentos();
        obj_medicamento.setId_medicamento(obj_persona.getLista_medicamentos().size()+1);        //Cada que entra sube el número
        obj_medicamento.setNombre_medicamento(inOut.solicitarNombre("Digite el nombre del medicamento"));
        while(verificaciones.validarExistenciaMedicamento(obj_persona.getLista_medicamentos(),obj_medicamento.getNombre_medicamento())&&JOptionPane.showConfirmDialog(null,"El medicamento ingresado ya se encuentra registrado \n¿Desea continuar con el proceso?","Seleccione una opción", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
        {
           obj_medicamento.setNombre_medicamento(inOut.solicitarNombre("El medicamento registrado ya se encuentra registrado\nDigite el nombre del medicamento")); 
        }
        while(!verificaciones.validarEspacio(obj_medicamento.getNombre_medicamento()))
        {
           obj_medicamento.setNombre_medicamento(inOut.solicitarNombre("Debe insertar un dato\nDigite el nombre del medicamento"));        
        }
          
        if(!verificaciones.validarExistenciaMedicamento(obj_persona.getLista_medicamentos(),obj_medicamento.getNombre_medicamento()))
        {
            obj_medicamento.setCantidad_medicamento(inOut.solicitarDoubles("Digite el contenido neto del producto"));
            while(obj_medicamento.getCantidad_medicamento()<=0||obj_medicamento.getCantidad_medicamento()>10000)
            {
              obj_medicamento.setCantidad_medicamento(inOut.solicitarDoubles("Digite el contenido neto del producto")); 
            }
            obj_medicamento.setUnidad_medida(inOut.solicitarNombre("Digite la unidad de medida"));

            
            while(!verificaciones.validarEspacio(obj_medicamento.getUnidad_medida()))
            {
               obj_medicamento.setUnidad_medida(inOut.solicitarNombre("Debe insertar un dato\nDigite la unidad de medida"));  
            }
            asignarHorario(obj_medicamento,obj_persona);        // Agrega el horario
            obj_persona.setMedicamento(obj_medicamento);        // Agrega el medicamento
            
            año = inOut.solicitarEntero("Ingrese el año de vencimiento del medicamento: ");
            while(verificaciones.verficarAño(año)==false)
            {
                año = inOut.solicitarEntero("Ingrese un año correcto");
            }
            mes = inOut.solicitarEntero("Ingrese el mes de vencimiento del medicamento: ");
            while(verificaciones.verificarMes(mes)==false)
            {
                mes = inOut.solicitarEntero("Ingrese un mes");
            }
            dia = inOut.solicitarEntero("Ingrese el dia de vencimiento del medicamento: ");
              while(verificaciones.verificarDia(dia)==false)
            {
                dia = inOut.solicitarEntero("Ingrese un dia entre 1 y 31");
            }
            Date fecha = new Date(verificaciones.datoAño(año),(mes-1),dia);
            if(verificaciones.vencimiento(fecha)==false)                        //Si está vencido no lo inserta
            {
                obj_medicamento.setFecha_vencimiento(fecha);   //agrega la fecha de vencimiento
                inOut.mostrarResultado("Medicamento registrado con éxito.");
            }
            
        }
    }
    
     public String mostrarMedicamentos(Personas obj_persona) {

        String acumulador = " ";

        acumulador += ("Medicamentos:\n");

        for (int i = 0; i < obj_persona.getLista_medicamentos().size(); i++) {              //Todos los medicamentos de esa persona
            ArrayList<Horarios> lista = obj_persona.getLista_medicamentos().get(i).getHorarios_medicamento();
            acumulador += (obj_persona.getLista_medicamentos().get(i).getId_medicamento() + ": " + obj_persona.getLista_medicamentos().get(i).getNombre_medicamento() +" Cantidad "+obj_persona.getLista_medicamentos().get(i).getCantidad_medicamento()+ "\n");
            for(int j =0;j<lista.size();j++)                                            //Horarios de ese medicamento
            {
                acumulador+= ("    Día: "+seleccionarDias(lista.get(j).getDia())+ " Hora : "+ lista.get(j).getHora()+"\n");
            }
                  
          }
        return acumulador;

     }

      public void modificarMedicamento(Personas persona) {
        
        String acumulador;
        int opc,numero = 0;

        do {

            opc = inOut.solicitarEntero("MENÚ MODIFICAR MEDICAMENTO. \n"
                    + "1. Cambiar nombre del medicamento \n"
                    + "2. Cambiar cantidad del medicamento \n"
                    + "3. Cambiar Horario\n"
                    + "4. Salir \n");

            switch (opc) {
                case 1:
                    acumulador = mostrarMedicamentos(persona);
                    acumulador += ("\n\nDigite el número del medicamento que desea modificar su nombre: ");
                    numero = inOut.solicitarEntero(acumulador);
                    numero = verificaciones.returnPosicion(numero, persona);
                    cambiarNombreMed(persona, numero);
                    break;
                case 2:
                    acumulador = mostrarMedicamentos(persona);
                    acumulador += ("\n\nDigite el número del medicamento que desea modificar su cantidad: ");
                    numero = inOut.solicitarEntero(acumulador);
                    numero = verificaciones.returnPosicion(numero, persona);
                    cambiarCantidad(persona, numero);
                    break;

                case 3:{
                    int opcion2 = inOut.solicitarEntero(mostrarHorarios(persona.getLista_medicamentos().get(numero))+"\n\nDigite una opción");
                   while(opcion2<=0||opcion2>persona.getLista_medicamentos().get(numero).getHorarios_medicamento().size())
                   {
                     opcion2 = inOut.solicitarEntero(mostrarHorarios(persona.getLista_medicamentos().get(numero))+"\n\nDigite una opción");  
                   }
                   persona.getLista_medicamentos().get(numero).getHorarios_medicamento().remove(opcion2-1);  
                   asignarHorario( persona.getLista_medicamentos().get(numero),persona);
                   break;
                }
                case 4: break;


                default:
                    inOut.mostrarResultado("OPCION NO VALIDA...");
            }
        } while (opc != 4);

    }

      public void cambiarNombreMed(Personas persona, int posicion) {

        String nombre = inOut.solicitarNombre("Digite el nuevo nombre del medicamento.");
   
        while (verificaciones.verificarNombreMedicamento(nombre, persona)||!verificaciones.validarEspacio(nombre)) {
            if(verificaciones.verificarNombreMedicamento(nombre, persona))
            nombre = inOut.solicitarNombre("El nombre del medicamento ya existe. \nDigite el nombre del medicamento.");
            else
            nombre = inOut.solicitarNombre("Debe digitar un dato\nDigite el nombre del medicamento.");
        }
        persona.getLista_medicamentos().get(posicion).setNombre_medicamento(nombre);

    }
      
      
     public void cambiarCantidad(Personas persona, int posicion ){
        
        double cantidad = inOut.solicitarEntero("Digite la nueva cantidad del medicamento.");
        while(cantidad<=0 ||cantidad>=10000 ){
            cantidad = inOut.solicitarEntero("\nLa cantidad no puede ser negativa ni exagerada. \nDigite la cantidad del medicamento."); 
        }
        persona.getLista_medicamentos().get(posicion).setCantidad_medicamento(cantidad);
        
    }
     
       public void eliminarMedicamento(Personas persona){
          
       int numero;   
       String acumulador;
       String menu = "ELIMINAR\n\n1.Eliminar medicamento \n2.Eliminar Horario\n3.Salir\n\nDigite una opción";
       int opcion = inOut.solicitarEntero(menu);
       while(opcion<=0||opcion>3)
       {
          opcion = inOut.solicitarEntero(menu);  
       }
        acumulador = mostrarMedicamentos(persona);
        acumulador += ("\n\nDigite el número del medicamento que desea eliminar: ");
        numero = inOut.solicitarEntero(acumulador);
        numero = verificaciones.returnPosicion(numero, persona);
       switch(opcion)
       {
           case 1:{            
                 persona.getLista_medicamentos().remove(numero);   
                 inOut.mostrarResultado("Medicamento eliminado.");
               break;
           }
           case 2:{
               int opcion2 = inOut.solicitarEntero(mostrarHorarios(persona.getLista_medicamentos().get(numero))+"\n\nDigite una opción");
               while(opcion2<=0||opcion2>persona.getLista_medicamentos().get(numero).getHorarios_medicamento().size())
               {
                 opcion2 = inOut.solicitarEntero(mostrarHorarios(persona.getLista_medicamentos().get(numero))+"\n\nDigite una opción");  
               }
                 persona.getLista_medicamentos().get(numero).getHorarios_medicamento().remove(opcion2-1);  
                 inOut.mostrarResultado("Horario eliminado.");
               break;
           }
           case 3:{
               break;
           }
           default:{
               inOut.mostrarResultado("Opción Incorrecta");
               break;
           }
       }
     

    }
      
    public void crearUsuario(){

        String nombre = inOut.solicitarNombre("Digite su nombre: ");
       while(!verificaciones.validarEspacio(nombre)){
           nombre = inOut.solicitarNombre("Su nombre no puede estar en blanco. \nDigite su nombre: ");
       }
         String usuario = inOut.solicitarNombre("Digite su nombre de usuario: ");
       while(!verificaciones.validarEspacio(usuario)){
           usuario = inOut.solicitarNombre("Su usuario no puede estar en blanco. \nDigite su nombre de usuario: ");
       }  
        while(verificaciones.verificarUsuario(usuario)!= -1){
             usuario = inOut.solicitarNombre("\nEl usuario ya existe. \nDigite su nombre de usuario: ");
        }
        
        String contrasena = inOut.solicitarNombre("Digite su contraseña: ");
         while(!verificaciones.validarEspacio(contrasena)){
             contrasena = inOut.solicitarNombre("\nLa contraseña no puede estar en blanco. \nDigite su contraseña: ");
        }  
        Personas persona = new Personas(nombre,usuario,contrasena);
        
        personas.add(persona);
    }
 
    public void asignarHorario(Medicamentos obj_medicamento,Personas obj_persona)
    {
        int opcion;
           String mensaje= "Días de la semana: \n 1.Domingo\n2.Lunes\n3.Martes\n4.Miercoles\n5.Jueves\n6.Viernes\n7.Sábado\n";
            do 
            {
                Horarios obj_horario = new Horarios();
                opcion= inOut.solicitarEntero(mensaje+"\n\nDigite el día para el horario");
                      
             obj_horario.setDia(opcion);
                while(obj_horario.getDia()<=0||obj_horario.getDia()>7)
                {
                    opcion= inOut.solicitarEntero(mensaje+"\n\nOpción no encontrada\nDigite una opción");
                   obj_horario.setDia(opcion);
                }
                obj_horario.setHora(inOut.solicitarNombre("Digite la hora en formato de 24h CON dos puntos\nEjemplo: 14:30"));
                while(!verificaciones.validarFormato(obj_horario.getHora())||verificaciones.validarHorario(obj_medicamento,obj_horario.getHora()))
                {
                 obj_horario.setHora(inOut.solicitarNombre("FORMATO INCORRECTO\nDigite la hora en formato de 24h CON dos puntos\nEjemplo: 14:30"));
                }
                inOut.mostrarResultado("Hora seleccionada: "+ obj_horario.getHora());

               obj_horario.setDosis(inOut.solicitarDoubles("Cantidad ingeridad el día "+seleccionarDias(obj_horario.getDia())));
               while(obj_horario.getDosis()>obj_medicamento.getCantidad_medicamento()||obj_horario.getDosis()>7)
               {
                  if(obj_horario.getDosis()>obj_medicamento.getCantidad_medicamento())
                  obj_horario.setDosis(inOut.solicitarDoubles("NO puede exceder la cantidad disponible\nCantidad ingeridad el día "+seleccionarDias(obj_horario.getDia())));
                  else
                  obj_horario.setDosis(inOut.solicitarDoubles("NO puede excederse de 7 dosis\nCantidad ingeridad el día "+seleccionarDias(obj_horario.getDia())));
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
                 if(verificaciones.verificarContrasena(contraseña,pos)== false){
                     contraseña=inOut.solicitarNombre("Contraseña incorrecta. \nIngrese la contraseña: ");
                 }else{
                    return personas.get(pos); 
                 }
        }
        return null;
    }
    
    public void menuInicio(){
        int opc;
        String contraseña;
        Personas usuario;
        do{
            String mensaje= "1.Iniciar Sesion \n"
                      +     "2.Registrar Cuenta \n"
                      +     "3.Salir \n";
            opc=inOut.solicitarEntero(mensaje);
              switch(opc)
                {
                    case 1:{
                       usuario = IniciarSesion();
                       
                       if(usuario != null){
                                notificacionVencimiento(usuario);
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
                        Archivos.crearArchivoPersonas(gestion);
                        Archivos.crearMedicamentos(gestion);
                         System.exit(0);
                    }
                    default:{
                        inOut.mostrarResultado("Opción incorrecta");
                        break;
                    }
                }  
        } while(opc!=3); 

    }
    public String mostrarHorarios(Medicamentos obj_medicamento)
    {
        String mensaje="";
        int id=0;
        for(Horarios horario: obj_medicamento.getHorarios_medicamento())
        {
            ++id;
            mensaje+= id+" Día: "+seleccionarDias(horario.getDia())+ " Hora: "+horario.getHora()+"\n";
        }
        return mensaje;
    }
    public void verPersonas(){
        String acumulador ="";
        for(int i=0; i<personas.size(); i++){
            acumulador+= ("Nombre: "+personas.get(i).getNombre()+"          Usuario"+personas.get(i).getUsuario()+"\n");
            ArrayList<Medicamentos> medicamentos = personas.get(i).getLista_medicamentos();
            for(int j=0; j<medicamentos.size(); j++){
                 acumulador+= ("Nombre: "+medicamentos.get(j).getNombre_medicamento()+" \nCantidad "+medicamentos.get(j).getCantidad_medicamento()+" \nUnidad "+medicamentos.get(j).getUnidad_medida()+"\n");
                 ArrayList<Horarios> horarios = medicamentos.get(j).getHorarios_medicamento();
                         for(int k=0; k<horarios.size(); k++){
                               acumulador+=("Dia : "+horarios.get(k).getDia()+" Cantidad: "+horarios.get(k).getHora()+" Dosis: "+horarios.get(k).getDosis()+"\n");
                         }
            }
        }
       inOut.mostrarResultado(acumulador);
    }
   
    public void avisoMed(Personas persona, Medicamentos medicamento){

                if(medicamento.getCantidad_medicamento()<=5){
                    inOut.mostrarResultado("ATENCION: Apenas quedan "
                            +medicamento.getCantidad_medicamento()+" unidades del medicamento "
                            +medicamento.getNombre_medicamento());
                }
                else{
                    if(medicamento.getCantidad_medicamento()==0){
                        inOut.mostrarResultado("No hay del medicamento "
                                +medicamento.getNombre_medicamento());
                        persona.getLista_medicamentos().remove(medicamento.getId_medicamento()-1);
                    }
                }
    }
    
    
    public void descontardosis(Horarios horario ,Medicamentos obj_medicamento,Personas obj_persona){
              
        double opcion= inOut.solicitarDoubles("\n\nDigite la dosis ingerida");
        while(obj_persona.getLista_medicamentos().get(obj_medicamento.getId_medicamento()-1).getCantidad_medicamento()<opcion ||opcion>horario.getDosis() ){
            opcion= inOut.solicitarDoubles("\n\nDigite la dosis ingerida recuerde que este medicamento tiene "+obj_persona.getLista_medicamentos().get(obj_medicamento.getId_medicamento()-1).getCantidad_medicamento()+" Cantidad total del medicamento o  "+horario.getDosis()+"Cantidad de dosis");
        }  
        double nuevo=0;  
        nuevo=obj_persona.getLista_medicamentos().get(obj_medicamento.getId_medicamento()-1).getCantidad_medicamento();         //La que tenía
        obj_persona.getLista_medicamentos().get(obj_medicamento.getId_medicamento()-1).setCantidad_medicamento(nuevo-opcion);   //Nueva cantidad
        avisoMed(obj_persona,obj_medicamento);
            
    }


     public void medicamentosDia(Personas obj_persona){
        
            int dia = day.get(Calendar.DAY_OF_WEEK);                //Trae el número de día de la semana
            String mostrar = "MEDICAMENTOS DE HOY ";
            for(int i=0; i<obj_persona.getLista_medicamentos().size(); i++){
                boolean existe = false;
                ArrayList<Horarios> horarios = obj_persona.getLista_medicamentos().get(i).getHorarios_medicamento();
                for(int j=0; j<horarios.size();j++){
                    if(horarios.get(j).getDia()==dia){
                        if(existe == false){                        //Para que no muestre el nombre del medicamento varias veces
                              mostrar+= ("\n"+"Nombre Medicamento: "+obj_persona.getLista_medicamentos().get(i).getNombre_medicamento());
                              existe = true;
                        }
                    mostrar+= (" \nHora: "+horarios.get(j).getHora()+"      Dosis: "+horarios.get(j).getDosis());
                    }

                    }
              
            inOut.mostrarResultado(mostrar);
        }
    }
     
    public String traerMedicamentosaVencer(Personas obj_persona)
    {
        String mensaje = "";
        for(int i=0; i<obj_persona.getLista_medicamentos().size(); i++)
        {
            if(verificaciones.verificacionFecha(obj_persona.getLista_medicamentos().get(i).getFecha_vencimiento())<=7)
            {
                mensaje += "Medicamento: " + obj_persona.getLista_medicamentos().get(i).getNombre_medicamento() 
                        + " caducara en " + verificaciones.verificacionFecha(obj_persona.getLista_medicamentos().get(i).getFecha_vencimiento())
                        + " días.\n";
            }
        }
        return mensaje;
    }
     
    
    public void iniciarRecordatorio(Personas obj_persona)
    {
        
       if(!obj_persona.getLista_medicamentos().isEmpty())
       {
        DetenerRecordatorio o= new DetenerRecordatorio(); //formulario
         DateFormat dateFormat = new SimpleDateFormat("HH:mm");
         int dia=0;
         boolean estado_mensaje =false;
        
         o.setVisible(true);                            //Mostrar el formulario
         Horarios anterior=null;
         while(!estado)                                     //Mientras sea falso se seguirá en el recordatorio
         {
            dia =  day.get(Calendar.DAY_OF_WEEK);           //Día de la semana
            Date date = new Date();                         //Hora del compu
            if(estado_mensaje&&verificaciones.validarAlarma(dia,date,obj_persona)==null)    // Volver a mostrar el mensaje
            {
                estado_mensaje = false;     //12:00 y 12:02 o mayor
            }
            if(estado_mensaje&&verificaciones.validarAlarma(dia,date,obj_persona)!=null&&anterior!=verificaciones.returnHorario(verificaciones.validarAlarma(dia,date,obj_persona),dia,date))
            {
                estado_mensaje=false;       //Para que pueda mostrar alarmas con minutos de diferencia  12:00 y 12:01
            }
             if(verificaciones.validarAlarma(dia,date,obj_persona)!=null&&!estado_mensaje)
             {
                Medicamentos obmedicamento = verificaciones.validarAlarma(dia,date,obj_persona);
                Horarios obHorario = verificaciones.returnHorario(obmedicamento,dia,date);
                anterior = obHorario;
                DesktopNotify.showDesktopMessage("Notificación", "NO olvide tomar "+obHorario.getDosis()+ " "+obmedicamento.getUnidad_medida() 
                +" de "+obmedicamento.getNombre_medicamento(), DesktopNotify.SUCCESS);  //Tipo de notificación
                descontardosis(obHorario,obmedicamento,obj_persona);
                notificacionVencimiento(obj_persona);
                estado_mensaje=true;             
             }
         }
         o.setVisible(false);
       }
       
    }
    
    public void notificacionVencimiento(Personas p)     // Se muestra el día que se vence el medicamento
    {
        for(int i=0; i<p.getLista_medicamentos().size();i++)
        {
            if(verificaciones.fechaIgual(p.getLista_medicamentos().get(i).getFecha_vencimiento())== true)
            {
                DesktopNotify.showDesktopMessage("¡Urgente!", "El medicamento " + p.getLista_medicamentos().get(i).getNombre_medicamento() 
                        + " está vencido");
                p.getLista_medicamentos().remove(i);
            }
        }
    }
}




