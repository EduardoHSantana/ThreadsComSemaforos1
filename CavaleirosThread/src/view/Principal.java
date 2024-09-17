package view;

import controller.CavaleirosThread;

public class Principal {

	public static void main(String[] args) {
		CavaleirosThread cavaleiro1 = new CavaleirosThread("Cavaleiro 1");
		CavaleirosThread cavaleiro2 = new CavaleirosThread("Cavaleiro 2");
		CavaleirosThread cavaleiro3 = new CavaleirosThread("Cavaleiro 3");
		CavaleirosThread cavaleiro4 = new CavaleirosThread("Cavaleiro 4");
		
	    cavaleiro1.start();
		cavaleiro2.start();
		cavaleiro3.start();
		cavaleiro4.start();
	} 

}
