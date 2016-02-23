package uo.sdi.model;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	
	private AddressPoint departure;
	private AddressPoint destination;
	private Date arrivalDate;
	private Date departureDate;
	private Date closingDate;
	private Integer availablePax = 0; 
	private Integer maxPax = 0;
	private Double estimatedCost = 0.0;
	private String comments;
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