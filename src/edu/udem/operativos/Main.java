package edu.udem.operativos;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        while(true) {
            Mesa mesa = new Mesa();
            Fumador tabaco = new Fumador(mesa, "Tabaco");
            Fumador papel = new Fumador(mesa, "Papel");
            Fumador mechero = new Fumador(mesa, "Mechero");

            mesa.start();
            tabaco.start();
            papel.start();
            mechero.start();
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
