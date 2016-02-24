package uo.sdi.model;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.crypto.Data;

import uo.sdi.model.Type.AddressPoint;
import uo.sdi.model.Type.TripStatus;

/**
 * This class is not an entity, it is a DTO with the same fields as 
 * a row in the table
 * 
 * @see Data Transfer Object pattern
 * @author alb
 *
 */
@Entity
@Table (name="TTRIPS")
public class Trip {
	
	@Id @GeneratedValue
	private Long id;
	
	
	@AttributeOverrides({
	@AttributeOverride(name="address",column=@Column(name="departure_address")),
	@AttributeOverride(name = "city", column = @Column(name = "departure_city")),
	@AttributeOverride(name = "state", column = @Column(name = "departure_state")),
	@AttributeOverride(name = "country", column = @Column(name = "departure_country")),
//	@AttributeOverride(name = "lat", column = @Column(name = "departure_wpt_lat")),
//	@AttributeOverride(name = "lon", column = @Column(name = "departure_wpt_lon")),
	@AttributeOverride(name = "zipCode", column = @Column(name = "departure_zipCode"))
	})
	@Embedded
	private AddressPoint departure;
	
	
	
	@AttributeOverrides( {
	@AttributeOverride(name = "address", column = @Column(name = "destination_address")),
	@AttributeOverride(name = "city", column = @Column(name = "destination_city")),
	@AttributeOverride(name = "state", column = @Column(name = "destination_state")),
	@AttributeOverride(name = "country", column = @Column(name = "destination_country")),
//	@AttributeOverride(name = "lat", column = @Column(name = "destination_wpt_lat")),
//	@AttributeOverride(name = "lon", column = @Column(name = "destination_wpt_lon")),
	@AttributeOverride(name = "zipCode", column = @Column(name = "destination_zipCode"))
	})
	@Embedded
	private AddressPoint destination;
	
	private Date arrivalDate;
	private Date departureDate;
	private Date closingDate;
	private Integer availablePax = 0; 
	private Integer maxPax = 0;
	private Double estimatedCost = 0.0;
	private String comments;
	@Enumerated(EnumType.STRING)
	private TripStatus status;


	//Relaciones
	@OneToMany(mappedBy="trip")
	private Set<Application> aplicaciones = new HashSet<>();
	
	@OneToMany(mappedBy="trip")
	private Set<Seat> seats = new HashSet<>();

	@ManyToOne
	private User promoter;
	
	//Metodos
	public Trip(){};
	

	public AddressPoint getDeparture() {
		return departure;
	}

	public void setDeparture(AddressPoint departure) {
		this.departure = departure;
	}

	public TripStatus getStatus() {
		return status;
	}

	public void setStatus(TripStatus status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AddressPoint getDestination() {
		return destination;
	}

	public void setDestination(AddressPoint destination) {
		this.destination = destination;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public Integer getAvailablePax() {
		return availablePax;
	}

	public void setAvailablePax(Integer availablePax) {
		this.availablePax = availablePax;
	}

	public Integer getMaxPax() {
		return maxPax;
	}

	public void setMaxPax(Integer maxPax) {
		this.maxPax = maxPax;
	}

	public Double getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(Double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((arrivalDate == null) ? 0 : arrivalDate.hashCode());
		result = prime * result
				+ ((closingDate == null) ? 0 : closingDate.hashCode());
		result = prime * result
				+ ((departure == null) ? 0 : departure.hashCode());
		result = prime * result
				+ ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Trip other = (Trip) obj;
		if (arrivalDate == null) {
			if (other.arrivalDate != null)
				return false;
		} else if (!arrivalDate.equals(other.arrivalDate))
			return false;
		if (closingDate == null) {
			if (other.closingDate != null)
				return false;
		} else if (!closingDate.equals(other.closingDate))
			return false;
		if (departure == null) {
			if (other.departure != null)
				return false;
		} else if (!departure.equals(other.departure))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (status != other.status)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Trip [id=" + id 
				+ ", departure=" + departure 
				+ ", destination=" + destination 
				+ ", arrivalDate=" + arrivalDate 
				+ ", departureDate=" + departureDate 
				+ ", closingDate=" + closingDate 
				+ ", availablePax=" + availablePax 
				+ ", maxPax=" + maxPax 
				+ ", estimatedCost=" + estimatedCost 
				+ ", comments=" + comments 
				+ ", status=" + status
				+ ", promoterId=" + promoter 
			+ "]";
	}

	// Metodos de relacion
	
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
	
	public User getPromoter(){
		return promoter;
	}
	
	void _setPromoter(User promoter){
		this.promoter = promoter;
	}
	
	
}
