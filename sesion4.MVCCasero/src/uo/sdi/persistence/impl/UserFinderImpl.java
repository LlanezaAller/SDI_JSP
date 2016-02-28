package uo.sdi.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import uo.sdi.model.User;
import uo.sdi.persistence.UserFinder;
import uo.sdi.persistence.util.Jpa;

public class UserFinderImpl implements UserFinder {

	@Override
	public List<User> findAll() {
		return Jpa.getManager()
				.createNamedQuery("User.findAll", User.class)
				.getResultList();
	}

	@Override
	public User findByLogin(String login) {
		List<User> usuarios = Jpa.getManager()
				.createNamedQuery("User.findUserByLogin",
						User.class)
				.setParameter(1, login).getResultList();
		return (usuarios.size() > 0) ? usuarios.get(0) : null;
	}
	@Override
	public List<User> findUsersByTrip(Long id) {
		List<User> usuarios = Jpa.getManager()
				.createNamedQuery("User.findAllByTrip",
						User.class)
						.setParameter(1, id)
						.getResultList();
		return (usuarios != null) ? usuarios : new ArrayList<User>();
	}

	@Override
	public void update(User usuario) {
		Jpa.getManager().merge(usuario);
	}


	@Override
	public void save(User usuario){
		Jpa.getManager().persist(usuario);
	}
}
