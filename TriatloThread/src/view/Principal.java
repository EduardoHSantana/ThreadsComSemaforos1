package view;

import controller.Atleta;
import java.util.concurrent.Semaphore;

public class Principal {
    public static void main(String[] args) {
        // 5 armas de tiro disponíveis para 25 atletas
        Semaphore armas = new Semaphore(5);

        // Criar e iniciar 25 atletas, atribuindo uma posição fixa no ranking para cada um
        for (int i = 0; i < 25; i++) {
            Atleta atleta = new Atleta("Atleta " + (i + 1), armas, i);
            atleta.start();
        }
    }
}
