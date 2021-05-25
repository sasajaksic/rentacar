package rentacar.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the rent database table.
 * 
 */
@Entity
@NamedQuery(name="Rent.findAll", query="SELECT r FROM Rent r")
public class Rent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RENT_ID_GENERATOR", sequenceName="RENT_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RENT_ID_GENERATOR")
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private Integer deposit;

	private Integer price;

	//bi-directional many-to-one association to PaymentMethod
	@ManyToOne
	@JoinColumn(name="payment_method")
	private PaymentMethod paymentMethodBean;

	//bi-directional many-to-one association to Reservation
	@ManyToOne
	@JoinColumn(name="reservation")
	private Reservation reservationBean;

	//bi-directional many-to-one association to Report
	@OneToMany(mappedBy="rentBean")
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

	public PaymentMethod getPaymentMethodBean() {
		return this.paymentMethodBean;
	}

	public void setPaymentMethodBean(PaymentMethod paymentMethodBean) {
		this.paymentMethodBean = paymentMethodBean;
	}

	public Reservation getReservationBean() {
		return this.reservationBean;
	}

	public void setReservationBean(Reservation reservationBean) {
		this.reservationBean = reservationBean;
	}

	public List<Report> getReports() {
		return this.reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public Report addReport(Report report) {
		getReports().add(report);
		report.setRentBean(this);

		return report;
	}

	public Report removeReport(Report report) {
		getReports().remove(report);
		report.setRentBean(null);

		return report;
	}

}