package com.cad.jeu_abstrait;

public abstract class Action {
	
	protected Partie partie;

	public abstract class Builder{
		
		private Partie partie;
		
		public Builder(Partie p){
			partie = p;
		}
		
		public abstract Action build();
	}
	
	public Action(Builder builder){
		partie = builder.partie;
	}
	
	public abstract int exectute();
}
