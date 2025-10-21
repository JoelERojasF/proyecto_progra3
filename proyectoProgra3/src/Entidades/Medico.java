/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author le0jx
 */
public class Medico {
    int id;
    String nombre;
    Especialidad especialidad;

    public Medico() {
    }

    public Medico(int id) {
        this.id = id;
    }
    
    public Medico(int id, String nombre, Especialidad especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
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

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
    
    public Medico fromString(String data) throws Exception{
        String[] parts = data.split(",");
        if (parts.length != 4) return null;
        return new Medico(Integer.parseInt(parts[0]), parts[1], new Especialidad(Integer.parseInt(parts[2]), parts[3]));
    }

    @Override
    public String toString() {
        return id + "," + nombre + "," + especialidad.toString();
    }
    
    
    
}
