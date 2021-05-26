package rentacar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the rent database table.
 * 
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
@Entity
public class Rent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RENT_ID_GENERATOR", sequenceName="RENT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RENT_ID_GENERATOR")
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private Integer deposit;

	private Integer price;

	//bi-directional many-to-one association to PaymentMethod
	@ManyToOne
	@JoinColumn(name="payment_method")
	private PaymentMethod paymentMethod;

	//bi-directional many-to-one association to Reservation
	@ManyToOne
	@JoinColumn(name="reservation")
	private Reservation reservation;

	//bi-directional many-to-one association to Report
	//@JsonIgnore
	@OneToMany(mappedBy= "rent")
	private List<Report> reports;

	public Rent() {
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

	public Integer getDeposit() {
		return this.deposit;
	}

	public void setDeposit(Integer deposit) {
		this.deposit = deposit;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public PaymentMethod getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Reservation getReservation() {
		return this.reservation;
	}

	public void setReservation(Reservation reservationBean) {
		this.reservation = reservationBean;
	}

	public List<Report> getReports() {
		return this.reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public Report addReport(Report report) {
		getReports().add(report);
		report.setRent(this);

		return report;
	}

	public Report removeReport(Report report) {
		getReports().remove(report);
		report.setRent(null);

		return report;
	}

}