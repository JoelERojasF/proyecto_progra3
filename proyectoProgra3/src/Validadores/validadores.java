/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author le0jx
 */
public class validadores {
    //paciente
    public boolean validarNombrePaciente(String nombre){
        String patron = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{1,50}$";
        Pattern p = Pattern.compile(patron);
        Matcher matcher = p.matcher(nombre);
        
        return matcher.matches();
    }
    
    public boolean validarEdad(String edad){
        String patron = "^(?:0|[1-9]\\d?|1[01]\\d|120)$";
        Pattern p = Pattern.compile(patron);
        Matcher matcher = p.matcher(edad);
        
        return matcher.matches();
    }
    
    public boolean validarDireccion(String direccion){
        String patron = "^[A-Za-z-zÁÉÍÓÚáéíóúÑñ0-9 .,]*$";
        Pattern p = Pattern.compile(patron);
        Matcher matcher = p.matcher(direccion);
        
        return matcher.matches();
    }
    
    //medico
    public boolean validarNombreMedico(String nombre){
        String patron = "^[A-Za-zÁÉÍÓÚáéíóúÑñ .]{1,50}$";
        Pattern p = Pattern.compile(patron);
        Matcher matcher = p.matcher(nombre);
        
        return matcher.matches();
    }
    
    public boolean validarEspecialidad(String especialidad){
        String patron = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{1,30}$";
        Pattern p = Pattern.compile(patron);
        Matcher matcher = p.matcher(especialidad);
        
        return matcher.matches();
    }
    
    //equipo medico
    public boolean validarNombreEquipo(String nombre){
        String patron = "^[A-Za-z0-9]{1,30}$";
        Pattern p = Pattern.compile(patron);
        Matcher matcher = p.matcher(nombre);
        
        return matcher.matches();
    }
    
    public boolean validrCantidadEquipo(String cantidad){
        String patron = "^[1-9]\\d*$";
        Pattern p = Pattern.compile(patron);
        Matcher matcher = p.matcher(cantidad);
        
        return matcher.matches();
    }
    
    //consulta
    public boolean validarFechaConsulta(String fecha){
        String patron = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
        Pattern p = Pattern.compile(patron);
        Matcher matcher = p.matcher(fecha);
        
        return matcher.matches();
    }
    
    public boolean validarId(String id){
        String patron = "^[1-9]\\d*$";
        Pattern p = Pattern.compile(patron);
        Matcher matcher = p.matcher(id);
        
        return matcher.matches();
    }
    
    //busquedas
    //busqueda de paciente por nombre
    //busqueda de consulta por fecha
}
