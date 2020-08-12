
package Modelo;


public class Horarios {
    private String dia;
    private String hora;
    private Double dosis;
    public Horarios() {
    }

    public Horarios(String dia, String hora,Double dosis) {
        this.dia = dia;
        this.hora = hora;
        this.dosis = dosis;
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

    public Double getDosis() {
        return dosis;
    }

    public void setDosis(Double dosis) {
        this.dosis = dosis;
    }
    

    @Override
    public String toString() {
        return " Día " + dia + " Hora " + hora + " Dosis "+dosis+'\n';
    }
    
}
