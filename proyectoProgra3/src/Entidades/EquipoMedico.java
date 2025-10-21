/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author le0jx
 */
public class EquipoMedico {
    int id;
    String nombre;
    int cantidad;

    public EquipoMedico() {
    }

    public EquipoMedico(int id) {
        this.id = id;
    }
    
    public EquipoMedico(int id, String nombre, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public EquipoMedico fromString(String data) throws Exception{
        String[] parts = data.split(",");
        if (parts.length != 3) return null;
        return new EquipoMedico(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]));
    }

    @Override
    public String toString() {
        return id + "," + nombre + "," + cantidad;
    }
            
    
}
