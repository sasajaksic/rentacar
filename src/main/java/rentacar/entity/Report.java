package rentacar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the report database table.
 *
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
@Entity
public class Report implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REPORT_ID_GENERATOR", sequenceName="REPORT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REPORT_ID_GENERATOR")
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String type;

	@Column(name="fuel_level")
	private Integer fuelLevel;

	//bi-directional many-to-one association to Fine
	@ManyToOne
	@JoinColumn(name="fine")
	private Fine fine;

	//bi-directional many-to-one association to Damage
	@ManyToOne
	@JoinColumn(name="damage")
	private Damage damage;

	//bi-directional many-to-one association to Rent
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="rent")
	private Rent rent;

	public Integer getId() {
		return this.id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getFuelLevel() {
		return this.fuelLevel;
	}

	public void setFuelLevel(Integer fuelLevel) {
		this.fuelLevel = fuelLevel;
	}

	public Fine getFine() {
		return this.fine;
	}

	public void setFine(Fine fine) {
		this.fine = fine;
	}

	public Damage getDamage() {
		return this.damage;
	}

	public void setDamage(Damage damage) {
		this.damage = damage;
	}

	public Rent getRent() {
		return this.rent;
	}

	public void setRent(Rent rent) {
		this.rent = rent;
	}
}