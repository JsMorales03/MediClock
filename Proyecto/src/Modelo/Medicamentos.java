
package Modelo;

import java.util.ArrayList;


public class Medicamentos {
    
    int id_medicamento;
    String nombre_medicamento;
    Double cantidad_medicamento;               //cantidad total
    Double dosis;                              //dosis (la cantidad que toma)
    ArrayList<Horarios> horarios_medicamento = new ArrayList<>();

    public Medicamentos() {
    }

    
    public Medicamentos(int id_medicamento, String nombre_medicamento, Double cantidad_medicamento, Double dosis) {
        this.id_medicamento = id_medicamento;
        this.nombre_medicamento = nombre_medicamento;
        this.cantidad_medicamento = cantidad_medicamento;
        this.dosis = dosis;
    }

    public int getId_medicamento() {
        return id_medicamento;
    }

    public void setId_medicamento(int id_medicamento) {
        this.id_medicamento = id_medicamento;
    }

    public String getNombre_medicamento() {
        return nombre_medicamento;
    }

    public void setNombre_medicamento(String nombre_medicamento) {
        this.nombre_medicamento = nombre_medicamento;
    }

    public Double getCantidad_medicamento() {
        return cantidad_medicamento;
    }

    public void setCantidad_medicamento(Double cantidad_medicamento) {
        this.cantidad_medicamento = cantidad_medicamento;
    }

    public Double getDosis() {
        return dosis;
    }

    public void setDosis(Double dosis) {
        this.dosis = dosis;
    }

    public ArrayList<Horarios> getHorarios_medicamento() {
        return horarios_medicamento;
    }
   
    public void setHorarios_medicamento(ArrayList<Horarios> horarios_medicamento) {
        this.horarios_medicamento = horarios_medicamento;
    }
    public void setHorario(Horarios horario)
    {
        this.horarios_medicamento.add(horario);
    }
    
}
