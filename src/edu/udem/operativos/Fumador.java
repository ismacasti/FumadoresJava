package edu.udem.operativos;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Fumador extends Thread {

	boolean tabaco;
	boolean cerillo;
	boolean papel;
	
	int vecesFumadas=0;
	String ingrediente;
	int ing;
	Mesa mesa;
	
	public Fumador(String ingrediente, Mesa mesa)
	
	{
		if (ingrediente.equalsIgnoreCase("tabaco"))
			tabaco=true;
		if (ingrediente.equalsIgnoreCase("cerillo"))
			cerillo=true;
		if (ingrediente.equalsIgnoreCase("papel"))
			papel=true;
		
		this.mesa=mesa;
		
	}
	
	
	
	public void run()  
	{
		
		//1 tabaco
		//2 cerillo
		//3 papel 
		
		try {
			
			//mesa.quieroFumar();
			while (vecesFumadas<5)
			{
				if (tabaco)
				mesa.tieneTabaco();
			if (cerillo)
				mesa.tieneCerillo();
			if (papel)
				mesa.tienePapel();
			//fumar
			
			
			}
			 
			
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
	}
	
	public void fumar() throws InterruptedException
	{
		System.out.println("Estoy Fumando tengo" +ingrediente);
		vecesFumadas++;
		Thread.sleep(5000);
		
	}
	
	
	
	
	
	
	

}
