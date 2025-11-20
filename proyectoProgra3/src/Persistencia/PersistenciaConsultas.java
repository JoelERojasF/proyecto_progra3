/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.Consulta;
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
public class PersistenciaConsultas {
    private static final String ARCHIVO_CONSULTAS = "consultas.txt";
    
    public PersistenciaConsultas() {
    }
    
    public void agregarConsulta(Consulta consulta) throws Exception{
        try {
            Files.write(Paths.get(ARCHIVO_CONSULTAS), (consulta.toString() + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    public List<Consulta> listarConsultas() throws Exception{
        List<Consulta> consultas = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(ARCHIVO_CONSULTAS));
            for (String linea : lineas) {
                Consulta consulta = new Consulta();
                consulta = consulta.fromString(linea);
                if(consulta == null){}
                else if (consulta.getId() != 0) {
                    consultas.add(consulta);
                }
            }
        } catch (IOException e) {
            System.err.println("error al leer el archivo: " + e.getMessage());
        }
        return consultas;
    }
    
    public void guardarListaConsultas(List<Consulta> consultas) throws Exception{
        try {
            List<String> lineas = consultas.stream().map(Consulta::toString).collect(Collectors.toList());
            Files.write(Paths.get(ARCHIVO_CONSULTAS), lineas);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
    
    public Consulta obtenerConsultaPorId(int id) throws Exception{
        return listarConsultas().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }
    
    public void actualizarConsulta(Consulta consultaActualizada) throws Exception{
        List<Consulta> consultas = listarConsultas();
        consultas = consultas.stream().map(c -> c.getId() == consultaActualizada.getId() ? consultaActualizada : c).collect(Collectors.toList());
        guardarListaConsultas(consultas);
    }
    
    public void eliminarConsulta(int id) throws Exception{
        List<Consulta> consultas = listarConsultas();
        consultas.removeIf(c -> c.getId() == id);
        guardarListaConsultas(consultas);
    }
}
