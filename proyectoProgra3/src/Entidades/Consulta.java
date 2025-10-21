/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import objetosServicio.Fecha;

/**
 *
 * @author le0jx
 */
public class Consulta {
    int id;
    Paciente paciente;
    Medico medico;
    Fecha fecha;

    public Consulta() {
    }

    public Consulta(int id) {
        this.id = id;
    }
    
    public Consulta(int id, Paciente paciente, Medico medico, Fecha fecha) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }
    
    public Consulta fromString(String data) throws Exception{
        String[] parts = data.split(",");
        if (parts.length != 10) return null;
        return new Consulta(Integer.parseInt(parts[0]), new Paciente(Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]), parts[4]), new Medico(Integer.parseInt(parts[5]), parts[6], new Especialidad(Integer.parseInt(parts[7]), parts[8])), new Fecha(parts[9]));
    }

    @Override
    public String toString() {
        return id + "," + paciente.toString() + "," + medico.toString() + "," + fecha.toString();    
    }
    
    
}
