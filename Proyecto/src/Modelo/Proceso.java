package Modelo;

import Vista.InOut;
import java.util.ArrayList;

public class Proceso {

    ArrayList<Personas> personas = new ArrayList();

    InOut inOut = new InOut();

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
    
    public void eliminarMedicamento(Personas persona){
        
       int medicamento = mostrarMedicamentos(persona);
       persona.lista_medicamentos.remove(medicamento);
        
    }
}
