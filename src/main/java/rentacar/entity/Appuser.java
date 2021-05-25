package rentacar.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the appuser database table.
 * 
 */
@Entity
@NamedQuery(name="Appuser.findAll", query="SELECT a FROM Appuser a")
public class Appuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="APPUSER_ID_GENERATOR", sequenceName="APPUSER_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APPUSER_ID_GENERATOR")
	private Integer id;

	private String adress;

	@Column(name="card_number")
	private String cardNumber;

	@Column(name="driver_licence")
	private String driverLicence;

	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String password;

	private String phone;

	private Integer role;

	private String username;

	//bi-directional many-to-one association to Location
	@OneToMany(mappedBy="appuser")
	private List<Location> locations;

	//bi-directional many-to-one association to Reservation
	@OneToMany(mappedBy="appuser1")
	private List<Reservation> reservations1;

	//bi-directional many-to-one association to Reservation
	@OneToMany(mappedBy="appuser2")
	private List<Reservation> reservations2;

	public Appuser() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getDriverLicence() {
		return this.driverLicence;
	}

	public void setDriverLicence(String driverLicence) {
		this.driverLicence = driverLicence;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getRole() {
		return this.role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Location> getLocations() {
		return this.locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public Location addLocation(Location location) {
		getLocations().add(location);
		location.setAppuser(this);

		return location;
	}

	public Location removeLocation(Location location) {
		getLocations().remove(location);
		location.setAppuser(null);

		return location;
	}

	public List<Reservation> getReservations1() {
		return this.reservations1;
	}

	public void setReservations1(List<Reservation> reservations1) {
		this.reservations1 = reservations1;
	}

	public Reservation addReservations1(Reservation reservations1) {
		getReservations1().add(reservations1);
		reservations1.setAppuser1(this);

		return reservations1;
	}

	public Reservation removeReservations1(Reservation reservations1) {
		getReservations1().remove(reservations1);
		reservations1.setAppuser1(null);

		return reservations1;
	}

	public List<Reservation> getReservations2() {
		return this.reservations2;
	}

	public void setReservations2(List<Reservation> reservations2) {
		this.reservations2 = reservations2;
	}

	public Reservation addReservations2(Reservation reservations2) {
		getReservations2().add(reservations2);
		reservations2.setAppuser2(this);

		return reservations2;
	}

	public Reservation removeReservations2(Reservation reservations2) {
		getReservations2().remove(reservations2);
		reservations2.setAppuser2(null);

		return reservations2;
	}

}