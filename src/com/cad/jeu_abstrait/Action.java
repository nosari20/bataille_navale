package com.cad.jeu_abstrait;

public abstract class Action {
	
	protected Partie partie;
	
	public Action(Partie p){
		partie = p;
	}
	
	public abstract int execute();
	
	public static abstract class Builder{
		
		public Partie partie;
		
		public Builder partie(Partie p){
			partie = p;
			return this;
		}
		
		public abstract Action build();
	}
}
