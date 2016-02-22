package uo.sdi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	
	

	private Long seatFromTripId;
	private Long seatFromUserId;
	private Long seatAboutTripId;
	private Long seatAboutUserId;

	private String comment;
	private Integer value = 0;

	public Rating(){};
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSeatFromTripId() {
		return seatFromTripId;
	}

	public void setSeatFromTripId(Long seatFromTripId) {
		this.seatFromTripId = seatFromTripId;
	}

	public Long getSeatFromUserId() {
		return seatFromUserId;
	}

	public void setSeatFromUserId(Long seatFromUserId) {
		this.seatFromUserId = seatFromUserId;
	}

	public Long getSeatAboutTripId() {
		return seatAboutTripId;
	}

	public void setSeatAboutTripId(Long seatAboutTripId) {
		this.seatAboutTripId = seatAboutTripId;
	}

	public Long getSeatAboutUserId() {
		return seatAboutUserId;
	}

	public void setSeatAboutUserId(Long seatAboutUserId) {
		this.seatAboutUserId = seatAboutUserId;
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
		return "Rating [id=" + id 
				+ ", comment=" + comment 
				+ ", value=" + value 
				+ ", seatFromTripId=" + seatFromTripId
				+ ", seatFromUserId=" + seatFromUserId 
				+ ", seatAboutTripId=" + seatAboutTripId 
				+ ", seatAboutUserId=" + seatAboutUserId 
			+ "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result
				+ ((seatAboutTripId == null) ? 0 : seatAboutTripId.hashCode());
		result = prime * result
				+ ((seatAboutUserId == null) ? 0 : seatAboutUserId.hashCode());
		result = prime * result
				+ ((seatFromTripId == null) ? 0 : seatFromTripId.hashCode());
		result = prime * result
				+ ((seatFromUserId == null) ? 0 : seatFromUserId.hashCode());
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
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (seatAboutTripId == null) {
			if (other.seatAboutTripId != null)
				return false;
		} else if (!seatAboutTripId.equals(other.seatAboutTripId))
			return false;
		if (seatAboutUserId == null) {
			if (other.seatAboutUserId != null)
				return false;
		} else if (!seatAboutUserId.equals(other.seatAboutUserId))
			return false;
		if (seatFromTripId == null) {
			if (other.seatFromTripId != null)
				return false;
		} else if (!seatFromTripId.equals(other.seatFromTripId))
			return false;
		if (seatFromUserId == null) {
			if (other.seatFromUserId != null)
				return false;
		} else if (!seatFromUserId.equals(other.seatFromUserId))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
}
