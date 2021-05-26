package rentacar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vehicle database table.
 * 
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
@Entity
public class Vehicle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="VEHICLE_ID_GENERATOR", sequenceName="VEHICLE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VEHICLE_ID_GENERATOR")
	private Integer id;

	private String brand;

	@Column(name="door_number")
	private Integer doorNumber;

	private String fuel;

	private String gearbox;

	private String model;

	private Integer price;

	@Column(name="production_year")
	private Integer productionYear;

	@Column(name="seat_number")
	private Integer seatNumber;

	private String type;

	//bi-directional many-to-one association to Reservation
	@JsonIgnore
	@OneToMany(mappedBy= "vehicle")
	private List<Reservation> reservations;

	public Integer getId() {
		return this.id;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getDoorNumber() {
		return this.doorNumber;
	}

	public void setDoorNumber(Integer doorNumber) {
		this.doorNumber = doorNumber;
	}

	public String getFuel() {
		return this.fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getGearbox() {
		return this.gearbox;
	}

	public void setGearbox(String gearbox) {
		this.gearbox = gearbox;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getProductionYear() {
		return this.productionYear;
	}

	public void setProductionYear(Integer productionYear) {
		this.productionYear = productionYear;
	}

	public Integer getSeatNumber() {
		return this.seatNumber;
	}

	public void setSeatNumber(Integer seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Reservation> getReservations() {
		return this.reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Reservation addReservation(Reservation reservation) {
		getReservations().add(reservation);
		reservation.setVehicle(this);

		return reservation;
	}

	public Reservation removeReservation(Reservation reservation) {
		getReservations().remove(reservation);
		reservation.setVehicle(null);

		return reservation;
	}

}