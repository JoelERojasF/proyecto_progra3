/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import Entidades.Consulta;
import Entidades.EquipoMedico;
import Entidades.Especialidad;
import Entidades.Medico;
import Entidades.Paciente;
import java.util.List;
import objetosServicio.Fecha;
import objetosServicio.Periodo;

/**
 *
 * @author le0jx
 */
public interface IPersistenciaFachada {
    //pacientes
    void agregarPaciente(String nombre, int edad, String direccion) throws Exception;
    Paciente obtenerPacientePorId(int id) throws Exception; 
    List<Paciente> listarPacientes(String direccion, int edadDesde, int edadHasta) throws Exception;
    void actualizarPaciente(int id,String nombre, int edad, String direccion)throws Exception;
    void eliminarPaciente(int id) throws Exception;
    
    
    //medicos
    void agregarMedico(String nombre, Especialidad especialidad) throws Exception;
    Medico obtenerMedicoPorId(int id) throws Exception;
    List<Medico> listarMedicos(Especialidad especialidad) throws Exception;
    
    //especialidades
    void agregarEspecialidad(String nombre) throws Exception;
    Especialidad obtenerEspecialidadPorId(int id) throws Exception;
    List<Especialidad> listarEspecialidades() throws Exception;
    
    //equipo medicos
    void agregarEquipoMedico(String nombre, int cantidad) throws Exception;
    void actualizarCantidadEquipo(int id, int cantidad) throws Exception;
    List<EquipoMedico> listarEquiposMedicos(String nombre, int cantidad) throws Exception;
    EquipoMedico obtenerEquipoMedicoPorId(int id) throws Exception;
    
    //consultas
    void agregarConsulta(Paciente paciente, Medico medico, Fecha fecha) throws Exception;
    List<Consulta> listarConsultas(Paciente paciente, Medico medico, Periodo periodo) throws Exception;
    Consulta obtenerConsultaPorId(int id) throws Exception;
    void eliminarConsulta(int id) throws Exception;
    
}
