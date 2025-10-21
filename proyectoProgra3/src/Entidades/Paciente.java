/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author le0jx
 */
public class Paciente {
    int id;
    String nombre;
    int edad;
    String direccion;

    public Paciente() {
    }

    public Paciente(int id) {
        this.id = id;
    }

    public Paciente(int id, String nombre, int edad, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;   
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public Paciente fromString(String data) throws Exception{
        String[] parts = data.split(",");
        if (parts.length != 4) return null;
        return new Paciente(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]), parts[3]);
    }

    @Override
    public String toString() {
        return id + "," + nombre + "," + edad + "," + direccion;    
    }
    
}
