/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.Medico;
import Interfaces.Imayusculas;
import Interfaces.Iordenamiento;
import Interfaces.Ireporte;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author le0jx
 */
public class PersistenciaMedicos implements Ireporte<Medico>, Imayusculas<Medico>, Iordenamiento<Medico> {
     private static final String ARCHIVO_MEDICOS = "medicos.txt";

    public PersistenciaMedicos() {
    }
    
    public void agregarMedico(Medico medico) throws Exception{
        try {
            Files.write(Paths.get(ARCHIVO_MEDICOS), (medico.toString() + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    public List<Medico> listarMedicos() throws Exception{
    List<Medico> medicos = new ArrayList<>();
    try {
            List<String> lineas = Files.readAllLines(Paths.get(ARCHIVO_MEDICOS));
            for (String linea : lineas) {
                Medico medico = new Medico();
                medico = medico.fromString(linea);
                if (medico.getId() != 0) {
                    medicos.add(medico);
                }
            }
        } catch (IOException e) {
            System.err.println("error al leer el archivo: " + e.getMessage());
        }
    return medicos;
    }
    
    public void guardarListaMedicos(List<Medico> medicos) throws Exception{
        try {
            List<String> lineas = medicos.stream().map(Medico::toString).collect(Collectors.toList());
            Files.write(Paths.get(ARCHIVO_MEDICOS), lineas);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
    
    public Medico obtenerMedicoPorId(int id) throws Exception{
        return listarMedicos().stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }
    
    public void actualizarMedico(Medico medicodActualizado) throws Exception{
        List<Medico> medicos = listarMedicos();
        medicos = medicos.stream().map(m -> m.getId() == medicodActualizado.getId() ? medicodActualizado : m).collect(Collectors.toList());
        guardarListaMedicos(medicos);
    }
     
    public void eliminarMedico(int id) throws Exception{
        List<Medico> especialidades = listarMedicos();
        especialidades.removeIf(e -> e.getId() == id);
        guardarListaMedicos(especialidades);
    }

    @Override
    public String generarReporte(List<Medico> lista) {
        return lista.stream().map(m -> "Nombre: {" + m.getNombre() + "} Especialidad: {" + m.getEspecialidad().getNombre()+ "}" ).collect(Collectors.joining("\n"));
    }

    @Override
    public List cambiarMayusculas(List<Medico> lista) {
        return lista.stream()
                .map(m -> new Medico(m.getId(), m.getNombre().toUpperCase(), m.getEspecialidad()))
                .toList();
    }

    @Override
    public List ordenarDatos(List<Medico> lista) {
        return lista.stream().sorted(Comparator.comparing(m -> m.getEspecialidad().getNombre())).toList();
    }
}
