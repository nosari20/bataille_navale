package com.cad.dao;

import com.cad.bataille_navale.jeu.PartieBatailleNavale;

public interface PartieBatailleNavaleDao {

	public void save(PartieBatailleNavale partie);

	public PartieBatailleNavale load(String url);
}
