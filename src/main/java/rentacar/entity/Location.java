package rentacar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the location database table.
 * 
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
@Entity
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LOCATION_ID_GENERATOR", sequenceName="LOCATION_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOCATION_ID_GENERATOR")
	private Integer id;

	private String city;

	private String number;

	private String street;

	private String image;

	//bi-directional many-to-one association to Appuser
	@ManyToOne
	@JoinColumn(name="manager")
	private Appuser appuser;

	//bi-directional many-to-one association to Reservation
	@JsonIgnore
	@OneToMany(mappedBy= "location")
	private List<Reservation> reservations;

	public Integer getId() {
		return this.id;
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

	public Appuser getManager() {
		return this.appuser;
	}

	public void setManager(Appuser appuser) {
		this.appuser = appuser;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Reservation> getReservations() {
		return this.reservations;
	}
}