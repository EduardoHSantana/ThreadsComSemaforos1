package controller;

import java.util.concurrent.Semaphore;

public class Atleta extends Thread {
    private String nome;
    private int pontos;
    private int pontuacaoTiro;
    private int posicaoRanking; // Posição do atleta no array de ranking
    private Semaphore armas;

    public Atleta(String nome, Semaphore armas, int posicaoRanking) {
        this.nome = nome;
        this.pontos = 0;
        this.pontuacaoTiro = 0;
        this.armas = armas;
        this.posicaoRanking = posicaoRanking;
    }

    public int getPontos() {
        return pontos;
    }

    public String getNome() {
        return nome;
    }

    public void run() {
        try {
            corrida();
            tiro();
            ciclismo();
            finalizar();
        } catch (InterruptedException e) {
            System.out.println(nome + " foi interrompido.");
        }
    }

    private void corrida() throws InterruptedException {
        // Cada atleta corre entre 20 e 25 m a cada 30 ms até completar 3 km
        int distanciaPercorrida = 0;
        while (distanciaPercorrida < 3000) {
            int velocidade = (int) (Math.random() * 6) + 20; // 20 a 25 m por ciclo
            distanciaPercorrida += velocidade;
            Thread.sleep(30);
        }
        // Determina os pontos baseados na posição de chegada
        pontos = 250 - (Ranking.getPosicaoChegada() - 1) * 10;
        System.out.println(nome + " terminou a corrida e recebeu " + pontos + " pontos.");
    }

    private void tiro() throws InterruptedException {
        // Cada atleta faz 3 tiros quando consegue uma arma
        armas.acquire(); // Adquire uma arma
        System.out.println(nome + " está fazendo os tiros.");

        for (int i = 1; i <= 3; i++) {
            int pontuacao = (int) (Math.random() * 11); // Pontuação entre 0 e 10
            pontuacaoTiro += pontuacao;
            System.out.println(nome + " fez " + pontuacao + " pontos no tiro " + i);
            int tempoTiro = (int) (Math.random() * 2500) + 500; // Tempo entre 0.5 e 3 segundos
            Thread.sleep(tempoTiro);
        }

        armas.release(); // Libera a arma para o próximo atleta
    }

    private void ciclismo() throws InterruptedException {
        // Cada atleta pedala entre 30 e 40 m a cada 40 ms até completar 5 km
        int distanciaPercorrida = 0;
        while (distanciaPercorrida < 5000) {
            int velocidade = (int) (Math.random() * 11) + 30; // 30 a 40 m por ciclo
            distanciaPercorrida += velocidade;
            Thread.sleep(40);
        }
        System.out.println(nome + " terminou o ciclismo.");
    }

    private void finalizar() {
        // Soma os pontos dos tiros à pontuação final
        pontos += pontuacaoTiro;
        System.out.println(nome + " terminou a prova com " + pontos + " pontos.");
        Ranking.adicionarAtleta(this, posicaoRanking); // Insere o atleta no ranking
    }
}
