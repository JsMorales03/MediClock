
package Modelo;


public class Horarios {
    String dia;
    String hora;

    public Horarios() {
    }

    public Horarios(String dia, String hora) {
        this.dia = dia;
        this.hora = hora;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return " Día " + dia + " Hora " + hora + '\n';
    }
    
}
