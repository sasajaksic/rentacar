package rentacar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the appuser database table.
 *
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
@Entity
public class Appuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="APPUSER_ID_GENERATOR", sequenceName="APPUSER_SEQ", allocationSize = 1)
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

//	@JsonIgnore
//	@OneToMany(mappedBy="appuser")
//	private List<Reservation> reservations;

	public Integer getId() {
		return this.id;
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
}