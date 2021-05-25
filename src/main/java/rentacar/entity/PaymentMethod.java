package rentacar.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the payment_method database table.
 * 
 */
@Entity
@Table(name="payment_method")
@NamedQuery(name="PaymentMethod.findAll", query="SELECT p FROM PaymentMethod p")
public class PaymentMethod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PAYMENT_METHOD_ID_GENERATOR", sequenceName="PAYMENT_METHOD_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAYMENT_METHOD_ID_GENERATOR")
	private Integer id;

	private String description;

	private String method;

	//bi-directional many-to-one association to Rent
	@OneToMany(mappedBy="paymentMethodBean")
	private List<Rent> rents;

	public PaymentMethod() {
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

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public List<Rent> getRents() {
		return this.rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}

	public Rent addRent(Rent rent) {
		getRents().add(rent);
		rent.setPaymentMethodBean(this);

		return rent;
	}

	public Rent removeRent(Rent rent) {
		getRents().remove(rent);
		rent.setPaymentMethodBean(null);

		return rent;
	}

}