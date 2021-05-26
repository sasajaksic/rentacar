package rentacar.entity.dto;

import rentacar.entity.PaymentMethod;
import rentacar.entity.Reservation;

import java.util.Date;

public class RentDTO {

    public Date date;

    public Integer deposit;

    public Integer price;

    public int paymentMethod;

    public int reservation;

    public Date getDate() {
        return date;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public Integer getPrice() {
        return price;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public int getReservation() {
        return reservation;
    }
}
