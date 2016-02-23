package uo.sdi.persistence.impl;

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
	public void update(User usuario) {
		Jpa.getManager().merge(usuario);
	}

}
