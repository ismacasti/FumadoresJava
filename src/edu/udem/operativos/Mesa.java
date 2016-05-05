package edu.udem.operativos;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * Created by Ismael on 05/05/2016.
 */
public class Mesa extends Thread {
    //la mesa se encarga de manejar a los fumadores
    //es una mesa muy lista y que sabe de Dijkstra

    //semáforos
    public Semaphore semFumados = new Semaphore(0);
    public Semaphore semIngredientes = new Semaphore(0);
    //el latch es un semáforo que ejecuta sus hilos bloqueados simultáneamente.
    //lo hacemos para que la mesa espere hasta que todos los fumadores estén
    //listos.
    public CountDownLatch latch;
    //tiene su semáforo para que no haya lios al cambiarlo
    public Semaphore semDaleAlLatch = new Semaphore(0);

    public LinkedList<String> ingredientesOfrecidos;

    public Mesa(){
        this.ingredientesOfrecidos = new LinkedList<>();
    }

    public void run() {
        Random random = new Random();
        int ingredientes_actuales;

        latch = new CountDownLatch(3); //tres fumadores
        semDaleAlLatch.release(3);
        //esperamos a que los fumadores estén listos
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //ponemos cosas en la mesa
        ingredientes_actuales = random.nextInt(3);
        switch (ingredientes_actuales) {
            case 0: //falta papel
                ingredientesOfrecidos.addLast("Tabaco");
                ingredientesOfrecidos.addLast("Mechero");
                break;
            case 1: //falta tabaco
                ingredientesOfrecidos.addLast("Papel");
                ingredientesOfrecidos.addLast("Mechero");
                break;
            case 2: //falta mechero
                ingredientesOfrecidos.addLast("Tabaco");
                ingredientesOfrecidos.addLast("Papel");
                break;
        }
        System.out.print("Ingredientes en la mesa: ");
        for (String ing : ingredientesOfrecidos) {
            System.out.print(ing + " ");
        }
        System.out.print("\n");
        //soltamos los ingredientes
        semIngredientes.release(3);
        //esperamos a los fumadores
        try {
            semFumados.acquire(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }


}
