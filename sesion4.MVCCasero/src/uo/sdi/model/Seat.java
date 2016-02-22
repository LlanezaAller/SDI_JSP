package uo.sdi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.crypto.Data;

import uo.sdi.model.Type.SeatKey;
import uo.sdi.model.Type.SeatStatus;

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
	private SeatStatus status;

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

}
