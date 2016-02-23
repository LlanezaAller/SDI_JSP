package uo.sdi.model;

import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.crypto.Data;

/**
 * This class is not an entity, it is a DTO with the same fields as 
 * a row in the table
 * 
 * @see Data Transfer Object pattern
 * @author alb
 *
 */
@Entity
@Table (name= "TRATING")
public class Rating {

	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
	@Named("seat")
	private Seat fromSeat;
//	private Long seatFromTripId;
//	private Long seatFromUserId;
	
	@ManyToOne
	private Seat aboutSeat;
//	private Long seatAboutTripId;
//	private Long seatAboutUserId;

	private String comment;
	private Integer value = 0;

	public Seat getFromSeat() {
		return fromSeat;
	}

	public void setFromSeat(Seat fromSeat) {
		this.fromSeat = fromSeat;
	}

	public Seat getAboutSeat() {
		return aboutSeat;
	}

	public void setAboutSeat(Seat aboutSeat) {
		this.aboutSeat = aboutSeat;
	}

	public Rating(){};
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	
	
	@Override
	public String toString() {
		return "Rating [fromSeat=" + fromSeat +
				", aboutSeat=" + aboutSeat
				+ ", comment=" + comment 
				+ ", value=" + value + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aboutSeat == null) ? 0 : aboutSeat.hashCode());
		result = prime * result
				+ ((fromSeat == null) ? 0 : fromSeat.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Rating other = (Rating) obj;
		if (aboutSeat == null) {
			if (other.aboutSeat != null)
				return false;
		} else if (!aboutSeat.equals(other.aboutSeat))
			return false;
		if (fromSeat == null) {
			if (other.fromSeat != null)
				return false;
		} else if (!fromSeat.equals(other.fromSeat))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
}
