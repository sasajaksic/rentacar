package rentacar.entity.dto;

import java.util.Date;

public class ReservationDTO {

    public Date date;

    public Date pickupDate;

    public Date returnDate;

    public Integer price;

    public int manager;

    public int client;

    public int location;

    public int vehicle;

    public Date getDate() {
        return date;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public Integer getPrice() {
        return price;
    }

    public int getManager() {
        return manager;
    }

    public int getClient() {
        return client;
    }

    public int getLocation() {
        return location;
    }

    public int getVehicle() {
        return vehicle;
    }
}
