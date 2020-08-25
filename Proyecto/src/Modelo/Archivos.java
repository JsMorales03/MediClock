/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.InOut;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Archivos {
    
     private InOut ioData= new InOut();

    
    public Archivos ()//objeto para enviar los datos a esta clase
    { 
    }

    public static void crearArchivoPersonas(Proceso objproceso) {
             
                ArrayList <Personas> personas = objproceso.personas;
               
		FileWriter flwriter = null;
		try {
			//crea el flujo para escribir en el archivo
			flwriter = new FileWriter("personas.txt",false);
			//crea un buffer o flujo intermedio antes de escribir directamente en el archivo
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
                      
                       
                        for(int i=0 ; i<personas.size(); i++){
                            if(i == personas.size()-1){
                                bfwriter.write(personas.get(i).getNombre()+","+personas.get(i).getUsuario()+","+personas.get(i).getContrasena());
                            } else{
                                bfwriter.write(personas.get(i).getNombre()+","+personas.get(i).getUsuario()+","+personas.get(i).getContrasena()+"\n");
                            }
                        }
                      	//cierra el buffer intermedio
			bfwriter.close();
			System.out.println("Archivo personas creado satisfactoriamente..");
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (flwriter != null) {
				try {//cierra el flujo principal
					flwriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
    
    public static void leerArchivoPersonas(Proceso objproceso) {
		// crea el flujo para leer desde el archivo
		File file = new File("personas.txt");		
		Scanner scanner;
             
		try {
			//se pasa el flujo al objeto scanner
                        
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {

				// el objeto scanner lee linea a linea desde el archivo

				String linea = scanner.nextLine();
				Scanner delimitar = new Scanner(linea);

                                delimitar.useDelimiter("\\s*,\\s*");
                                
				Personas e= new Personas();
				         
				e.setNombre_persona(delimitar.next());
                                e.setUsuario(delimitar.next());
				e.setContrasena(delimitar.next());
                                
				objproceso.personas.add(e);
        
			}
			//se cierra el ojeto scanner
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.out);
		}
		     
	}
    
    public static void crearMedicamentos(Proceso objproceso) {
                
                ArrayList <Personas> personas = objproceso.personas;
                ArrayList <Medicamentos> medicamentos= new ArrayList<>();
                ArrayList <Horarios> horarios= new ArrayList<>();
                File f = new File("medicamentos.txt");
		FileWriter flwriter = null;
		try {
			//crea el flujo para escribir en el archivo
			flwriter = new FileWriter(f,false);
			//crea un buffer o flujo intermedio antes de escribir directamente en el archivo
			BufferedWriter bfwriter = new BufferedWriter(flwriter);

                for(int i=0;i<personas.size();i++){                                                                  //Recorre lista de personas
                        medicamentos = personas.get(i).getLista_medicamentos();
                    for(int j=0;j<medicamentos.size();j++){                                                             //Recorre lista de medicamentos
                         horarios = personas.get(i).getLista_medicamentos().get(j).getHorarios_medicamento();
                        for(int k=0; k<horarios.size() ; k++){                                                         //Recorre la lista de horarios
                              bfwriter.write(personas.get(i).getUsuario()+","+medicamentos.get(j).getId_medicamento()+","+medicamentos.get(j).getNombre_medicamento()+","+medicamentos.get(j).getCantidad_medicamento()+","+medicamentos.get(j).getUnidad_medida()+",");
                            if(i==personas.size()-1){
                                    if(j==medicamentos.size()-1){
                                        if(k==horarios.size()-1){
                                            bfwriter.write(horarios.get(k).getDia()+","+horarios.get(k).getHora()+","+horarios.get(k).getDosis());
                                        } else{
                                            bfwriter.write(horarios.get(k).getDia()+","+horarios.get(k).getHora()+","+horarios.get(k).getDosis()+"\n");              
                                          }
                                    }else{
                                         bfwriter.write(horarios.get(k).getDia()+","+horarios.get(k).getHora()+","+horarios.get(k).getDosis()+"\n");  
                                    }
                            } else{
                                  bfwriter.write(horarios.get(k).getDia()+","+horarios.get(k).getHora()+","+horarios.get(k).getDosis()+"\n");  
                            }
                        }
                    }
                }
			//cierra el buffer intermedio
			bfwriter.close();
			System.out.println("Archivo medicamentos creado satisfactoriamente..");
 
		} catch (IOException e) {
			e.printStackTrace(System.out);
		} finally {
			if (flwriter != null) {
				try {//cierra el flujo principal
					flwriter.close();
				} catch (IOException e) {
					e.printStackTrace(System.out);
				}
			}
		}
	}
        
       public static void leerArchivoMedicamentos(Proceso objproceso) {
		// crea el flujo para leer desde el archivo
               
		File file = new File("medicamentos.txt");
		Scanner scanner;
             
		try {
			//se pasa el flujo al objeto scanner
                        boolean si = false ;
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
                                si = false;
				// el objeto scanner lee linea a linea desde el archivo
                                boolean existe = false;
				String linea = scanner.nextLine();
				Scanner delimitar = new Scanner(linea);

                                delimitar.useDelimiter("\\s*,\\s*");
                                
                                int cedula;
				Personas e= new Personas();
                                Medicamentos medicamentos = new Medicamentos();
                                Horarios horarios = new Horarios();
                            
				e.setUsuario(delimitar.next());
                                medicamentos.setId_medicamento(delimitar.nextInt());
                                medicamentos.setNombre_medicamento(delimitar.next());
                               medicamentos.setCantidad_medicamento(Double.parseDouble(delimitar.next()));
                                medicamentos.setUnidad_medida(delimitar.next());
                                horarios.setDia(delimitar.nextInt());
                                horarios.setHora(delimitar.next());
                              
                               
                                horarios.setDosis(Double.parseDouble(delimitar.next()));
                                
                                medicamentos.setHorario(horarios);
                                
                                for(int i=0 ; i<objproceso.personas.size();i++){                               //Recorre la lista de personas
                                    if(objproceso.personas.get(i).getUsuario().equals(e.getUsuario())){ //Si encuentra el usuario       
                                        if(objproceso.personas.get(i).getLista_medicamentos().isEmpty()) {
                                            objproceso.personas.get(i).getLista_medicamentos().add(medicamentos); 
                                        }  else{                                                 
                                            for(int j=0; j<objproceso.personas.get(i).getLista_medicamentos().size(); j++){        //Busca en la lista de medicamentos
                                                if(objproceso.personas.get(i).getLista_medicamentos().get(j).getId_medicamento() == medicamentos.getId_medicamento()){ //Si encuentra el medicamento
                                                     objproceso.personas.get(i).getLista_medicamentos().get(j).setHorario(horarios);                                   //Le añade el horario
                                                     existe = true;
                                                     break;                                                                                                         //sale del bucle
                                                }
                                            }
                                            if(existe == false){                                                                                        //Si no existe
                                                  objproceso.personas.get(i).getLista_medicamentos().add(medicamentos);                            //añade el medicamento junto con su primer horario
                                        }
                                       
                                    }
                                  }
                                }                          
			}
			//se cierra el ojeto scanner
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.out);
		}
		     
	}
	
     

}
