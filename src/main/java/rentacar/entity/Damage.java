package rentacar.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the damage database table.
 * 
 */
@Entity
@NamedQuery(name="Damage.findAll", query="SELECT d FROM Damage d")
public class Damage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DAMAGE_ID_GENERATOR", sequenceName="DAMAGE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DAMAGE_ID_GENERATOR")
	private Integer id;

	private String description;

	private Integer price;

	private String type;

	//bi-directional many-to-one association to Report
	@OneToMany(mappedBy="damageBean")
	private List<Report> reports;

	public Damage() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Report> getReports() {
		return this.reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public Report addReport(Report report) {
		getReports().add(report);
		report.setDamageBean(this);

		return report;
	}

	public Report removeReport(Report report) {
		getReports().remove(report);
		report.setDamageBean(null);

		return report;
	}

}