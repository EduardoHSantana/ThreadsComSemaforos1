package controller;

public class Ranking {
    private static Atleta[] atletas = new Atleta[25];
    private static int posicaoChegada = 0; 

    // Método para definir a posição de chegada 
    public static int getPosicaoChegada() {
        posicaoChegada++; // Incrementa a posição de chegada manualmente
        return posicaoChegada;
    }

    // Adiciona o atleta no ranking de acordo com a posição fixa fornecida
    public static void adicionarAtleta(Atleta atleta, int posicaoRanking) {
        atletas[posicaoRanking] = atleta;
        if (posicaoRanking == 24) { // Quando o último atleta é inserido, o ranking é exibido
            ordenarRanking();
            exibirRanking();
        }
    }

    // Ordena os atletas pelo método de ordenação simples (bubble sort)
    private static void ordenarRanking() {
        for (int i = 0; i < atletas.length - 1; i++) {
            for (int j = 0; j < atletas.length - 1 - i; j++) {
                if (atletas[j].getPontos() < atletas[j + 1].getPontos()) {
                    Atleta temp = atletas[j];
                    atletas[j] = atletas[j + 1];
                    atletas[j + 1] = temp;
                }
            }
        }
    }

    // Exibe o ranking final de todos os atletas
    private static void exibirRanking() {
        System.out.println("\nRanking Final:");
        for (int i = 0; i < atletas.length; i++) {
            System.out.println((i + 1) + "º lugar: " + atletas[i].getNome() + " - " + atletas[i].getPontos() + " pontos");
        }
    }
}

