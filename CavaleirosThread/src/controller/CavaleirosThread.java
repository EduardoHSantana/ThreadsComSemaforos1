package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class CavaleirosThread extends Thread {
		private String nome;
		private int velocidade;
		private int posicao;
		private boolean temTocha;
		private boolean temPedra;
		private static final int corredor_comprimento = 2000;
		private static final int tocha_posicao = 500;
		private static final int pedra_posicao = 1500;
		private static Semaphore tocha = new Semaphore(1);
		private static Semaphore pedra = new Semaphore(1);
		
		public CavaleirosThread(String nome) {
			this.nome = nome;
			this.velocidade = (int) (Math.random()*3) + 2;
			this.posicao = 0;
			this.temTocha = false;
			this.temPedra = false;
		}
		
		@Override
		public void run() {
			try {
				while (posicao < corredor_comprimento) {
					Thread.sleep(50);
					posicao = posicao + velocidade;
					System.out.println(nome + "está na posição" + posicao + "m com velocidade" + velocidade + " m/50ms");
					   if (posicao >= tocha_posicao && !temTocha && tocha.tryAcquire()) {
						   temTocha = true;
						   velocidade = velocidade + 2;
						   System.out.println(nome + " pegou a tocha! Nova velocidade: " + velocidade);
					   }
					   if (posicao >= pedra_posicao && !temPedra && !temTocha && pedra.tryAcquire()) {
						   temPedra = true;
						   velocidade = velocidade + 2;
						   System.out.println(nome + " pegou a pedra brilhante! Nova velocidade: " + velocidade);
					   }
				}
					   escolherPorta();
					   
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		
	        
			private void escolherPorta() {
	            System.out.println(nome + " chegou ao final do corredor e está escolhendo uma porta...");
	            int portaEscolhida = (int) (Math.random() * 4) + 1;
	            System.out.println(nome + " escolheu a porta " + portaEscolhida);
	            if(portaEscolhida == 1) {
	            	System.out.println(nome + " encontrou a saída!");
	            } else {
	            	System.out.println(nome + " foi devorado por monstros");
	            }
			}   	
		}
            