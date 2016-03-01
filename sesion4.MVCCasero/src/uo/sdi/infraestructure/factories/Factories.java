package uo.sdi.infraestructure.factories;

import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.impl.JpaPersistenceFactory;

public class Factories {
	public static PersistenceFactory persistence = new JpaPersistenceFactory();
}
