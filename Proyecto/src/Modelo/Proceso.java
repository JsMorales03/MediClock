package Modelo;

import Vista.InOut;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class Proceso {

    ArrayList<Personas> personas = new ArrayList();

    InOut inOut = new InOut();
    Calendar day = Calendar.getInstance();

    public void crearUsuario() {

        String nombre = inOut.solicitarNombre("Digite su nombre: ");

        String usuario = inOut.solicitarNombre("Digite su nombre de usuario: ");

        while (verificarUsuario(usuario) != -1) {                                          //Si es diferente de -1 significa que existe el usuario
            usuario = inOut.solicitarNombre("\nEl usuario ya existe. \nDigite su nombre de usuario: ");
        }

        String contrasena = inOut.solicitarNombre("Digite su contraseña: ");

        Personas persona = new Personas(nombre, usuario, contrasena);

        personas.add(persona);
    }

    public void ingresar() {

        String usuario = inOut.solicitarNombre("Digite su usuario");

        int posicion = verificarUsuario(usuario);

        if (posicion != -1) {

            String contrasena = inOut.solicitarNombre("Digite su contraseña: ");

            if (verificarContrasena(contrasena, posicion)) {

            }

        } else {
            inOut.mostrarResultado("El usuario es incorrecto.");
        }

    }

    public int verificarUsuario(String usuario) {

        for (int i = 0; i < personas.size(); i++) {

            if (personas.get(i).getUsuario().equals(usuario)) {
                return i;
            }
        }
        return -1;
    }

    public boolean verificarContrasena(String contrasena, int posicion) {

        if (personas.get(posicion).getContrasena().equals(contrasena)) {
            return true;
        } else {
            return false;
        }

    }

    public void modificarMedicamento(Personas persona) {

        int opc, medicamento;

        do {
            opc = inOut.solicitarEntero("MENU MODIFICAR MEDICAMENTO. \n"
                    + "1. Cambiar id del medicamento \n"
                    + "2. Cambiar nombre del medicamento \n"
                    + "3. Cambiar cantidad del medicamento \n"
                    + "4. Salir \n");
            switch (opc) {
                case 1:
                    medicamento = mostrarMedicamentos(persona);
                    modificarId(persona, medicamento);
                    break;
                case 2:
                    medicamento = mostrarMedicamentos(persona);
                    cambiarNombreMed(persona, medicamento);
                    break;
                case 3:
                    medicamento = mostrarMedicamentos(persona);
                    
                    break;
                case 4: break;
                default:
                    inOut.mostrarResultado("OPCION NO VALIDA...");
            }
        } while (opc != 4);

    }

    public int returnPosicion(int medicamento, Personas persona) {
        while (true) {
            if (medicamento > 0 && medicamento <= persona.lista_medicamentos.size()) {                                            //Si digita un número entre 0 y la cantidad de categorias, entra
                return medicamento - 1;
            } else {
                medicamento = inOut.solicitarEntero("\nDebe digitar un número dentro del rango [1, " + persona.lista_medicamentos.size() + "] \nDigite el número del medicamento que desea modificar: ");
            }
        }
    }

    public int mostrarMedicamentos(Personas persona) {

        String acumulador = " ";

        acumulador += ("Medicamentos:");

        for (int i = 0; i < persona.lista_medicamentos.size(); i++) {

            acumulador += ((i + 1) + ": " + persona.lista_medicamentos.get(i).nombre_medicamento + "\n");

        }

        acumulador += ("\n\nDigite el número del medicamento que desea modificar");

        int numero;

        numero = inOut.solicitarEntero(acumulador);
        numero = returnPosicion(numero, persona);

        return numero;

    }

    public void modificarId(Personas persona, int posicion) {

        int id = inOut.solicitarEntero("Digite el nuevo ID del medicamento.");
        while (mirarID(id, persona) == true || verificarEnterosPos(id) == false ) {
            if(mirarID(id, persona) == true )
            id = inOut.solicitarEntero("El ID del medicamento ya existe. \nDigite el ID del medicamento.");
            else
            id = inOut.solicitarEntero("El ID del medicamento no puede ser negativo. \nDigite el ID del medicamento.");    
        }

        persona.lista_medicamentos.get(posicion).setId_medicamento(id);
    }

    public void cambiarNombreMed(Personas persona, int posicion) {

        String nombre = inOut.solicitarNombre("Digite el nuevo nombre del medicamento.");

        while (verificarNombreMedicamento(nombre, persona)) {
            nombre = inOut.solicitarNombre("El nombre del medicamento ya existe. \nDigite el nombre del medicamento.");
        }
        persona.lista_medicamentos.get(posicion).setNombre_medicamento(nombre);

    }
    
    public void cambiarCantidad(int posicion, Personas persona){
        
        double cantidad = inOut.solicitarEntero("Digite la nueva cantidad del medicamento.");
        while(cantidad<=0){
            cantidad = inOut.solicitarEntero("\nLa cantidad no puede ser negativa. \nDigite la cantidad del medicamento."); 
        }
        persona.lista_medicamentos.get(posicion).setCantidad_medicamento(cantidad);
        
    }

    public boolean verificarNombreMedicamento(String nombre, Personas persona) {

        for (int i = 0; i < persona.lista_medicamentos.size(); i++) {

            if (persona.lista_medicamentos.get(i).nombre_medicamento.equals(nombre)) {
                return true;
            }
        }
        return false;

    }

    public boolean mirarID(int id, Personas persona) {

        for (int i = 0; i < persona.lista_medicamentos.size(); i++) {

            if (persona.lista_medicamentos.get(i).id_medicamento == id) {
                return true;
            }
        }
        return false;
    }
    
    public boolean verificarEnterosPos(int numero){
        if(numero>0)
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
    public void eliminarMedicamento(Personas persona){
        
       int medicamento = mostrarMedicamentos(persona);
       persona.lista_medicamentos.remove(medicamento);
        

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
                if(personas.get(i).lista_medicamentos.get(i).horarios_medicamento.get(i).dia.equals(hoy)){
                    mostrar+= ("MEDICAMENTOS DE HOY \n"+"Nombre Medicamento: "+personas.get(i).lista_medicamentos.get(i).nombre_medicamento
                        +"  Cantidad: "+personas.get(i).lista_medicamentos.get(i).cantidad_medicamento);
                }
            }
        }
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
   /* public boolean validarFormato(String horario)
    {
        char[] hora = new char[5];
        hora = horario.toCharArray(); //hora a validar
        if(hora[0])
        char numeros[] = new char[2];
        
        
        return true;
    }*/
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
}
