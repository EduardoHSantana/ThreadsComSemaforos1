package controller;

import java.util.concurrent.Semaphore;

public class Aviao extends Thread {
    private String nome;
    private Pista pista;

    // Semáforo para limitar o número de aviões na área de decolagem a 2
    private static Semaphore areaDecolagemSemaforo = new Semaphore(2);

    public Aviao(String nome, Pista pista) {
        this.nome = nome;
        this.pista = pista;
    }

    public void run() {
        try {
            // Fases da decolagem
            manobra();
            taxiar();
            decolar();
            afastar();
        } catch (InterruptedException e) {
            System.out.println(nome + " foi interrompido.");
        }
    }

    private void manobra() throws InterruptedException {
        // Fase de manobra (300 a 700 ms) usando Math.random()
        int tempo = (int) (Math.random() * 400) + 300;
        System.out.println(nome + " está na fase de manobra na pista " + pista.getNome() + " (" + tempo + " ms)");
        Thread.sleep(tempo);
    }

    private void taxiar() throws InterruptedException {
        // Fase de taxiar (500 a 1000 ms) usando Math.random()
        int tempo = (int) (Math.random() * 500) + 500;
        System.out.println(nome + " está taxiando na pista " + pista.getNome() + " (" + tempo + " ms)");
        Thread.sleep(tempo);
    }

    private void decolar() throws InterruptedException {
        // Limitar a área de decolagem a apenas 2 aviões simultaneamente
        areaDecolagemSemaforo.acquire();
        try {
            pista.usarPista(this);  // Usar a pista (decisão controlada pelo semáforo da pista)
        } finally {
            areaDecolagemSemaforo.release(); // Libera o acesso à área de decolagem
        }
    }

    private void afastar() throws InterruptedException {
        // Fase de afastamento (300 a 800 ms) usando Math.random()
        int tempo = (int) (Math.random() * 500) + 300;
        System.out.println(nome + " está na fase de afastamento da pista " + pista.getNome() + " (" + tempo + " ms)");
        Thread.sleep(tempo);
        System.out.println(nome + " completou a decolagem.");
    }
}
