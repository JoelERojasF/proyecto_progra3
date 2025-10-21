/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.Paciente;
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
public class PersistenciaPacientes {
    private static final String ARCHIVO_PACIENTES = "pacientes.txt";
    
    public PersistenciaPacientes() {
    }
    
    public void agregarPaciente(Paciente paciente) throws Exception{
        try {
            Files.write(Paths.get(ARCHIVO_PACIENTES), (paciente.toString() + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    public List<Paciente> listarPacientes() throws Exception {
       List<Paciente> pacientes = new ArrayList<>();
       try {
            List<String> lineas = Files.readAllLines(Paths.get(ARCHIVO_PACIENTES));
            for (String linea : lineas) {
                Paciente paciente = new Paciente();
                paciente = paciente.fromString(linea);
                if (paciente.getId() != 0) {
                    pacientes.add(paciente);
                }
            }
        } catch (IOException e) {
            System.err.println("error al leer el archivo: " + e.getMessage());
        }
        return pacientes;
    }
    
    public void guardarListaPacientes(List<Paciente> pacientes) throws Exception{
        try {
            List<String> lineas = pacientes.stream().map(Paciente::toString).collect(Collectors.toList());
            Files.write(Paths.get(ARCHIVO_PACIENTES), lineas);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
    
    public Paciente obtenerPacientePorID(int id) throws Exception{
        return listarPacientes().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
    
    public void actualizarPaciente(Paciente pacienteActualizado) throws Exception{
        List<Paciente> pacientes = listarPacientes();
        pacientes = pacientes.stream().map(p -> p.getId() == pacienteActualizado.getId() ? pacienteActualizado : p).collect(Collectors.toList());
        guardarListaPacientes(pacientes);
    }
    
    public void eliminarPaciente(int id) throws Exception{
        List<Paciente> pacientes = listarPacientes();
        pacientes.removeIf(p -> p.getId() == id);
        guardarListaPacientes(pacientes);
    }
}
