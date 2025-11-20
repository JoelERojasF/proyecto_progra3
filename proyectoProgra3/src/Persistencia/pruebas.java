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

        Paciente p1 = new Paciente(4, "manuel", 23, "calle inventada 126");
        

        Especialidad e1 = new Especialidad(5,"Onclologia");
        
        Medico m1 = new Medico(1 , "Dr. Juan Pérez", e1);
        
        PersistenciaFachada pe = new PersistenciaFachada();
        
//        pe.agregarPaciente("María123", "-25", "@#$%"); no funciona
//        pe.agregarPaciente("María López", "25", "calle inventada 123"); si funciona
        
//        pe.agregarMedico("Dr123", e1); no funciona
//        pe.agregarMedico("Dr. Juan Pérez", e5); si funciona
        
//        pe.agregarEquipoMedico("ECG!!!", "-1"); no funciona
//        pe.agregarEquipoMedico("Electrocardiograma", "5"); si funciona
        
//        pe.agregarConsulta(p1, m1, "2025-01-07"); no funciona
//        pe.agregarConsulta(p1, m1, "07/01/2025"); si funciona
        
        
    }
    
}
