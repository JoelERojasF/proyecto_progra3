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
    void agregarPaciente(String nombre, String edad, String direccion) throws Exception;
    Paciente obtenerPacientePorId(String id) throws Exception; 
    List<Paciente> listarPacientes(String nombre, String direccion, String edadDesde, String edadHasta) throws Exception;
    void actualizarPaciente(String id,String nombre, String edad, String direccion)throws Exception;
    void eliminarPaciente(String id) throws Exception;
    
    
    //medicos
    void agregarMedico(String nombre, Especialidad especialidad) throws Exception;
    Medico obtenerMedicoPorId(String id) throws Exception;
    List<Medico> listarMedicos(Especialidad especialidad) throws Exception;
    
    //especialidades
    void agregarEspecialidad(String nombre) throws Exception;
    Especialidad obtenerEspecialidadPorId(String id) throws Exception;
    List<Especialidad> listarEspecialidades() throws Exception;
    
    //equipo medicos
    void agregarEquipoMedico(String nombre, String cantidad) throws Exception;
    void actualizarCantidadEquipo(String id, String cantidad) throws Exception;
    List<EquipoMedico> listarEquiposMedicos(String nombre, String cantidad) throws Exception;
    EquipoMedico obtenerEquipoMedicoPorId(String id) throws Exception;
    
    //consultas
    void agregarConsulta(Paciente paciente, Medico medico, String fecha) throws Exception;
    List<Consulta> listarConsultas(Paciente paciente, Medico medico, String fechaDesde, String fechaHasta) throws Exception;
    Consulta obtenerConsultaPorId(String id) throws Exception;
    void eliminarConsulta(String id) throws Exception;
    
}
