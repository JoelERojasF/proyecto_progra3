/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hilos;

import Interfaces.IaccionTiempo;

/**
 *
 * @author le0jx
 */
public class TimerReiniciable implements Runnable {

    private int segundos = 60;
    private boolean running = true;
    private Thread hilo;
    private IaccionTiempo action;

    public TimerReiniciable(IaccionTiempo action) {
        this.action = action;
    }

    @Override
    public void run() {
        while (running) {
            try {
                System.out.println("Quedan: " + segundos + " segundos");
                Thread.sleep(1000);
                segundos--;

                if (segundos <= 0) {
                    action.cuandoTiempoAcabe(); 
                    segundos = 60;
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Hilo interrumpido");
            }
        }
    }
    
    public void startTimer() {
        System.out.println("se comenzara el hilo");
    if (hilo == null || !hilo.isAlive()) {
        hilo = new Thread(this);
        hilo.start();
        System.out.println("el hilo a comenzado");
    }
}

    public void stop() {
        running = false;
        if (hilo != null) {
            hilo.interrupt();
        }
        hilo = null;
    }
}
