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
import Validadores.validadores;
import java.util.regex.Pattern;

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
    private validadores validador = new validadores();
    
    public PersistenciaFachada() {
        this.persistenciaPacientes = new PersistenciaPacientes();
        this.persistenciaMedicos = new PersistenciaMedicos();
        this.persistenciaEspecialidades = new PersistenciaEspecialidades();
        this.persistenciaInventarios = new PersistenciaInventarios();
        this.persistenciaConsultas = new PersistenciaConsultas(); 
        
    }

    //pacientes
    @Override
    public void agregarPaciente(String nombre, String edad, String direccion) throws Exception {
//        if(nombre == null || nombre.isBlank() || edad <= 0 || direccion == null || direccion.isBlank()){
//        throw new IllegalArgumentException("datos invalidos");
//        } else 
        if(!validador.validarNombrePaciente(nombre)){
        throw new IllegalArgumentException("nombre invalido");
        }else if(!validador.validarEdad(edad)){
        throw new IllegalArgumentException("edad invalida");
        } else if(!validador.validarDireccion(direccion)){
        throw new IllegalArgumentException("direccion invalida");
        }
        else{
            int id = 1;
            while(persistenciaPacientes.obtenerPacientePorID(id) != null){
            id++;
            }
            Paciente p = new Paciente(id, nombre, Integer.parseInt(edad), direccion);
        persistenciaPacientes.agregarPaciente(p);
        }
    }

    @Override
    public Paciente obtenerPacientePorId(String id) throws Exception {
        if(!validador.validarId(id)){
        throw new IllegalArgumentException("id invalida");
        }
        Paciente p = persistenciaPacientes.obtenerPacientePorID(Integer.parseInt(id));
        if(p == null){
        throw new NoSuchElementException("paciente no encontrado");
        }
        return p;
    }

    @Override
    public List<Paciente> listarPacientes(String nombre, String direccion, String SedadDesde, String SedadHasta) throws Exception {
        List<Paciente> lista = persistenciaPacientes.listarPacientes();
        int edadDesde = Integer.parseInt(SedadDesde);
        int edadHasta = Integer.parseInt(SedadHasta);

        if (lista.isEmpty()) {
            throw new NoSuchElementException("ningún paciente registrado");
        }

    // Filtros activos
        boolean filtroDireccion = direccion != null && !direccion.isBlank();
        boolean filtroNombre = nombre != null && !nombre.isBlank();
        boolean filtroEdadDesde = edadDesde > 0;
        boolean filtroEdadHasta = edadHasta > 0;

    // Si ningún filtro está activo, devolvemos todo
        if (!filtroNombre && !filtroDireccion && !filtroEdadDesde && !filtroEdadHasta) {
            return lista;
        }

    // Ajuste automático si el rango de edades viene invertido
        if (filtroEdadDesde && filtroEdadHasta && edadDesde > edadHasta) {
            int tmp = edadDesde;
            edadDesde = edadHasta;
            edadHasta = tmp;
        }

        Iterator<Paciente> it = lista.iterator();
        Pattern pa = Pattern.compile(nombre, Pattern.CASE_INSENSITIVE);
        while (it.hasNext()) {
            Paciente p = it.next();

            if (filtroDireccion && !p.getDireccion().equalsIgnoreCase(direccion)) {
                it.remove();
                continue;
            }
            
            if(!pa.matcher(p.getNombre()).find()){
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
    public void actualizarPaciente(String id,String nombre, String edad, String direccion) throws Exception {
//        if(nombre == null || nombre.isBlank() || edad <= 0 || direccion == null || direccion.isBlank()){
//        throw new IllegalArgumentException("datos invalidos");
//        }
        if(!validador.validarId(id)){
        throw new IllegalArgumentException("id invalido");
        } else if(!validador.validarNombrePaciente(nombre)){
        throw new IllegalArgumentException("nombre invalido");
        }else if(!validador.validarEdad(edad)){
        throw new IllegalArgumentException("edad invalida");
        } else if(!validador.validarDireccion(direccion)){
        throw new IllegalArgumentException("direccion invalida");
        }
        else{
        
        if(persistenciaPacientes.obtenerPacientePorID(Integer.parseInt(id)) == null){
        throw new NoSuchElementException("paciente no encontrado");
        }
        
        Paciente p = new Paciente(Integer.parseInt(id), nombre, Integer.parseInt(edad), direccion);
        persistenciaPacientes.actualizarPaciente(p);
        }
    }

    @Override
    public void eliminarPaciente(String id) throws Exception {
        if(!validador.validarId(id)){
        throw new IllegalArgumentException("id invalida");
        }
        if(persistenciaPacientes.obtenerPacientePorID(Integer.parseInt(id)) == null){
        throw new NoSuchElementException("paciente no encontrado");
        }
        persistenciaPacientes.eliminarPaciente(Integer.parseInt(id));
    }

    //medicos
    @Override
    public void agregarMedico(String nombre, Especialidad especialidad) throws Exception {
//        if(nombre == null || nombre.isBlank() || especialidad == null){
//        throw new IllegalArgumentException("datos invalidos");
//        }
        if(!validador.validarNombreMedico(nombre)){
        throw new IllegalArgumentException("nombre invalido");
        } else if(especialidad == null || !validador.validarEspecialidad(especialidad.getNombre())){
        throw new IllegalArgumentException("especialidad invalida");
        }
        else{
            int id = 1;
            while(persistenciaMedicos.obtenerMedicoPorId(id) != null){
            id++;
            }
            Medico m = new Medico(id,nombre,especialidad);
            persistenciaMedicos.agregarMedico(m);
        }
    }

    @Override
    public Medico obtenerMedicoPorId(String id) throws Exception {
        if(!validador.validarId(id)){
        throw new IllegalArgumentException("id invalida");
        }
        Medico m = persistenciaMedicos.obtenerMedicoPorId(Integer.parseInt(id));
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
//        if(nombre == null || nombre.isBlank()){
//        throw new IllegalArgumentException("datos invalidos");
//        }
        if(!validador.validarEspecialidad(nombre)){
        throw new IllegalArgumentException("especialidad invalida");
        }
         else{
            
            int id = 1;
            while(persistenciaEspecialidades.obtenerEspecialidadPorId(id) != null){
            id++;
            }
            Especialidad e = new Especialidad(id, nombre);
        persistenciaEspecialidades.agregarEspecialidad(e);
        }
    }

    @Override
    public Especialidad obtenerEspecialidadPorId(String id) throws Exception {
        if(!validador.validarId(id)){
        throw new IllegalArgumentException("id invalida");
        }
        Especialidad e = persistenciaEspecialidades.obtenerEspecialidadPorId(Integer.parseInt(id));
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
    public void agregarEquipoMedico(String nombre, String cantidad) throws Exception {
//        if(nombre == null || nombre.isBlank()){
//        throw new IllegalArgumentException("datos invalidos");
//        }
        if(!validador.validarNombreEquipo(nombre)){
        throw new IllegalArgumentException("equipo invalido");
        } else if(!validador.validrCantidadEquipo(cantidad)){
        throw new IllegalArgumentException("cantidad invalida");
        }
        else{
            int id = 1;
            while(persistenciaInventarios.obtenerInventarioPorId(id) != null){
            id++;
            }
            EquipoMedico e = new EquipoMedico(id, nombre, Integer.parseInt(cantidad));
        persistenciaInventarios.agregarInventario(e);
        }    
    }

    @Override
    public void actualizarCantidadEquipo(String id, String cantidad) throws Exception {
//        if(cantidad < 0 || id < 1 ){
//        throw new IllegalArgumentException("datos invalidos");
//        }
        if(!validador.validarId(id)){
        throw new IllegalArgumentException("id invalido");
        }else if(!validador.validrCantidadEquipo(cantidad)){
        throw new IllegalArgumentException("cantidad invalida");
        }
        EquipoMedico e = persistenciaInventarios.obtenerInventarioPorId(Integer.parseInt(id));
        if(e == null){
        throw new NoSuchElementException("equipo medico no encontrado");
        }
        e.setCantidad(Integer.parseInt(cantidad));
        persistenciaInventarios.actualizarInventario(e);
    }

    @Override
    public List<EquipoMedico> listarEquiposMedicos(String nombre, String cantidad) throws Exception {
        List<EquipoMedico> lista = persistenciaInventarios.listarInventarios();

        if (lista.isEmpty()) {
            throw new NoSuchElementException("ningún equipo médico registrado");
        }

        boolean filtroNombreActivo = nombre != null && !nombre.isBlank();
        boolean filtroCantidadActivo = Integer.parseInt(cantidad) >= 0;

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

        if (filtroCantidadActivo && e.getCantidad() != Integer.parseInt(cantidad)) {
            it.remove();
        }
        }
        
        return lista;
    }

    @Override
    public EquipoMedico obtenerEquipoMedicoPorId(String id) throws Exception {
        if(!validador.validarId(id)){
        throw new IllegalArgumentException("id invalida");
        }
        EquipoMedico e = persistenciaInventarios.obtenerInventarioPorId(Integer.parseInt(id));
        if(e == null){
        throw new NoSuchElementException("equipo medico no encontrado");
        }
        return e;
    }

    //consultas
    @Override
    public void agregarConsulta(Paciente paciente, Medico medico, String fecha) throws Exception {
//        if(paciente == null || medico == null || fecha == null){
//        throw new IllegalArgumentException("datos invalidos");
//        }
        if(paciente == null || !validador.validarNombrePaciente(paciente.getNombre())){
        throw new IllegalArgumentException("paciente invalido");
        }else if(medico == null || !validador.validarNombreMedico(medico.getNombre())){
        throw new IllegalArgumentException("medico invalido");
        }else if(!validador.validarFechaConsulta(fecha)){
        throw new IllegalArgumentException("fecha invalida");
        }
        else{
            int id = 1;
            while(persistenciaConsultas.obtenerConsultaPorId(id) != null){
            id++;
            }
            Fecha fecha1 = new Fecha(fecha);
            Consulta c = new Consulta(id, paciente, medico, fecha1);
        persistenciaConsultas.agregarConsulta(c);
        }
    }

    @Override
    public List<Consulta> listarConsultas(Paciente paciente, Medico medico, String fechaDesde, String fechaHasta) throws Exception {
        List<Consulta> lista = persistenciaConsultas.listarConsultas();
        if(!validador.validarFechaConsulta(fechaDesde)){
        throw new IllegalArgumentException("fecha invalida");
        }
        if(!validador.validarFechaConsulta(fechaHasta)){
        throw new IllegalArgumentException("fecha invalida");
        }
            
        
        Fecha fecha1 = new Fecha(fechaDesde);
        Fecha fecha2 = new Fecha(fechaHasta);

        Periodo periodo = new Periodo(fecha1, fecha2);
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
    public Consulta obtenerConsultaPorId(String id) throws Exception {
        if(!validador.validarId(id)){
        throw new IllegalArgumentException("id invalida");
        }
        Consulta c = persistenciaConsultas.obtenerConsultaPorId(Integer.parseInt(id));
        if(c == null){
        throw new NoSuchElementException("consulta no encontrada");
        }
        return c;
    }

    @Override
    public void eliminarConsulta(String id) throws Exception {
        if(!validador.validarId(id)){
        throw new IllegalArgumentException("id invalida");
        }
        if(persistenciaConsultas.obtenerConsultaPorId(Integer.parseInt(id)) == null){
        throw new NoSuchElementException("consulta no encontrado");
        }
        persistenciaConsultas.eliminarConsulta(Integer.parseInt(id));
    }
    
}
