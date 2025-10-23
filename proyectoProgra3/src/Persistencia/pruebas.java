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
        
        Especialidad e1 = new Especialidad(1,"neuro cirujano");
        
        Medico m1 = new Medico(1, "house", e1);
        Fecha f1 = new Fecha();
        
        Consulta c1 = new Consulta(1, p1, m1, f1);
        Consulta c2 = new Consulta(2, p2, m1, f1);
        Consulta c3 = new Consulta(3, p3, m1, f1);
        
        
        PersistenciaConsultas pc = new PersistenciaConsultas();
        
        PersistenciaPacientes pp = new PersistenciaPacientes();
        
        System.out.println(pp.listarPacientes());
        
    }
    
}
