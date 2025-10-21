/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.Especialidad;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author le0jx
 */
public class PersistenciaEspecialidades {
private static final String ARCHIVO_ESPECIALIDADES = "especialidades.txt";
    
    
    public PersistenciaEspecialidades() {
    }
    
    public void agregarEspecialidad(Especialidad especialidad) throws Exception{
    try {
            Files.write(Paths.get(ARCHIVO_ESPECIALIDADES), (especialidad.toString() + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    public List<Especialidad> listarEspecialidades() throws Exception{
    List<Especialidad> especialidades = new ArrayList<>();
    try {
            List<String> lineas = Files.readAllLines(Paths.get(ARCHIVO_ESPECIALIDADES));
            for (String linea : lineas) {
                Especialidad especialidad = new Especialidad();
                especialidad = especialidad.fromString(linea);
                if (especialidad.getId() != 0) {
                    especialidades.add(especialidad);
                }
            }
        } catch (IOException e) {
            System.err.println("error al leer el archivo: " + e.getMessage());
        }
    return especialidades;
    }
    
    public void guardarListaEspecialidades(List<Especialidad> especialidades) throws Exception{
        try {
            List<String> lineas = especialidades.stream().map(Especialidad::toString).collect(Collectors.toList());
            Files.write(Paths.get(ARCHIVO_ESPECIALIDADES), lineas);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
    
    public Especialidad obtenerEspecialidadPorId(int id) throws Exception{
        return listarEspecialidades().stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }
    
    public void actualizarEspecialidad(Especialidad especialidadActualizada) throws Exception{
        List<Especialidad> especialidades = listarEspecialidades();
        especialidades = especialidades.stream().map(p -> p.getId() == especialidadActualizada.getId() ? especialidadActualizada : p).collect(Collectors.toList());
        guardarListaEspecialidades(especialidades);
    }
    
    public void eliminarEspecialidad(int id) throws Exception{
        List<Especialidad> especialidades = listarEspecialidades();
        especialidades.removeIf(e -> e.getId() == id);
        guardarListaEspecialidades(especialidades);
    }
}
