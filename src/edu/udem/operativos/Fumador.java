package edu.udem.operativos;

import java.util.LinkedList;

/**
 * Created by Ismael on 05/05/2016.
 */
public class Fumador extends Thread {

    private Mesa mesa;
    private String ingrediente;
    private LinkedList<String> ingredientes_necesarios;

    public Fumador (Mesa mesa, String ingrediente_propio){
        this.mesa = mesa;
        this.ingrediente = ingrediente_propio;
        this.ingredientes_necesarios = new LinkedList<>();
        if (ingrediente_propio == "Tabaco") {
            ingredientes_necesarios.addLast("Mechero");
            ingredientes_necesarios.addLast("Papel");
        }else if (ingrediente_propio == "Mechero") {
            ingredientes_necesarios.addLast("Tabaco");
            ingredientes_necesarios.addLast("Papel");
        }else if (ingrediente_propio == "Papel") {
            ingredientes_necesarios.addLast("Tabaco");
            ingredientes_necesarios.addLast("Mechero");
        }
    }

    public void run(){
        //esperamos a la mesa para empezar
        try {
            mesa.semDaleAlLatch.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace(); //¬¬ maldito java
        }
        //típico "desactivar interrupciones" estilo java
        //sección crítica y tal
        synchronized (mesa.latch){
            mesa.latch.countDown();
        }
        //esperamos a los ingredientes
        try {
            mesa.semIngredientes.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //estamos en sección crítica
        //aquí comprobamos si los ingredientes nos sirven
        int ingComunes = 0;
        for (String ing: mesa.ingredientesOfrecidos){
            if (ingredientes_necesarios.get(0) == ing ||
                ingredientes_necesarios.get(1) == ing){
                ingComunes++;
            }
        }
        //si me faltan los dos ingredientes en la mesa, puedo fumar
        if(ingComunes == 2){
            System.out.println("Puedo fumar! Yo tengo " + ingrediente);
            System.out.println(":D");
        }
        mesa.semFumados.release();


    }
}
