package controller;

import java.util.concurrent.Semaphore;

public class Pista {
    private String nome;
    private Semaphore semaforo;

    public Pista(String nome) {
        this.nome = nome;
        this.semaforo = new Semaphore(1); // Apenas um avião pode usar a pista por vez
    }

    public String getNome() {
        return nome;
    }

    public void usarPista(Aviao aviao) throws InterruptedException {
        // Limitar o uso da pista a 1 aeronave por vez
        semaforo.acquire();
        try {
            // Fase de decolagem (600 a 800 ms) usando Math.random()
            int tempo = (int) (Math.random() * 200) + 600;
            System.out.println(aviao.getName() + " está decolando na pista " + nome + " (" + tempo + " ms)");
            Thread.sleep(tempo);
        } finally {
            semaforo.release(); // Libera a pista para outro avião
        }
    }
}
