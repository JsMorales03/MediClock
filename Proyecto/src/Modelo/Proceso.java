
package Modelo;

import Vista.InOut;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

public class Proceso {
    
    ArrayList<Personas> personas = new ArrayList();
     Calendar day = Calendar.getInstance();                         //Las fechas
    
    InOut inOut = new InOut();                                      // Solicitar datos
    Verificaciones verificaciones = new Verificaciones();           //Verificar datos
    
    public ArrayList<Personas> getPersonas() {                      // Retorna la lista de personas a las verificaciones
        return personas;
    }
    
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

        }
    }
    
     public String mostrarMedicamentos(Personas persona) {

        String acumulador = " ";

        acumulador += ("Medicamentos:");

        for (int i = 0; i < persona.getLista_medicamentos().size(); i++) {

            acumulador += ((i + 1) + ": " + persona.getLista_medicamentos().get(i).getNombre_medicamento() + "\n");

        }
        return acumulador;
    }
     
      public void medicamentosDia(){
        if(personas.isEmpty()==true){
            inOut.mostrarResultado("LISTA VACIA...");
        }
        else{
            int dia = day.get(Calendar.DAY_OF_MONTH);
            String hoy = String.valueOf(dia);
            String mostrar = " ";
            for(int i=0; i<personas.size(); i++){
                if(personas.get(i).getLista_medicamentos().get(i).getHorarios_medicamento().get(i).getDia().equals(hoy)){
                    mostrar+= ("MEDICAMENTOS DE HOY \n"+"Nombre Medicamento: "+personas.get(i).getLista_medicamentos().get(i).getNombre_medicamento()
                        +"  Cantidad: "+personas.get(i).getLista_medicamentos().get(i).getCantidad_medicamento());
                }
            }
            inOut.mostrarResultado(mostrar);
        }
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

        while (verificarNombreMedicamento(nombre, persona)) {
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

                obj_horario.setDia(seleccionarDias(opcion));
                while(obj_horario.getDia()==null)
                {
                    opcion= inOut.solicitarEntero(mensaje+"\n\nOpción no encontrada\nDigite una opción");
                    obj_horario.setDia(seleccionarDias(opcion));
                }
                obj_horario.setHora(inOut.solicitarNombre("Digite la hora en formato de 24h SIN dos puntos\nEjemplo: 14:30 -> 1430"));
                while(!verificaciones.validarFormato(obj_horario.getHora(),obj_horario))
                {
                 obj_horario.setHora(inOut.solicitarNombre("FORMATO INCORRECTO\nDigite la hora en formato de 24h SIN dos puntos\nEjemplo: 14:30 -> 1430"));
                }
                inOut.mostrarResultado("Hora seleccionada: "+ obj_horario.getHora());
               obj_horario.setDosis(inOut.solicitarDoubles("Cantidad ingeridad el día "+obj_horario.getDia()));
               obj_medicamento.setHorario(obj_horario);
            }
            while(JOptionPane.showConfirmDialog(null,"¿Desea registrar un nuevo recordatorio?","Seleccione una opcion",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION);
      obj_persona.setMedicamento(obj_medicamento);
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

    public boolean verificarNombreMedicamento(String nombre, Personas persona) {

        for (int i = 0; i < persona.getLista_medicamentos().size(); i++) {

            if (persona.getLista_medicamentos().get(i).getNombre_medicamento().equals(nombre)) {
                return true;
            }
        }
        return false;

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

    


