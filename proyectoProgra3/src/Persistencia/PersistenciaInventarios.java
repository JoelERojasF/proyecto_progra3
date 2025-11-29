/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidades.EquipoMedico;
import Interfaces.Iconteo;
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
public class PersistenciaInventarios implements Ireporte<EquipoMedico>, Iordenamiento<EquipoMedico>, Iconteo<EquipoMedico>{
    private static final String ARCHIVO_INVENTARIOS = "inventarios.txt";

    public PersistenciaInventarios() {
    }
    
    public void agregarInventario(EquipoMedico equipoMedico) throws Exception{
        try {
            Files.write(Paths.get(ARCHIVO_INVENTARIOS), (equipoMedico.toString() + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    public List<EquipoMedico> listarInventarios() throws Exception{
        List<EquipoMedico> consultas = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(ARCHIVO_INVENTARIOS));
            for (String linea : lineas) {
                EquipoMedico equipoMedico = new EquipoMedico();
                equipoMedico = equipoMedico.fromString(linea);
                if (equipoMedico.getId() != 0) {
                    consultas.add(equipoMedico);
                }
            }
        } catch (IOException e) {
            System.err.println("error al leer el archivo: " + e.getMessage());
        }
        return consultas;
    }
    
    public void guardarListaInventarios(List<EquipoMedico> inventario) throws Exception{
        try {
            List<String> lineas = inventario.stream().map(EquipoMedico::toString).collect(Collectors.toList());
            Files.write(Paths.get(ARCHIVO_INVENTARIOS), lineas);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
    
    public EquipoMedico obtenerInventarioPorId(int id) throws Exception{
        return listarInventarios().stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }
    
    public void actualizarInventario(EquipoMedico equipoMedicoActualizada) throws Exception{
        List<EquipoMedico> inventario = listarInventarios();
        inventario = inventario.stream().map(c -> c.getId() == equipoMedicoActualizada.getId() ? equipoMedicoActualizada : c).collect(Collectors.toList());
        guardarListaInventarios(inventario);
    }
    
    public void eliminarInventario(int id) throws Exception{
        List<EquipoMedico> inventario = listarInventarios();
        inventario.removeIf(c -> c.getId() == id);
        guardarListaInventarios(inventario);
    }

    @Override
    public String generarReporte(List<EquipoMedico> lista) {
        return lista.stream().map(e -> "Nombre: {" + e.getNombre() + "} Cantidad: {" + e.getCantidad() + "}" ).collect(Collectors.joining("\n"));
    }

    @Override
    public List ordenarDatos(List<EquipoMedico> lista) {
        return lista.stream().sorted(Comparator.comparing(EquipoMedico::getCantidad)).toList();
    }

    @Override
    public long contarDatos(List<EquipoMedico> lista) {
            return lista.stream().mapToLong(EquipoMedico::getCantidad).sum();
    }
}
