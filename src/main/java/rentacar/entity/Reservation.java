package rentacar.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the reservation database table.
 * 
 */
@Entity
@NamedQuery(name="Reservation.findAll", query="SELECT r FROM Reservation r")
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RESERVATION_ID_GENERATOR", sequenceName="RESERVATION_SEQ")
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
	@OneToMany(mappedBy="reservationBean")
	private List<Rent> rents;

	//bi-directional many-to-one association to Appuser
	@ManyToOne
	@JoinColumn(name="client")
	private Appuser appuser1;

	//bi-directional many-to-one association to Appuser
	@ManyToOne
	@JoinColumn(name="manager")
	private Appuser appuser2;

	//bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(name="location")
	private Location locationBean;

	//bi-directional many-to-one association to Vehicle
	@ManyToOne
	@JoinColumn(name="vehicle")
	private Vehicle vehicleBean;

	public Reservation() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		rent.setReservationBean(this);

		return rent;
	}

	public Rent removeRent(Rent rent) {
		getRents().remove(rent);
		rent.setReservationBean(null);

		return rent;
	}

	public Appuser getAppuser1() {
		return this.appuser1;
	}

	public void setAppuser1(Appuser appuser1) {
		this.appuser1 = appuser1;
	}

	public Appuser getAppuser2() {
		return this.appuser2;
	}

	public void setAppuser2(Appuser appuser2) {
		this.appuser2 = appuser2;
	}

	public Location getLocationBean() {
		return this.locationBean;
	}

	public void setLocationBean(Location locationBean) {
		this.locationBean = locationBean;
	}

	public Vehicle getVehicleBean() {
		return this.vehicleBean;
	}

	public void setVehicleBean(Vehicle vehicleBean) {
		this.vehicleBean = vehicleBean;
	}

}