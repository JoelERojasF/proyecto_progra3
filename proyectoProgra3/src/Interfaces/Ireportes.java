/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import java.util.List;

/**
 *
 * @author le0jx
 * @param <T>
 */
public interface Ireportes <T>{
    String generarReoprte(List<T> lista);
    List<T> filtrarBusqueda(List<T> lista, String filtro);
    List<T> ordenarResultados(List<T> lista);
    int SumarResultados(List<T> lista);
}
