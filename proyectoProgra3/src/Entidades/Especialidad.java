/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author le0jx
 */
public class Especialidad {
    int id;
    String nombre;

    public Especialidad() {
    }

    public Especialidad(int id) {
        this.id = id;
    }
    
    public Especialidad(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
    
    public Especialidad fromString(String data) throws Exception{
        String[] parts = data.split(",");
        if (parts.length != 2) return null;
        return new Especialidad(Integer.parseInt(parts[0]), parts[1]);
    }

    @Override
    public String toString() {
        return id + "," + nombre;
    }
    
    
}
