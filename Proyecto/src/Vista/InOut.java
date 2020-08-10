/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import javax.swing.JOptionPane;

/**
 *
 * @author joanm
 */
public class InOut {
    public int solicitarEntero(String m) {
        do {
            try {
                return Integer.parseInt(JOptionPane.showInputDialog(m));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,"Debe ser un dato  numérico"); //Entra aquí si hay error convirtiendo de String a entero
            }
        } while(true);
    }

    //Este metodo captura datos de tipo Double
    public double solicitarDoubles(String m) {
        do {
            try {
                return Double.parseDouble(JOptionPane.showInputDialog(m));
            } catch (NumberFormatException ex) {
                 JOptionPane.showMessageDialog(null,"Debe ser un dato  numérico");//Entra aquí si hay error convirtiendo de String a double
            }
        } while(true);
    }

    public String solicitarNombre(String m) {
        return JOptionPane.showInputDialog(m);
    }

    //Este metodo captura datos de tipo float
    public float solicitardecimal(String m) {
        do {
            try {
                return Float.parseFloat(JOptionPane.showInputDialog(m));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,"Debe ser un dato  numérico"); //Entra aquí si hay error convirtiendo de String a float
            }
        } while(true);
    }
    //Este metodo muestra un resultado en pantalla

    public void mostrarResultado(String m) {
        JOptionPane.showMessageDialog(null, m);
    } 
}
