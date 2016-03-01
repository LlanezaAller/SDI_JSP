package uo.sdi.persistence;

import java.util.List;

import uo.sdi.model.User;

public interface UserFinder {

	User findByLogin(String login);

	List<User> findAll();

	void update(User usuario);

	void newUser(User usuario);

	List<User> findUsersByTrip(Long id);

}
