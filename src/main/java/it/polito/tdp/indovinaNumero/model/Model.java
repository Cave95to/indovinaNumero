package it.polito.tdp.indovinaNumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {

	private final int NMAX = 100;
	private final int TMAX = 8;
	private int numSegreto;
	private int tentativiFatti;
	//private boolean inGioco=false;
	
	private Set<Integer> tentativi;
	
	public void nuovaPartita () {
		
		// gestione nuova partita, che prima facevamo direttamente nel controller
		this.tentativiFatti=0;
    	//this.inGioco= true;
    	this.numSegreto = (int)(Math.random()*NMAX)+1;
    	tentativi = new HashSet<>();
	}
	
	// "valutiamo" il numero inserito dall'utente
	public int controllo(int numero) {
			
		//controllo il numero inserito, conviene DELEGARE validazione ad un altro metodo
		if (tentativoValido(numero)==false) {
			throw new InvalidParameterException("Devi inserire un numero tra 1 e "+NMAX+", oppure numero già inserito\n");
		}
		
		//il tentativo è valido
		this.tentativiFatti++;
		this.tentativi.add(numero);
		
		/*
		 * blocchiamo prossima giocata if(this.tentativiFatti == TMAX) this.inGioco =
		 * false;
		 */
	
		if (numero == this.numSegreto) {
			return 0;
		}
			//controllo se era ultimo tentativo
		if (this.tentativiFatti==this.TMAX) {
				throw new IllegalStateException ("La partita è terminata! Il numero segreto era "+this.numSegreto+"\n");
		}
		
		if (numero > this.numSegreto) 
			return 1;
		else
			return -1;
		
			
	}
	
	// VERA validazione del numero inserito
	private boolean tentativoValido(int numero) {
		
		if (numero<1 || numero > NMAX)
			return false;
		
		// estensione l'utente non può inserire due volte lo stesso numero
		if (tentativi.contains(numero))
			return false;
			
		return true;
	}
	
	public int getNumSegreto() {
		return numSegreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	
}
