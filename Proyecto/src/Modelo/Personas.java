
package Modelo;

import java.util.ArrayList;


public class Personas {
    private String nombre_persona;
    private String usuario;
    private String contrasena;
    ArrayList<Medicamentos> lista_medicamentos = new ArrayList<>();
    public Personas()
    {
        
    }

    public Personas(String nombre_persona, String usuario, String contrasena) {
        this.nombre_persona = nombre_persona;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }
    

    public String getNombre() {
        return nombre_persona;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getNombre_persona() {
        return nombre_persona;
    }

    public void setNombre_persona(String nombre_persona) {
        this.nombre_persona = nombre_persona;
    }

    public ArrayList<Medicamentos> getLista_medicamentos() {
        return lista_medicamentos;
    }

    public void setLista_medicamentos(ArrayList<Medicamentos> lista_medicamentos) {
        this.lista_medicamentos = lista_medicamentos;
    }
    public void setMedicamento (Medicamentos medicamento )
    {
        this.lista_medicamentos.add(medicamento);
    }

    @Override
    public String toString() {
        String mensaje="Nombre : " + nombre_persona + " Usuario: " + usuario+"\nMedicamentos:";
       for(Medicamentos medicamento: lista_medicamentos)
       {
            mensaje+= medicamento.toString();
       }
        return mensaje;
    }
    
}
