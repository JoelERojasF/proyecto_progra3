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
import objetosServicio.Periodo;

/**
 *
 * @author le0jx
 */
public interface IPersistenciaFachada {
    //pacientes
    void agregarPaciente(Paciente paciente) throws Exception;
    Paciente obtenerPacientePorId(int id) throws Exception; 
    List<Paciente> listarPacientes(String direccion, int edadDesde, int edadHasta) throws Exception;
    void actualizarPaciente(Paciente paciente)throws Exception;
    void eliminarPaciente(int id) throws Exception;
    
    
    //medicos
    void agregarMedico(Medico medico) throws Exception;
    Medico obtenerMedicoPorId(int id) throws Exception;
    List<Medico> listarMedicos(String nombre, Especialidad especialidad) throws Exception;
    
    //especialidades
    void agregarEspecialidad(Especialidad especialidad) throws Exception;
    Especialidad obtenerEspecialidadPorId(int id) throws Exception;
    List<Especialidad> listarEspecialidades() throws Exception;
    
    //equipo medicos
    void agregarEquipoMedico(EquipoMedico equipo) throws Exception;
    void actualizarCantidadEquipo(int id, int cantidad) throws Exception;
    List<EquipoMedico> listarEquiposMedicos(String nombre, int cantidad) throws Exception;
    EquipoMedico obtenerEquipoMedicoPorId() throws Exception;
    
    //consultas
    void agregarConsulta(Consulta consulta) throws Exception;
    List<Consulta> listarConsultas(Paciente paciente, Medico medico, Periodo periodo) throws Exception;
    Consulta obtenerConsultaPorId(int id) throws Exception;
    void eliminarConsulta(int id) throws Exception;
    
}
