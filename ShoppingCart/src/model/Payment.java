package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PAYMENT database table.
 * 
 */
@Entity
@Table(name="Payment",schema="testDb")
@NamedQuery(name="Payment.findAll", query="SELECT p FROM Payment p")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PAYMENT_ID")
	private long paymentId;

	@Column(name="BILLING_ADDRESS")
	private String billingAddress;

	private long cardnumber;

	@Column(name="SHIPPING_ADDRESS")
	private String shippingAddress;

	//bi-directional many-to-one association to Userprofile
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Userprofile userprofile;

	public Payment() {
	}

	public long getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public String getBillingAddress() {
		return this.billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public long getCardnumber() {
		return this.cardnumber;
	}

	public void setCardnumber(long cardnumber) {
		this.cardnumber = cardnumber;
	}

	public String getShippingAddress() {
		return this.shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Userprofile getUserprofile() {
		return this.userprofile;
	}

	public void setUserprofile(Userprofile userprofile) {
		this.userprofile = userprofile;
	}

}