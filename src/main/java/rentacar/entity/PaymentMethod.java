package rentacar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the payment_method database table.
 *
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
@Entity
@Table(name="payment_method")
public class PaymentMethod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PAYMENT_METHOD_ID_GENERATOR", sequenceName="PAYMENT_METHOD_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAYMENT_METHOD_ID_GENERATOR")
	private Integer id;

	private String method;

	private String description;

	//bi-directional many-to-one association to Rent
	@JsonIgnore
	@OneToMany(mappedBy= "paymentMethod")
	private List<Rent> rents;

	public Integer getId() {
		return this.id;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Rent> getRents() {
		return this.rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}

	public Rent addRent(Rent rent) {
		getRents().add(rent);
		rent.setPaymentMethod(this);

		return rent;
	}

	public Rent removeRent(Rent rent) {
		getRents().remove(rent);
		rent.setPaymentMethod(null);

		return rent;
	}
}