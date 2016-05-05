package edu.udem.operativos;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Agente extends Thread {

	
	Mesa mesa=new Mesa();
	String ingrediente;
	int numCambio=0;
	public Agente( ) 
	{
		// TODO Auto-generated constructor stub
		
		
		
	}
	
		public void run() 
		{
			try {
			int ing1;
			int ing2;
			
			while (numCambio<4)
			{
				ing1=ThreadLocalRandom.current().nextInt(1, 4);
			
				ing2=ThreadLocalRandom.current().nextInt(1, 4);
				//1 tabaco
				//2 cerillo
				//3 papel 
				
				//mesa.quitarIngredientes(ing1,ing2);
				mesa.nuevosIngredientes(ing1,ing2);
			
				Thread.sleep(5000);
				numCambio++;
			}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
			
		}
	

		public static void main(String[] args)
		{
			// TODO Auto-generated method stub
			
			Agente ag=new Agente();
			ag.start();

		}
		
		
}
