/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Persistencia;

import Entidades.Consulta;
import Entidades.Especialidad;
import Entidades.Medico;
import Entidades.Paciente;
import objetosServicio.Fecha;

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
        Paciente p1 = new Paciente(1, "jose", 20, "calle inventada 123");
        Paciente p2 = new Paciente(2, "juan", 21, "calle inventada 124");
        Paciente p3 = new Paciente(3, "vicente", 22, "calle inventada 125");
        Paciente p4 = new Paciente(4, "manuel", 23, "calle inventada 126");
        
        Especialidad e1 = new Especialidad(1,"Neurologia");
        Especialidad e2 = new Especialidad(2,"Cardiologia");
        Especialidad e3 = new Especialidad(3,"Oftalmologia");
        Especialidad e4 = new Especialidad(4,"Pediatria");
        Especialidad e5 = new Especialidad(5,"Onclologia");
        
        PersistenciaEspecialidades pe = new PersistenciaEspecialidades();
        pe.agregarEspecialidad(e1);
        pe.agregarEspecialidad(e2);
        pe.agregarEspecialidad(e3);
        pe.agregarEspecialidad(e4);
        pe.agregarEspecialidad(e5);
        
    }
    
}
