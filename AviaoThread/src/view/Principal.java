package view;

import controller.Aviao;
import controller.Pista;

public class Principal {
    public static void main(String[] args) {
        // Cria as duas pistas
        Pista pistaNorte = new Pista("Norte");
        Pista pistaSul = new Pista("Sul");

        // Simula 12 aeronaves
        for (int i = 1; i <= 12; i++) {
            Pista pistaEscolhida = Math.random() < 0.5 ? pistaNorte : pistaSul;
            Aviao aviao = new Aviao("Avião " + i, pistaEscolhida);
            aviao.start();
        }
    }
}
