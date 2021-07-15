package rentacar.entity.dto;

import javax.persistence.Column;

public class VehicleDTO {

    public String brand;

    public Integer doorNumber;

    public String fuel;

    public String gearbox;

    public String model;

    public Integer price;

    public Integer productionYear;

    public Integer seatNumber;

    public String type;

    public String image;

    public String getBrand() {
        return brand;
    }

    public Integer getDoorNumber() {
        return doorNumber;
    }

    public String getFuel() {
        return fuel;
    }

    public String getGearbox() {
        return gearbox;
    }

    public String getModel() {
        return model;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public String getType() {
        return type;
    }

    public String getImage() {
        return image;
    }

}
