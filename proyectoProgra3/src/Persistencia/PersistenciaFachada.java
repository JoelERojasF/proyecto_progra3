/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.Consulta;
import Entidades.EquipoMedico;
import Entidades.Especialidad;
import Entidades.Medico;
import Entidades.Paciente;
import java.util.List;
import java.util.NoSuchElementException;
import objetosServicio.Fecha;
import objetosServicio.Periodo;

/**
 *
 * @author le0jx
 */
public class PersistenciaFachada implements IPersistenciaFachada{
    
    private PersistenciaPacientes persistenciaPacientes;
    private PersistenciaMedicos persistenciaMedicos;
    private PersistenciaEspecialidades persistenciaEspecialidades;
    private PersistenciaInventarios persistenciaInventarios;
    private PersistenciaConsultas persistenciaConsultas;
    
    public PersistenciaFachada() {
        this.persistenciaPacientes = new PersistenciaPacientes();
        this.persistenciaMedicos = new PersistenciaMedicos();
        this.persistenciaEspecialidades = new PersistenciaEspecialidades();
        this.persistenciaInventarios = new PersistenciaInventarios();
        this.persistenciaConsultas = new PersistenciaConsultas();
    }

    //pacientes
    @Override
    public void agregarPaciente(String nombre, int edad, String direccion) throws Exception {
        if(nombre == null || nombre.isBlank() || edad <= 0 || direccion == null || direccion.isBlank()){
        throw new IllegalArgumentException("datos invalidos");
        }else{
            int id = 1;
            while(persistenciaPacientes.obtenerPacientePorID(id) != null){
            id = id++;
            }
            Paciente p = new Paciente(id, nombre, edad, direccion);
        persistenciaPacientes.agregarPaciente(p);
        }
    }

    @Override
    public Paciente obtenerPacientePorId(int id) throws Exception {
        if(id < 1){
        throw new IllegalArgumentException("id invalida");
        }
        Paciente p = persistenciaPacientes.obtenerPacientePorID(id);
        if(p == null){
        throw new NoSuchElementException("paciente no encontrado");
        }
        return p;
    }

    @Override
    public List<Paciente> listarPacientes(String direccion, int edadDesde, int edadHasta) throws Exception {
        List<Paciente> lista = persistenciaPacientes.listarPacientes();
        if(lista.size() == 0){
        throw new NoSuchElementException("ningun paciente registrado");
        }
        if ((direccion == null || direccion.isBlank()) && edadDesde <= 0 && edadHasta <= 0){
        return lista;
        }
        if(direccion != null || !direccion.isBlank()){
            for(int i=0; i > lista.size(); i++){
                if (!lista.get(i).getDireccion().equalsIgnoreCase(direccion)){
                    lista.remove(i);
                }
            }
        }
        if(edadDesde > 0){
        for(int i=0; i > lista.size(); i++){
                if (lista.get(i).getEdad() < edadDesde){
                    lista.remove(i);
                }
            }
        }
        if(edadHasta > 0){
        for(int i=0; i > lista.size(); i++){
                if (lista.get(i).getEdad() > edadHasta){
                    lista.remove(i);
                }
            }
        }
        return lista;
    }

    @Override
    public void actualizarPaciente(int id,String nombre, int edad, String direccion) throws Exception {
        if(nombre == null || nombre.isBlank() || edad <= 0 || direccion == null || direccion.isBlank()){
        throw new IllegalArgumentException("datos invalidos");
        }
        
        if(persistenciaPacientes.obtenerPacientePorID(id) == null){
        throw new NoSuchElementException("paciente no encontrado");
        }
        
        Paciente p = new Paciente(id, nombre, edad, direccion);
        persistenciaPacientes.actualizarPaciente(p);
        
    }

    @Override
    public void eliminarPaciente(int id) throws Exception {
        if(id < 1){
        throw new IllegalArgumentException("id invalida");
        }
        if(persistenciaPacientes.obtenerPacientePorID(id) == null){
        throw new NoSuchElementException("paciente no encontrado");
        }
        persistenciaPacientes.eliminarPaciente(id);
    }

    //medicos
    @Override
    public void agregarMedico(String nombre, Especialidad especialidad) throws Exception {
        if(nombre == null || nombre.isBlank() || especialidad == null){
        throw new IllegalArgumentException("datos invalidos");
        }else{
            int id = 1;
            while(persistenciaMedicos.obtenerMedicoPorId(id) != null){
            id = id++;
            }
            Medico m = new Medico(id,nombre,especialidad);
            persistenciaMedicos.agregarMedico(m);
        }
    }

    @Override
    public Medico obtenerMedicoPorId(int id) throws Exception {
        if(id < 1){
        throw new IllegalArgumentException("id invalida");
        }
        Medico m = persistenciaMedicos.obtenerMedicoPorId(id);
        if(m == null){
        throw new NoSuchElementException("medico no encontrado");
        }
        return m;    
    }

    @Override
    public List<Medico> listarMedicos(Especialidad especialidad) throws Exception {
        List<Medico> lista = persistenciaMedicos.listarMedicos();
        if(lista.size() == 0){
        throw new NoSuchElementException("ningun medico registrado");
        }
        if (especialidad == null){
        return lista;
        }
        if(especialidad != null){
            for(int i=0; i > lista.size(); i++){
                if (!lista.get(i).getEspecialidad().equals(especialidad)){
                    lista.remove(i);
                }
            }
        }
        return lista;    
    }

