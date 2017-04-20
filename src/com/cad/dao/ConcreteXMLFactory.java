package com.cad.dao;

public class ConcreteXMLFactory extends AbstractDAOFactory {

	@Override
	public PartieBatailleNavaleDao getPartieBatailleNavaleDao() {
		return PartieBatailleNavaleXMLDAO.getInstance();
	}

}
