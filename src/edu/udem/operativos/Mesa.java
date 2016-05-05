package edu.udem.operativos;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Mesa {

	
	Semaphore turnoCerillo;
	Semaphore turnoTabaco;
	Semaphore turnoPapel;
	Fumador fumador1;
	Fumador fumador2;
	Fumador fumador3;
	boolean fumando=false;
	
	boolean nuevosIngredientes;
	
	String ing1;
	String ing2;
	
	public Mesa()
	
	{
		// TODO Auto-generated constructor stub
		
		this.turnoCerillo=new Semaphore(1);
		this.turnoTabaco=new Semaphore(1);
		this.turnoPapel=new Semaphore(1);
		
		fumador1=new Fumador("tabaco", this);
		fumador2=new Fumador("cerillo", this);
		fumador3=new Fumador("papel", this);
		
		fumador1.start();
		fumador2.start();
		fumador3.start();
		
	}
	
		
	public void tieneTabaco() throws InterruptedException
	{
		turnoTabaco.acquire();
		
		//tomar cerillo
		//Tomar papel
		System.out.print("fumador con "+fumador1.ingrediente);
		fumador1.fumar() ;
		//Regresar ingrediente a la mesa
		
		
	}
	
	public void tieneCerillo() throws InterruptedException
	{
		turnoCerillo.acquire();
		fumador2.fumar();
		System.out.print("fumador con "+fumador2.ingrediente);
		
	}
	
	
	public void tienePapel() throws InterruptedException
	{
		turnoPapel.acquire();
		fumador3.fumar();
		System.out.print("fumador con "+fumador3.ingrediente);
	}
	
	public void nuevosIngredientes(int ing1, int ing2)
	{
		//1 tabaco
		//2 cerillo
		//3 papel
		
		if (ing1==1 && ing2==2) 
			 
		{
			turnoPapel.release();
		}
		else
			if (ing1==2 && ing2==3) 
				 
			{
				turnoTabaco.release();
			}
			else
				turnoCerillo.release();
		
	}
		
		
		
		
		
		

	
	

	
	
	
	
	

}
