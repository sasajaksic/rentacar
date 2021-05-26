package rentacar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the fine database table.
 *
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
@Entity
public class Fine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FINE_ID_GENERATOR", sequenceName="FINE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FINE_ID_GENERATOR")
	private Integer id;

	private String type;

	private String description;

	private Integer price;

	//bi-directional many-to-one association to Report
	@JsonIgnore
	@OneToMany(mappedBy= "fine")
	private List<Report> reports;

	public Integer getId() {
		return this.id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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

	public List<Report> getReports() {
		return this.reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public Report addReport(Report report) {
		getReports().add(report);
		report.setFine(this);

		return report;
	}

	public Report removeReport(Report report) {
		getReports().remove(report);
		report.setFine(null);

		return report;
	}
}