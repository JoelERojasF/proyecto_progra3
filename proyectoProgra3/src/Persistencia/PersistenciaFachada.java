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
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
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
            id++;
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

        if (lista.isEmpty()) {
            throw new NoSuchElementException("ningún paciente registrado");
        }

    // Filtros activos
        boolean filtroDireccion = direccion != null && !direccion.isBlank();
        boolean filtroEdadDesde = edadDesde > 0;
        boolean filtroEdadHasta = edadHasta > 0;

    // Si ningún filtro está activo, devolvemos todo
        if (!filtroDireccion && !filtroEdadDesde && !filtroEdadHasta) {
            return lista;
        }

    // Ajuste automático si el rango de edades viene invertido
        if (filtroEdadDesde && filtroEdadHasta && edadDesde > edadHasta) {
            int tmp = edadDesde;
            edadDesde = edadHasta;
            edadHasta = tmp;
        }

        Iterator<Paciente> it = lista.iterator();
        while (it.hasNext()) {
            Paciente p = it.next();

            if (filtroDireccion && !p.getDireccion().equalsIgnoreCase(direccion)) {
                it.remove();
                continue;
            }

            if (filtroEdadDesde && p.getEdad() < edadDesde) {
                it.remove();
                continue;
            }

            if (filtroEdadHasta && p.getEdad() > edadHasta) {
                it.remove();
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
            id++;
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
            id++;
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
            id++;
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

        if (lista.isEmpty()) {
            throw new NoSuchElementException("ningún equipo médico registrado");
        }

        boolean filtroNombreActivo = nombre != null && !nombre.isBlank();
        boolean filtroCantidadActivo = cantidad >= 0;

        if (!filtroNombreActivo && !filtroCantidadActivo) {
            return lista;
        }

        Iterator<EquipoMedico> it = lista.iterator();
        while (it.hasNext()) {
            EquipoMedico e = it.next();

        if (filtroNombreActivo && !e.getNombre().equalsIgnoreCase(nombre)) {
            it.remove();
            continue;
        }

        if (filtroCantidadActivo && e.getCantidad() != cantidad) {
            it.remove();
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
            id++;
            }
            Consulta c = new Consulta(id, paciente, medico, fecha);
        persistenciaConsultas.agregarConsulta(c);
        }
    }

    @Override
    public List<Consulta> listarConsultas(Paciente paciente, Medico medico, Periodo periodo) throws Exception {
        List<Consulta> lista = persistenciaConsultas.listarConsultas();

    if (lista.isEmpty()) {
        throw new NoSuchElementException("ninguna consulta registrada");
    }

    boolean filtrarPaciente = paciente != null;
    boolean filtrarMedico = medico != null;
    boolean filtrarPeriodo = periodo != null;

    if (!filtrarPaciente && !filtrarMedico && !filtrarPeriodo) {
        return lista;
    }

    Iterator<Consulta> it = lista.iterator();
    while (it.hasNext()) {
        Consulta c = it.next();

        if (filtrarPaciente && !paciente.toString().equalsIgnoreCase(c.getPaciente().toString())  ) {
            it.remove();
            continue;
        }

        if (filtrarMedico && !medico.toString().equalsIgnoreCase(c.getMedico().toString()) ) {
            it.remove();
            continue;
        }

        if (filtrarPeriodo) {
            // Asumo que Periodo.tiene/contiene acepta una fecha y devuelve boolean
            if (c.getFecha() == null || !periodo.contiene(c.getFecha())) {
                it.remove();
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
