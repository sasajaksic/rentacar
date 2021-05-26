package rentacar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the reservation database table.
 * 
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
@Entity
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RESERVATION_ID_GENERATOR", sequenceName="RESERVATION_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RESERVATION_ID_GENERATOR")
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Temporal(TemporalType.DATE)
	@Column(name="pickup_date")
	private Date pickupDate;

	private Integer price;

	@Temporal(TemporalType.DATE)
	@Column(name="return_date")
	private Date returnDate;

	//bi-directional many-to-one association to Rent
	@JsonIgnore
	@OneToMany(mappedBy= "reservation")
	private List<Rent> rents;

	//bi-directional many-to-one association to Appuser
	@ManyToOne
	@JoinColumn(name="client")
	private Appuser manager;

	//bi-directional many-to-one association to Appuser
	@ManyToOne
	@JoinColumn(name="manager")
	private Appuser client;

	//bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(name="location")
	private Location location;

	//bi-directional many-to-one association to Vehicle
	@ManyToOne
	@JoinColumn(name="vehicle")
	private Vehicle vehicle;

	public Integer getId() {
		return this.id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getPickupDate() {
		return this.pickupDate;
	}

	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Date getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public List<Rent> getRents() {
		return this.rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}

	public Rent addRent(Rent rent) {
		getRents().add(rent);
		rent.setReservation(this);

		return rent;
	}

	public Rent removeRent(Rent rent) {
		getRents().remove(rent);
		rent.setReservation(null);

		return rent;
	}

	public Appuser getManager() {
		return this.manager;
	}

	public void setManager(Appuser manager) {
		this.manager = manager;
	}

	public Appuser getClient() {
		return this.client;
	}

	public void setClient(Appuser client) {
		this.client = client;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}