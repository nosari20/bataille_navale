package com.cad.dao;

import com.cad.codesUtils.DAOUtils;

public abstract class AbstractDAOFactory {

	public abstract PartieBatailleNavaleDao getPartieBatailleNavaleDao();

	public static AbstractDAOFactory getAbstractDAOFactory(DAOUtils mean) {
		switch (mean) {
		case XML:
			return new ConcreteXMLFactory();
		default:
			return null;
		}
	}
}
