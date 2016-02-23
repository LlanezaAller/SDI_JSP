package uo.sdi.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.crypto.Data;

import uo.sdi.model.Type.UserStatus;

/**
 * This class is not an entity, it is a DTO with the same fields as a row in the
 * table
 * 
 * @see Data Transfer Object pattern
 * @author alb
 *
 */
@Entity
@Table (name="TUSERS")
public class User {
	@Id @GeneratedValue
	private Long id;
	
	private String login;
	private String password;
	private String name;
	private String surname;
	private String email;
	
	@OneToMany(mappedBy="user")
	private Set<Application> aplicaciones = new HashSet<>();
	
	@OneToMany(mappedBy="user")
	private Set<Seat> seats = new HashSet<>();
	
	@OneToMany(mappedBy="promoter")
	private Set<Trip> trips = new HashSet<>();
	
	private UserStatus status;
	
	public User(){};

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "User [id=" + id 
				+ ", login=" + login 
				+ ", password=" + password 
				+ ", name=" + name 
				+ ", surname=" + surname 
				+ ", status=" + status 
				+ ", email=" + email
			+ "]";
	}

	//Metodos de relacion 
	
	Set<Application> _getApplications() {
		return aplicaciones;
	}
	
	public Set<Application> getApplications(){
		return Collections.unmodifiableSet(aplicaciones);
	}
	
	Set<Seat> _getSeats() {
		return seats;
	}
	
	public Set<Seat> getSeats(){
		return Collections.unmodifiableSet(seats);
	}

	Set<Trip> _getTrips(){
		return trips;
	}
	
	public Set<Trip> getTrips(){
		return Collections.unmodifiableSet(trips);
	}
	
	
}