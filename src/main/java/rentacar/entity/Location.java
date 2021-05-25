package rentacar.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the location database table.
 * 
 */
@Entity
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LOCATION_ID_GENERATOR", sequenceName="LOCATION_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOCATION_ID_GENERATOR")
	private Integer id;

	private String city;

	private String number;

	private String street;

	//bi-directional many-to-one association to Appuser
	@ManyToOne
	@JoinColumn(name="manager")
	private Appuser appuser;

	//bi-directional many-to-one association to Reservation
	@OneToMany(mappedBy="locationBean")
	private List<Reservation> reservations;

	public Location() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Appuser getAppuser() {
		return this.appuser;
	}

	public void setAppuser(Appuser appuser) {
		this.appuser = appuser;
	}

	public List<Reservation> getReservations() {
		return this.reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Reservation addReservation(Reservation reservation) {
		getReservations().add(reservation);
		reservation.setLocationBean(this);

		return reservation;
	}

	public Reservation removeReservation(Reservation reservation) {
		getReservations().remove(reservation);
		reservation.setLocationBean(null);

		return reservation;
	}

}