package rentacar.entity.dto;

import java.util.Date;

public class ReportDTO {

    public Date date;

    public String type;

    public Integer fuelLevel;

    public int fine;

    public int damage;

    public int rent;

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public Integer getFuelLevel() {
        return fuelLevel;
    }

    public int getFine() {
        return fine;
    }

    public int getDamage() {
        return damage;
    }

    public int getRent() {
        return rent;
    }
}
