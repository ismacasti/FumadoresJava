package edu.udem.operativos;

public class Main {

    public static void main(String[] args) {

        Mesa mesa = new Mesa();
        Fumador tabaco = new Fumador(mesa, "Tabaco");
        Fumador papel = new Fumador(mesa, "Papel");
        Fumador mechero = new Fumador(mesa, "Mechero");
        mesa.start();
        tabaco.start();
        papel.start();
        mechero.start();
    }
}