    //especialidades
    @Override
    public void agregarEspecialidad(String nombre) throws Exception {
         if(nombre == null || nombre.isBlank()){
        throw new IllegalArgumentException("datos invalidos");
        }else{
            int id = 1;
            while(persistenciaEspecialidades.obtenerEspecialidadPorId(id) != null){
            id = id++;
            }
            Especialidad e = new Especialidad(id, nombre);
        persistenciaEspecialidades.agregarEspecialidad(e);
        }
    }

    @Override
    public Especialidad obtenerEspecialidadPorId(int id) throws Exception {
        if(id < 1){
        throw new IllegalArgumentException("id invalida");
        }
        Especialidad e = persistenciaEspecialidades.obtenerEspecialidadPorId(id);
        if(e == null){
        throw new NoSuchElementException("especialidad no encontrada");
        }
        return e;
    }

    @Override
    public List<Especialidad> listarEspecialidades() throws Exception {
        List<Especialidad> lista = persistenciaEspecialidades.listarEspecialidades();
        if(lista.size() == 0){
        throw new NoSuchElementException("ninguna especialidad registrada");
        }
        return lista;
    }

    //inventario/equipos medicos
    @Override
    public void agregarEquipoMedico(String nombre, int cantidad) throws Exception {
        if(nombre == null || nombre.isBlank()){
        throw new IllegalArgumentException("datos invalidos");
        }else{
            int id = 1;
            while(persistenciaInventarios.obtenerInventarioPorId(id) != null){
            id = id++;
            }
            EquipoMedico e = new EquipoMedico(id, nombre, cantidad);
        persistenciaInventarios.agregarInventario(e);
        }    
    }

    @Override
    public void actualizarCantidadEquipo(int id, int cantidad) throws Exception {
        if(cantidad < 0 || id < 1 ){
        throw new IllegalArgumentException("datos invalidos");
        }
        EquipoMedico e = persistenciaInventarios.obtenerInventarioPorId(id);
        if(e == null){
        throw new NoSuchElementException("equipo medico no encontrado");
        }
        e.setCantidad(cantidad);
        persistenciaInventarios.actualizarInventario(e);
    }

    @Override
    public List<EquipoMedico> listarEquiposMedicos(String nombre, int cantidad) throws Exception {
        List<EquipoMedico> lista = persistenciaInventarios.listarInventarios();
        if(lista.size() == 0){
        throw new NoSuchElementException("ningun equipo medico registrado");
        }
        if ((nombre == null || nombre.isBlank()) && cantidad < 0){
        return lista;
        }
        if(nombre != null || !nombre.isBlank()){
            for(int i=0; i > lista.size(); i++){
                if (!lista.get(i).getNombre().equalsIgnoreCase(nombre)){
                    lista.remove(i);
                }
            }
        }
        if(cantidad >= 0){
        for(int i=0; i > lista.size(); i++){
                if (lista.get(i).getCantidad() != cantidad){
                    lista.remove(i);
                }
            }
        }
        return lista;
    }

    @Override
    public EquipoMedico obtenerEquipoMedicoPorId(int id) throws Exception {
        if(id < 1){
        throw new IllegalArgumentException("id invalida");
        }
        EquipoMedico e = persistenciaInventarios.obtenerInventarioPorId(id);
        if(e == null){
        throw new NoSuchElementException("equipo medico no encontrado");
        }
        return e;
    }

    //consultas
    @Override
    public void agregarConsulta(Paciente paciente, Medico medico, Fecha fecha) throws Exception {
        if(paciente == null || medico == null || fecha == null){
        throw new IllegalArgumentException("datos invalidos");
        }else{
            int id = 1;
            while(persistenciaConsultas.obtenerConsultaPorId(id) != null){
            id = id++;
            }
            Consulta c = new Consulta(id, paciente, medico, fecha);
        persistenciaConsultas.agregarConsulta(c);
        }
    }

    @Override
    public List<Consulta> listarConsultas(Paciente paciente, Medico medico, Periodo periodo) throws Exception {
        List<Consulta> lista = persistenciaConsultas.listarConsultas();
        if(lista.size() == 0){
        throw new NoSuchElementException("ninguna consulta registrada");
        }
        if (paciente == null && medico == null && periodo == null){
        return lista;
        }
        if(paciente != null){
            for(int i=0; i > lista.size(); i++){
                if (!lista.get(i).getPaciente().equals(paciente)){
                    lista.remove(i);
                }
            }
        }
        if(medico != null){
            for(int i=0; i > lista.size(); i++){
                if (!lista.get(i).getMedico().equals(medico)){
                    lista.remove(i);
                }
            }
        }
        if(periodo != null){
            for(int i=0; i > lista.size(); i++){
                if (!periodo.contiene(lista.get(i).getFecha())){
                    lista.remove(i);
                }
            }
        }
        return lista;
    }

    @Override
    public Consulta obtenerConsultaPorId(int id) throws Exception {
        if(id < 1){
        throw new IllegalArgumentException("id invalida");
        }
        Consulta c = persistenciaConsultas.obtenerConsultaPorId(id);
        if(c == null){
        throw new NoSuchElementException("consulta no encontrada");
        }
        return c;
    }

    @Override
    public void eliminarConsulta(int id) throws Exception {
        if(id < 1){
        throw new IllegalArgumentException("id invalida");
        }
        if(persistenciaConsultas.obtenerConsultaPorId(id) == null){
        throw new NoSuchElementException("consulta no encontrado");
        }
        persistenciaConsultas.eliminarConsulta(id);
    }
    
}
