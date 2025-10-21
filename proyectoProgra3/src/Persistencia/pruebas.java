/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Persistencia;

import Entidades.Paciente;

/**
 *
 * @author le0jx
 */
public class pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Paciente p = new Paciente(1, "jose", 20, "calle inventada 123");
        
        
        PersistenciaPacientes persistenciaP = new PersistenciaPacientes();
        
        persistenciaP.agregarPaciente(p);
    }
    
}
