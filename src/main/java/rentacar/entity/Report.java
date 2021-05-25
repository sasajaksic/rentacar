package rentacar.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the report database table.
 * 
 */
@Entity
@NamedQuery(name="Report.findAll", query="SELECT r FROM Report r")
public class Report implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REPORT_ID_GENERATOR", sequenceName="REPORT_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REPORT_ID_GENERATOR")
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name="fuel_level")
	private Integer fuelLevel;

	private String type;

	//bi-directional many-to-one association to Damage
	@ManyToOne
	@JoinColumn(name="damage")
	private Damage damageBean;

	//bi-directional many-to-one association to Fine
	@ManyToOne
	@JoinColumn(name="fine")
	private Fine fineBean;

	//bi-directional many-to-one association to Rent
	@ManyToOne
	@JoinColumn(name="rent")
	private Rent rentBean;

	public Report() {
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

	public Integer getFuelLevel() {
		return this.fuelLevel;
	}

	public void setFuelLevel(Integer fuelLevel) {
		this.fuelLevel = fuelLevel;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Damage getDamageBean() {
		return this.damageBean;
	}

	public void setDamageBean(Damage damageBean) {
		this.damageBean = damageBean;
	}

	public Fine getFineBean() {
		return this.fineBean;
	}

	public void setFineBean(Fine fineBean) {
		this.fineBean = fineBean;
	}

	public Rent getRentBean() {
		return this.rentBean;
	}

	public void setRentBean(Rent rentBean) {
		this.rentBean = rentBean;
	}

}