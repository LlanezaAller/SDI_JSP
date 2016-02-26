package uo.sdi.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.crypto.Data;

import uo.sdi.model.keys.SeatKey;
import uo.sdi.model.type.SeatStatus;

/**
 * This class is not an entity, it is a DTO with the same fields as a row in the
 * table
 * 
 * @see Data Transfer Object pattern
 * @author alb
 *
 */
@Entity
@Table (name ="TSEATS")
@IdClass (SeatKey.class)
public class Seat {

	@Id @ManyToOne private User user;
	@Id @ManyToOne private Trip trip;

	private String comment;

	@Enumerated(EnumType.STRING)
	private SeatStatus status;
	
	@OneToMany(mappedBy="fromSeat")
	private Set<Rating> ratingsFrom = new HashSet<>();
	
	@OneToMany(mappedBy="aboutSeat")
	private Set<Rating> ratingsAbout = new HashSet<>();

	public Seat(){};

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public SeatStatus getStatus() {
		return status;
	}

	public void setStatus(SeatStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Seat [userId=" + user + ", tripId=" 
				+ trip + ", comment=" + comment 
				+ ", status=" + status + "]";
	}
	
	//Relaciones
	
	Set<Rating> _getRatingsFrom() {
		return ratingsFrom;
	}
	
	public void unlink(){
		user._getApplications().remove(this);
		trip._getApplications().remove(this);
		this.user = null;
		this.trip = null;
	}
	
	public Set<Rating> getRatingsFrom(){
		return Collections.unmodifiableSet(ratingsFrom);
	}
	
	Set<Rating> _getRatingsAbout(){
		return ratingsAbout;
	}
	
	public Set<Rating> getRatingsAbout(){
		return Collections.unmodifiableSet(ratingsAbout);
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((trip == null) ? 0 : trip.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seat other = (Seat) obj;
		if (trip == null) {
			if (other.trip != null)
				return false;
		} else if (!trip.equals(other.trip))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	
}
