
package Modelo;

import java.util.ArrayList;
import java.util.Date;


public class Medicamentos {
    
    private int id_medicamento;
    private String nombre_medicamento;
    private Double cantidad_medicamento;               //cantidad total                            //dosis (la cantidad que toma)
    private String unidad_medida;
    private Date fecha_vencimiento;
    private ArrayList<Horarios> horarios_medicamento = new ArrayList<>();
 
    public Medicamentos() {
    }

    public Medicamentos(int id_medicamento, String nombre_medicamento, Double cantidad_medicamento, String unidad_medida, Date fecha_vencimiento) {
        this.id_medicamento = id_medicamento;
        this.nombre_medicamento = nombre_medicamento;
        this.cantidad_medicamento = cantidad_medicamento;
        this.unidad_medida = unidad_medida;
        this.fecha_vencimiento = fecha_vencimiento;
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

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
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

    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

   
    
    
    
    

    @Override
    public String toString() {
        String mensaje = "No. " + id_medicamento + " Nombre: " + nombre_medicamento + " Cantidad: " + cantidad_medicamento +unidad_medida+"\n";
        for(Horarios hora: horarios_medicamento)
        {
           mensaje+= hora.toString();
        }

        return   mensaje;
    }
    
}
